package de.p39.asrs.server.controller.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;

public class RouteDAO{

	private CrudFacade cf;

	public RouteDAO(CrudFacade cf) {
		this.cf = cf;
	}

	public Route instertRoute(Route r) {
		/*for(LocaleDescription s :r.getDescriptions()){
			this.cf.create(s);
		}
		for(LocaleName s:r.getNames()){
			this.cf.create(s);
		}*/
		return this.cf.create(r);
	}

	public Route getRouteById(Long id) {
		return this.cf.find(id, Route.class);
	}
	

	public List<Route> getAllRoutes() {
				return this.cf.findAll(Route.class);
	}

	public List<Route> getAllRoutesCompleted() {

		Query q = this.cf.createQuery("SELECT e FROM " + Route.class.getName() + " e WHERE completed = true");
		return (List<Route>) q.getResultList();
	}


	public void deleteRoute(Long id) {
		this.cf.delete(id, Route.class);
	}

	public Route updateRoute(Route r) {
		return this.cf.update(r);
	}

	public void addSite(Route r, Site s) {
		r.addSite(s);
		this.cf.update(r);
	}

	public List<Site> getSites(Route r) {
		Set<Site> set =r.getSites();
		List<Site> res = new ArrayList<>();
		res.addAll(set);
		return res;
	}

	public void addCoordinate(Route r, Coordinate c) {
		r.addCoordinate(c);
	}

	public List<Coordinate> getCoordinates(Route r) {
		return r.getCoordinates();
	}

	public String getGPX(Route r) {
		// TODO not needed
		return null;
	}

	public void setGPX(Route r, String gpx) {
		// TODO not needed
		
	}

}
