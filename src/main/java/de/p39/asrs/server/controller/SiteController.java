package de.p39.asrs.server.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.p39.asrs.server.controller.db.JPACrudService;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.controller.db.dao.impl.SiteDAOImpl;
import de.p39.asrs.server.controller.exceptions.BadRequestException;
import de.p39.asrs.server.controller.exceptions.NotFoundExecption;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Site;

@RestController
@RequestMapping("/site")
public class SiteController {

	private final SiteDAO daoInterface = new SiteDAOImpl(new JPACrudService());

	@RequestMapping(value = "", method = RequestMethod.GET)
    public List<Site> routeAll(@RequestParam(value = "kind") String kind){
		if(kind != "all"){
    		throw new BadRequestException("Specifiy an ID by /site/{id} or all by /site?kind=all");
    	}
    	return daoInterface.getAllSites();
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Site siteId(@PathVariable Long id) {
		Site res = daoInterface.getSiteById(id);
    	if(res == null)
    		throw new NotFoundExecption("The siteId you specified was not in the data set");
    	return res;
	}

	@RequestMapping(value="/gps", method=RequestMethod.GET)
    public List<Site> routeGPS(@RequestParam Map<String,Double> requestParam) {
    	Double lat = requestParam.get("lat");
    	Double lon = requestParam.get("lon");
    	Double rad = requestParam.get("radius");
    	if(lat == null || lon == null || rad == null)
    		throw new BadRequestException("Specify longitude (lon), latitide (lat) and radius in meters");
    	Coordinate coord = new Coordinate(lat,lon);
    
    	List<Site> result = new LinkedList<>();
    	List<Site> allSites = daoInterface.getAllSites();
    	
    	for (Site site : allSites){
    		if(site.calculateDist(coord) <= rad)
    			result.add(site);
    	}
    	return result;
    } 
}