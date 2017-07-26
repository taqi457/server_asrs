package de.p39.asrs.db;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by bjornmohr on 25.07.17.
 */
public class MockRouteDAO extends RouteDAO{
    private CrudFacade cf;
    private Route route;

    public MockRouteDAO(CrudFacade cf) {
        super(cf);
        this.cf = cf;
        route = new Route();
    }

    public Route instertRoute(Route r) {
		/*for(LocaleDescription s :r.getDescriptions()){
			this.cf.create(s);
		}
		for(LocaleName s:r.getNames()){
			this.cf.create(s);
		}*/
		route = r;
		route.setId((long)1);
        return route;
    }

    public Route getRouteById(Long id) {
        return route;
    }


    public List<Route> getAllRoutes() {
        List list = new ArrayList();
        list.add(route);
        return list;
    }

    public List<Route> getAllRoutesCompleted() {
        return getAllRoutes();
    }


    public void deleteRoute(Long id) {
        return;
    }

    public Route updateRoute(Route r) {

        route = r;
        return route;
    }

    public void addSite(Route r, Site s) {
        route = r;
        route.addSite(s);
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
