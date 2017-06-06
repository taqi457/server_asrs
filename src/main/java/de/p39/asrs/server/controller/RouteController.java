package de.p39.asrs.server.controller;

import java.util.LinkedList;
import java.util.List;

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
import de.p39.asrs.server.model.Audio;
import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Picture;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;
import de.p39.asrs.server.model.Text;
import de.p39.asrs.server.model.Video;

@RestController
@RequestMapping(value="/route")
public class RouteController {
	
    private final RouteDAO routeif = new RouteDAOImpl(new JPACrudService());
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public List<Route> routeAll(@RequestParam(value = "kind") Long kind){
    	return routeif.getAllRoutes();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Route routeId(@PathVariable String id) {
    	long routeid = 0;
    	try{
    		routeid = Long.parseLong(id);
    	} catch (NumberFormatException e) {
    		throw new BadRequestException();
    	}
    	Route res = routeif.getRouteById(routeid);
    	if(res == null)
    		throw new NotFoundExecption();
    	return res;
    }
        
    @RequestMapping(value="/gps", method=RequestMethod.GET)
    public List<Route> routeGPS(@RequestParam(value = "coord") String coord) {
    	List<Route> result = new LinkedList<>();
    	List<Route> allRoutes = routeif.getAllRoutes();
    	for (Route r : allRoutes){
    		//calculate Distance
    	}
    	return result;
    } 
}
