package de.p39.asrs.server.controller.db.dao;

import java.util.Set;

import de.p39.asrs.server.model.Route;

public interface RouteDAO {
	
	public void instertRoute(Route r);
	
	public Route getRouteById(Long id);
	
	public Set<Route> getAllRoutes();
	
	public void deleteRoute(Route r);
	
	public void updateRoute(Route r);

}
