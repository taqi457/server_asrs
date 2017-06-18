package de.p39.asrs.server.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.controller.exceptions.BadRequestException;
import de.p39.asrs.server.controller.exceptions.NotFoundExecption;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Site;

@RestController
@RequestMapping("/site")
public class SiteController {

	@Autowired
	private SiteDAO daoInterface;

	@RequestMapping(value = "", method = RequestMethod.GET)
    public List<Site> siteAll(@RequestParam(value = "kind") String kind){
		if(!kind.equals("all")){
    		throw new BadRequestException("Specifiy an ID by /site/{id} or all by /site?kind=all");
    	}
    	return daoInterface.getAllSites();
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Site siteById(@PathVariable Long id) {
		Site res = daoInterface.getSiteById(id);
    	if(res == null)
    		throw new NotFoundExecption("The siteId you specified was not in the data set");
    	return res;
	}

	@RequestMapping(value="/gps", method=RequestMethod.GET)
    public List<Site> siteByGps(@RequestParam Map<String,String> requestParam) {
		Double lat, lon, rad;
		try {
			lat = Double.parseDouble(requestParam.get("lat"));
			lon = Double.parseDouble(requestParam.get("lon"));
			rad = Double.parseDouble(requestParam.get("radius"));
		} catch (NullPointerException | NumberFormatException e) {
			throw new BadRequestException(
					"Provide latitude, longitude and radius e.g. /gps?lat=1.0&lon=1.0&radius=10.0");
		}
		Coordinate coord = new Coordinate(lat, lon);

		List<Site> result = new LinkedList<>();
		List<Site> allSites = daoInterface.getAllSites();

		for (Site site : allSites) {
			if (site.calculateDist(coord) <= rad)
				result.add(site);
		}
		return result;
    } 
}