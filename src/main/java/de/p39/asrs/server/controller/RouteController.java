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
import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.controller.db.dao.impl.RouteDAOImpl;
import de.p39.asrs.server.controller.exceptions.BadRequestException;
import de.p39.asrs.server.controller.exceptions.NotFoundExecption;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Route;

@RestController
@RequestMapping(value="/route")
public class RouteController {
	
    private final RouteDAO daoInterface = new RouteDAOImpl(new JPACrudService());
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public List<Route> routeAll(@RequestParam(value = "kind") String kind){
    	if(kind != "all"){
    		throw new BadRequestException("Specifiy an ID by /route/{id} or all by /route?kind=all");
    	}
    	return daoInterface.getAllRoutes();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Route routeId(@PathVariable Long id) {
    	Route res = daoInterface.getRouteById(id);
    	if(res == null)
    		throw new NotFoundExecption("The routeId you specified was not in the data set");
    	return res;
    }
        
    @RequestMapping(value="/gps", method=RequestMethod.GET)
    public List<Route> routeGPS(@RequestParam Map<String,Double> requestParam) {
    	Double lat = requestParam.get("lat");
    	Double lon = requestParam.get("lon");
    	Double rad = requestParam.get("radius");
    	if(lat == null || lon == null || rad == null)
    		throw new BadRequestException("Specify longitude (lon), latitide (lat) and radius in meters");
    	Coordinate coord = new Coordinate(lat,lon);
    
    	List<Route> result = new LinkedList<>();
    	List<Route> allRoutes = daoInterface.getAllRoutes();
    	
    	for (Route route : allRoutes){
    		if(route.calculateDist(coord) <= rad)
    			result.add(route);
    	}
    	return result;
    } 
}
