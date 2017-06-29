package de.p39.asrs.server.controller.input;

import javax.xml.bind.JAXBException;

import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.controller.input.info.CategoryInfo;
import de.p39.asrs.server.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.controller.file.FileType;
import de.p39.asrs.server.controller.file.Storage;
import de.p39.asrs.server.controller.input.info.RouteInfo;
import de.p39.asrs.server.controller.util.parser.KMLParser;
import de.p39.asrs.server.model.Route;

import java.util.ArrayList;
import java.util.Locale;

@Controller
public class RouteEditController {

	private RouteDAO dao;
	private Storage storageService;
	private Route route;
	private KMLParser parser;
	private CategoryDAO CategoryDaoInterface;


	@Autowired
	public RouteEditController(SiteDAO siteDAO, RouteDAO routeDAO, Storage storage, CategoryDAO cdao) {
		this.dao = routeDAO;
		this.storageService = storage;
		parser = new KMLParser(siteDAO);
		this.CategoryDaoInterface = cdao;

	}
	
	@GetMapping("/editroute/{id}")
	public String editRoute(@PathVariable Long id, Model model){
		this.route=this.dao.getRouteById(id);
		model.addAttribute("route", route);
		model.addAttribute("RouteInfo", new RouteInfo());
		Locale test = new Locale("german");
		RouteInfo r_info = new RouteInfo();
		r_info.setCategory(id);
		model.addAttribute("RouteInfo", r_info);
		model.addAttribute("categories", CategoryDaoInterface.getCategoriesByType("route"));
		model.addAttribute("CategoryDAO", CategoryDaoInterface);

		return "/routedit";
	}

	@GetMapping("/deleteroute/{id}")
	public String handleRouteDelete(@PathVariable("id") Long id){
		dao.deleteRoute(id);
		return "redirect:/routeoverview";
	}

	@PostMapping("/editroute/routeinfo")
	public String handleRouteInfo(@ModelAttribute RouteInfo info) {
		//this.create(info);
		return "/routeoverview";
	}
	
	@PostMapping("/editroute/kml")
	public String handleFileUploadAndCreateRoute(@RequestParam("kml") MultipartFile file,
												 RedirectAttributes redirectAttributes, Model model) {
		if(storageService.check(file, FileType.KML))
			storageService.delete(FileType.KML, file.getOriginalFilename());
		String path = storageService.store(file, FileType.KML);
		Route r = new Route();
		//TODO different method for update should be called
		try {
			parser.parseKml(path,r);
		} catch (JAXBException e) {
			// TODO Some feedback that the kml was wrong !
			e.printStackTrace();
		}
		this.route = r;
		model.addAttribute("RouteInfo", new RouteInfo());
		return "/routeform";
	}

	public RouteDAO getDao() {
		return dao;
	}

	public void setDao(RouteDAO dao) {
		this.dao = dao;
	}

	public Storage getStorageService() {
		return storageService;
	}

	public void setStorageService(Storage storageService) {
		this.storageService = storageService;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
	
	
}
