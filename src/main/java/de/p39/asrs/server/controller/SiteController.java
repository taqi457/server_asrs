package de.p39.asrs.server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.p39.asrs.server.controller.db.JPACrudService;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.controller.db.dao.impl.SiteDAOImpl;
import de.p39.asrs.server.model.Site;

@RestController
@RequestMapping("/site")
public class SiteController {

	private final SiteDAO siteif = new SiteDAOImpl(new JPACrudService());

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Site siteId(@PathVariable Long id) {
		return siteif.getSiteById(id);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
    public List<Site> routeAll(@RequestParam(value = "kind") String kind){
    	return siteif.getAllSites();
    }

	@RequestMapping(value = "/gps", method = RequestMethod.GET)
    public Site siteGps(@RequestParam(value = "coord") String coord){
    	return null;
    }
}