package de.p39.asrs.server.controller.db.dao;

import java.util.List;

import de.p39.asrs.server.model.Route;

public interface RouteDAO {
	
	public void instertRoute(Route r);
	
	public Route getRouteById(Long id);
	
	public List<Route> getAllRoutes();
	
	public void deleteRoute(Long id);
	
	public void updateRoute(Route r);
	
	public List<Route> getRoutesByName(String s);
	

}
