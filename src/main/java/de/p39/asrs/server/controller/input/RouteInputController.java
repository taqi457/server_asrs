package de.p39.asrs.server.controller.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBException;

import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.controller.exceptions.StorageException;
import de.p39.asrs.server.controller.file.FileType;
import de.p39.asrs.server.controller.file.Storage;
import de.p39.asrs.server.controller.input.info.RouteInfo;
import de.p39.asrs.server.controller.util.parser.KMLParser;
import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.LocaleDescription;
import de.p39.asrs.server.model.LocaleName;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;

/**
 * 
 * @author adrianrebmann
 *
 */
@Controller
public class RouteInputController {

	private RouteDAO dao;

	private CategoryDAO CategoryDaoInterface;

	private List<Site> selectedSites;

	private Category selectedCategory;

	private Storage storageService;

	private KMLParser parser;

	private Route route;

	@Autowired
	public RouteInputController(SiteDAO siteDAO ,RouteDAO routeDAO, CategoryDAO cdao, Storage storage) {
		this.dao = routeDAO;
		this.CategoryDaoInterface = cdao;
		this.storageService = storage;
		parser = new KMLParser(siteDAO);
	}

	@GetMapping("/kml/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(FileType.KML, filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/routeinfo")
	public String handleRouteInfo(@ModelAttribute RouteInfo info) {
		this.create(info);
		return "redirect:editroute/" + route.getId();
	}

	@PostMapping("/editroute")
	public String editRoute(@ModelAttribute RouteInfo info, @RequestParam("id") Long id, @RequestParam("category") Long category) {
		this.edit(info, id, category);
		return "redirect:editroute/" + id;
	}

	private void edit(RouteInfo info, Long id, Long category) {
		route = dao.getRouteById(id);
		if (route != null) {
			/*List<Route> exists = this.dao.getRouteByPath(route.getPathToKml());
			if (!exists.isEmpty()) {
				Route existing = exists.get(0);
				this.dao.deleteRoute(existing.getId());
			}*/
			this.addInfo(route, info);
			route.setCategory(CategoryDaoInterface.getCategoryById(category));
			this.dao.updateRoute(route);
			route = null;
		}
	}

	private void create(RouteInfo info) {
		if (route != null) {
			/*List<Route> exists = this.dao.getRouteByPath(route.getPathToKml());
			if (!exists.isEmpty()) {
				Route existing = exists.get(0);
				this.dao.deleteRoute(existing.getId());
			}*/
			this.addInfo(route, info);
			Route new_route = this.dao.instertRoute(route);
			route.setId(new_route.getId());
			route = null;
		}
	}

	@PostMapping("/kml")
	public String handleFileUploadAndCreateRoute(@RequestParam("kml") MultipartFile file,
			RedirectAttributes redirectAttributes, Model model) {
		String path = storageService.store(file, FileType.KML);
		Route r = new Route();
		try {
			parser.parseKml(path,r);
		} catch (JAXBException e) {
			// TODO Some feedback that the kml was wrong !
			e.printStackTrace();
		}
		this.route = r;
		// redirectAttributes.addFlashAttribute("message",
		// "Route successfully created with " + file.getOriginalFilename() +
		// "!");
		model.addAttribute("RouteInfo", new RouteInfo());
		model.addAttribute("categories", CategoryDaoInterface.getCategoriesByType("route"));

		return "/routeform";
	}

	@ExceptionHandler(StorageException.class)
	public ResponseEntity<Object> handleStorageFileNotFound(StorageException exc) {
		return ResponseEntity.notFound().build();
	}

	private Route addInfo(Route r, RouteInfo info) {
		ArrayList<LocaleName> names = new ArrayList<LocaleName>();
		ArrayList<LocaleDescription> descriptions = new ArrayList<LocaleDescription>();

		if (info.getNameDE() != null) {
			LocaleName name = new LocaleName(Locale.GERMAN, info.getNameDE());
			names.add(name);
		}
		if (info.getDescriptionEN() != null) {
			LocaleName name = new LocaleName(Locale.ENGLISH, info.getNameEN());
			names.add(name);

		}
		if (info.getNameFR() != null) {
			LocaleName name = new LocaleName(Locale.FRENCH, info.getNameFR());
			names.add(name);
		}
		if (info.getDescriptionDE() != null) {
			LocaleDescription description = new LocaleDescription(Locale.GERMAN, info.getDescriptionDE());
			descriptions.add(description);
		}
		if (info.getDescriptionEN() != null) {
			LocaleDescription description = new LocaleDescription(Locale.ENGLISH, info.getDescriptionEN());
			descriptions.add(description);
		}
		if (info.getDescriptionFR() != null) {
			LocaleDescription description = new LocaleDescription(Locale.FRENCH, info.getDescriptionFR());
			descriptions.add(description);
		}
		if (info.getDurationByFoot() != null) {
			r.setDurationByFoot(info.getDurationByFoot());
		}
		if (info.getDurationByBike() != null) {
			r.setDurationByBike(info.getDurationByBike());
		}
		if (info.getCategory() != null) {
			r.setCategory(CategoryDaoInterface.getCategoryById(info.getCategory()));
		}
		r.setDescriptions(descriptions);
		r.setNames(names);
		return r;
	}

	@ModelAttribute("allRoutes")
	public List<Route> allRoutes() {
		System.out.println("all routes called");
		return this.dao.getAllRoutes();
	}

	/**
	 * @return the selectedSites
	 */
	public List<Site> getSelectedSites() {
		return selectedSites;
	}

	/**
	 * @param selectedSites
	 *            the selectedSites to set
	 */
	public void setSelectedSites(List<Site> selectedSites) {
		this.selectedSites = selectedSites;
	}

	/**
	 * @return the selectedCategory
	 */
	public Category getSelectedCategory() {
		return selectedCategory;
	}

	/**
	 * @param selectedCategory
	 *            the selectedCategory to set
	 */
	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

}
