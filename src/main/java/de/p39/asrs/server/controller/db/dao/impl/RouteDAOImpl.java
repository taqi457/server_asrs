package de.p39.asrs.server.controller.db.dao.impl;

import java.util.List;

import javax.persistence.Query;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.model.Audio;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Picture;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;
import de.p39.asrs.server.model.Text;
import de.p39.asrs.server.model.Video;

public class RouteDAOImpl implements RouteDAO {

	private CrudFacade cf;

	public RouteDAOImpl(CrudFacade cf) {
		this.cf = cf;
	}

	@Override
	public void instertRoute(Route r) {
		/*for(Coordinate c : r.getCoordinates()){
			this.cf.create(c);
		}
		for(Site s : r.getSites()){
			this.cf.create(s.getCoordinate());
			this.cf.create(s.getCategory());
			for(Audio a : s.getAudios()){
				this.cf.create(a);
			}
			for(Video v : s.getVideos()){
				this.cf.create(v);
			}
			for(Text t : s.getTexts()){
				this.cf.create(t);
			}
			for(Picture p: s.getPictures()){
				this.cf.create(p);
			}
			this.cf.create(s);
		}*/
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

}
