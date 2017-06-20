package de.p39.asrs.server.controller.input;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.Part;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import de.p39.asrs.server.controller.exceptions.StorageException;
import de.p39.asrs.server.controller.file.FileType;
import de.p39.asrs.server.controller.file.Storage;
import de.p39.asrs.server.controller.util.reader.KMLReader;
import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.LocaleDescription;
import de.p39.asrs.server.model.LocaleName;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;
import de.p39.asrs.server.model.input.RouteInfo;

/**
 * 
 * @author adrianrebmann
 *
 */
@Controller
public class RouteInputController {

	private RouteDAO dao;

	private List<Site> selectedSites;

	private Category selectedCategory;

	private Storage storageService;

	private Route route;

	@Autowired
	public RouteInputController(RouteDAO dao, Storage storage) {
		this.dao = dao;
		this.storageService = storage;
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
		return "/routeoverview";
	}

	private void create(RouteInfo info) {
		if (route != null) {
			List<Route> exists = this.dao.getRouteByPath(route.getPathToKml());
			if (!exists.isEmpty()) {
				Route existing = exists.get(0);
				this.dao.deleteRoute(existing.getId());
			}
			this.addInfo(route, info);
			this.dao.instertRoute(route);
			route = null;
		}
	}

	@PostMapping("/kml")
	public String handleFileUploadAndCreateRoute(@RequestParam("kml") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		String path = storageService.store(file, FileType.KML);

		KMLReader kmlreader = new KMLReader();
		Route r = null;
		try {
			r = kmlreader.parseKml(path);
		} catch (JAXBException e) {
			// TODO Some feedback that the kml was wrong !
			e.printStackTrace();
		}
		this.route = r;
		redirectAttributes.addFlashAttribute("message",
				"Route successfully created with " + file.getOriginalFilename() + "!");

		return "redirect:/uploadkml";
	}

	@ExceptionHandler(StorageException.class)
	public ResponseEntity handleStorageFileNotFound(StorageException exc) {
		return ResponseEntity.notFound().build();
	}

	private Route addInfo(Route r, RouteInfo info) {
		if (info.getNameDE() != null) {
			LocaleName name = new LocaleName(Locale.GERMAN, info.getNameDE());
			r.addLocaleName(name);
		}
		if (info.getDescriptionEN() != null) {
			LocaleName name = new LocaleName(Locale.ENGLISH, info.getNameEN());
			r.addLocaleName(name);
		}
		if (info.getNameFR() != null) {
			LocaleName name = new LocaleName(Locale.FRENCH, info.getNameFR());
			r.addLocaleName(name);
		}
		if (info.getDescriptionDE() != null) {
			LocaleDescription description = new LocaleDescription(Locale.GERMAN, info.getDescriptionDE());
			r.addLocaleDescription(description);
		}
		if (info.getDescriptionEN() != null) {
			LocaleDescription description = new LocaleDescription(Locale.ENGLISH, info.getDescriptionEN());
			r.addLocaleDescription(description);
		}
		if (info.getDescriptionFR() != null) {
			LocaleDescription description = new LocaleDescription(Locale.FRENCH, info.getDescriptionFR());
			r.addLocaleDescription(description);
		}
		if (info.getDuratonByFoot() != null) {
			r.setDurationByFoot(info.getDuratonByFoot());
		}
		if (info.getDurationByBike() != null) {
			r.setDurationByBike(info.getDurationByBike());
		}
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
