package de.p39.asrs.server.controller.db.dao.impl;

import java.util.List;

import javax.persistence.Query;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;

public class RouteDAOImpl implements RouteDAO {

	private CrudFacade cf;

	public RouteDAOImpl(CrudFacade cf) {
		this.cf = cf;
	}

	@Override
	public void instertRoute(Route r) {
		this.cf.create(r);
	}

	@Override
	public Route getRouteById(Long id) {
		return this.cf.find(id, Route.class);
	}

	@Override
	public List<Route> getAllRoutes() {
		return this.cf.findAll(Route.class);
	}

	@Override
	public void deleteRoute(Long id) {
		this.cf.delete(id, Route.class);
	}

	@Override
	public void updateRoute(Route r) {
		this.cf.update(r);
	}

	@Override
	public List<Route> getRoutesByName(String s) {
		Query q = this.cf.createQuery("SELECT e FROM " + Route.class.getName() + " e WHERE name = :name");
		q.setParameter("name", s);
		return (List) q.getResultList();
	}

	@Override
	public void addSite(Route r, Site s) {
		r.addSite(s);
		this.cf.update(r);
	}

	@Override
	public List<Site> getSites(Route r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCoordinate(Route r, Coordinate c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Coordinate> getCoordinates(Route r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGPX(Route r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGPX(Route r, String gpx) {
		// TODO Auto-generated method stub
		
	}

}
