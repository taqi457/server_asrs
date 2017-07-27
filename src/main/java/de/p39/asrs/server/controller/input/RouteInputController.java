package de.p39.asrs.server.controller.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBException;

import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.model.*;
import de.p39.asrs.server.model.media.Audio;
import de.p39.asrs.server.model.media.Picture;
import de.p39.asrs.server.model.media.Size;
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
	public RouteInputController(RouteDAO routeDAO, CategoryDAO cdao, Storage storage) {
		this.dao = routeDAO;
		this.CategoryDaoInterface = cdao;
		this.storageService = storage;
		parser = new KMLParser();
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
	public String handleRouteInfo(@ModelAttribute RouteInfo info, @RequestParam("audio_de") MultipartFile audio_de,
								  @RequestParam("audio_en") MultipartFile audio_en, @RequestParam("audio_fr") MultipartFile audio_fr) {
		MultipartFile[] audios = {audio_de, audio_fr, audio_en};
		Long id = this.create(info, audios);
		if (id != 0)
		return "redirect:editroute/" + id;
		else
			return "redirect:routeoverview";
	}

	@PostMapping("/editroute")
	public String editRoute(@ModelAttribute RouteInfo info, @RequestParam("id") Long id, @RequestParam("category") Long category,
							@RequestParam("audio_de") MultipartFile audio_de,
							@RequestParam("audio_en") MultipartFile audio_en, @RequestParam("audio_fr") MultipartFile audio_fr) {
		MultipartFile[] audios = {audio_de, audio_fr, audio_en};
		this.edit(info, id, category, audios);

		return "redirect:editroute/" + id;
	}

	private void edit(RouteInfo info, Long id, Long category, MultipartFile[] audios) {
		route = dao.getRouteById(id);
		if (route != null) {
			/*List<Route> exists = this.dao.getRouteByPath(route.getPathToKml());
			if (!exists.isEmpty()) {
				Route existing = exists.get(0);
				this.dao.deleteRoute(existing.getId());
			}*/
			this.uploadMedia(route, audios);
			this.addInfo(route, info);
			route.setCategory(CategoryDaoInterface.getCategoryById(category));
			route.isCompleted();
			this.dao.updateRoute(route);
			route = null;
		}
	}

	private long create(RouteInfo info, MultipartFile[] audios) {
		if (route != null) {
			/*List<Route> exists = this.dao.getRouteByPath(route.getPathToKml());
			if (!exists.isEmpty()) {
				Route existing = exists.get(0);
				this.dao.deleteRoute(existing.getId());
			}*/
			this.addInfo(route, info);
			this.uploadMedia(route, audios);
			Route new_route = this.dao.updateRoute(route);
			for(Site s : new_route.getSites()){
				s.setRoute(new_route.getId());
			}
			new_route = this.dao.updateRoute(new_route);
			route = null;
			return new_route.getId();

		}
		return 0;
	}

	private void uploadMedia(Route route, MultipartFile[] audios) {
		for (int i = 0; i < audios.length; i++) {
			if (audios[i].isEmpty()) {
				if(route.getAudios().size() >= i)
				route.addLocaleAudio(route.getAudios().get(i));
				continue;
			}
			String path = storageService.store(audios[i], FileType.AUDIO);
			Audio audio = new Audio();
			audio.setPath(path);
			List<LocaleName> names = new ArrayList<>();
			names.add(new LocaleName(Locale.GERMAN, audios[i].getOriginalFilename()));
			audio.setNames(names);
			if (i == 0){
				route.addLocaleAudio(new LocaleAudio(Locale.GERMAN, audio));
			}
			else if (i == 1){
				route.addLocaleAudio(new LocaleAudio(Locale.FRENCH, audio));
			}
			else if (i==2){
				route.addLocaleAudio(new LocaleAudio(Locale.ENGLISH, audio));
			}
		}

	}

	@PostMapping("/kml")
	public String handleFileUploadAndCreateRoute(@RequestParam("kml") MultipartFile file,
			RedirectAttributes redirectAttributes, Model model) {
		String path = storageService.store(file, FileType.KML);
		Route r = new Route();
		r = dao.instertRoute(r);
		try {
			parser.parseKml(path,r);
		} catch (JAXBException e) {
			// TODO Some feedback that the kml was wrong !
			e.printStackTrace();
		}
		this.route = r;
		r.setPath(path);
		// redirectAttributes.addFlashAttribute("message",
		// "Route successfully created with " + file.getOriginalFilename() +
		// "!");
		model.addAttribute("RouteInfo", new RouteInfo());
		model.addAttribute("categories", CategoryDaoInterface.getCategoriesByType(CategoryType.ROUTE));

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
			names.add(0, name);
		}
		if (info.getDescriptionEN() != null) {
			LocaleName name = new LocaleName(Locale.ENGLISH, info.getNameEN());
			names.add(1, name);

		}
		if (info.getNameFR() != null) {
			LocaleName name = new LocaleName(Locale.FRENCH, info.getNameFR());
			names.add(1, name);
		}
		if (info.getDescriptionDE() != null) {
			LocaleDescription description = new LocaleDescription(Locale.GERMAN, info.getDescriptionDE());
			descriptions.add(0, description);
		}
		if (info.getDescriptionEN() != null) {
			LocaleDescription description = new LocaleDescription(Locale.ENGLISH, info.getDescriptionEN());
			descriptions.add(1, description);
		}
		if (info.getDescriptionFR() != null) {
			LocaleDescription description = new LocaleDescription(Locale.FRENCH, info.getDescriptionFR());
			descriptions.add(1, description);
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
