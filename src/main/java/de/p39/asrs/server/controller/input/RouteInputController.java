package de.p39.asrs.server.controller.input;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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

/**
 * 
 * @author adrianrebmann
 *
 */
@Controller
public class RouteInputController {

	private RouteDAO dao;

	private String germanName;
	private String englishName;
	private String frenchName;

	private String germanDescription;
	private String englishDescription;
	private String frenchDescription;

	private List<Site> selectedSites;

	private Category selectedCategory;

	private Part kmlDocument;
	private Storage storageService;

	@Autowired
	public RouteInputController(RouteDAO dao, Storage storage) {
		this.dao = dao;
		this.storageService = storage;
	}
	
	@GetMapping("/kml/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(FileType.KML,filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/kml")
	public String handleFileUploadAndCreateRoute(@RequestParam("kml") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		String path = storageService.store(file, FileType.KML);
		KMLReader kmlreader = new KMLReader(path);
		Route r = kmlreader.parseKml();
		List<Route> exists= this.dao.getRouteByPath(path);
		if(!exists.isEmpty()){
			Route existing = exists.get(0);
			this.dao.deleteRoute(existing.getId());
		}
		if (r != null){
			this.addNamesAndDescriptions(r);			
			r.setPathToKml(path);
			this.dao.instertRoute(r);
		}
		redirectAttributes.addFlashAttribute("message",
				"Route successfully created with " + file.getOriginalFilename() + "!");

		return "redirect:/uploadkml";
	}
	
	@ExceptionHandler(StorageException.class)
	public ResponseEntity handleStorageFileNotFound(StorageException exc) {
		return ResponseEntity.notFound().build();
	}

	private Route addNamesAndDescriptions(Route r) {
		if (this.englishName != null) {
			LocaleName name = new LocaleName(Locale.GERMAN, this.germanName);
			r.addLocaleName(name);
		}
		if (this.germanName != null) {
			LocaleName name = new LocaleName(Locale.ENGLISH, this.englishName);
			r.addLocaleName(name);
		}
		if (this.frenchName != null) {
			LocaleName name = new LocaleName(Locale.FRENCH, this.frenchName);
			r.addLocaleName(name);
		}
		if (this.englishDescription != null) {
			LocaleDescription description = new LocaleDescription(Locale.GERMAN, this.germanDescription);
			r.addLocaleDescription(description);
		}
		if (this.germanDescription != null) {
			LocaleDescription description = new LocaleDescription(Locale.ENGLISH, this.englishDescription);
			r.addLocaleDescription(description);
		}
		if (this.frenchDescription != null) {
			LocaleDescription description = new LocaleDescription(Locale.FRENCH, this.frenchDescription);
			r.addLocaleDescription(description);
		}
		return r;
	}
	
	@ModelAttribute("allRoutes")
	public List<Route> allRoutes(){
		System.out.println("all routes called");
		return this.dao.getAllRoutes();
	}

	/**
	 * @return the germanName
	 */
	public String getGermanName() {
		return germanName;
	}

	/**
	 * @param germanName
	 *            the germanName to set
	 */
	public void setGermanName(String germanName) {
		this.germanName = germanName;
	}

	/**
	 * @return the englishName
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * @param englishName
	 *            the englishName to set
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	/**
	 * @return the frenchName
	 */
	public String getFrenchName() {
		return frenchName;
	}

	/**
	 * @param frenchName
	 *            the frenchName to set
	 */
	public void setFrenchName(String frenchName) {
		this.frenchName = frenchName;
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

	public String getGermanDescription() {
		return germanDescription;
	}

	public void setGermanDescription(String germanDescription) {
		this.germanDescription = germanDescription;
	}

	public String getEnglishDescription() {
		return englishDescription;
	}

	public void setEnglishDescription(String englishDescription) {
		this.englishDescription = englishDescription;
	}

	public String getFrenchDescription() {
		return frenchDescription;
	}

	public void setFrenchDescription(String frenchDescription) {
		this.frenchDescription = frenchDescription;
	}

	/**
	 * @return the kmlDocument
	 */
	public Part getKmlDocument() {
		return kmlDocument;
	}

	/**
	 * @param kmlDocument
	 *            the kmlDocument to set
	 */
	public void setKmlDocument(Part kmlDocument) {
		this.kmlDocument = kmlDocument;
	}

}
