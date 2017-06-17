package de.p39.asrs.server.controller.db.dao;

import java.util.List;

import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;

public interface RouteDAO {
	
	public void instertRoute(Route r);
	
	public Route getRouteById(Long id);
	
	public List<Route> getRouteByPath(String path);
	
	public List<Route> getAllRoutes();
	
	public void deleteRoute(Long id);
	
	public void updateRoute(Route r);
	
	public void addSite(Route r, Site s);
	
	public List<Site> getSites(Route r);
	
	public void addCoordinate(Route r,Coordinate c);
	
	public List<Coordinate> getCoordinates(Route r);
	
	public String getGPX(Route r);
	
	public void setGPX(Route r, String gpx);

}
