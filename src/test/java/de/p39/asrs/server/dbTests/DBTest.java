package de.p39.asrs.server.dbTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.JPACrudService;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.LocaleDescription;
import de.p39.asrs.server.model.LocaleName;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;
import de.p39.asrs.server.model.SiteCategory;
import de.p39.asrs.server.model.media.Audio;
import de.p39.asrs.server.model.media.Picture;

/**
 * this pollutes your database if you don't change update to create in persistence.xml
 * therefore tests are ignored 
 * @author adrianrebmann
 *
 */
public class DBTest {

	
	@Test
	@Ignore
	public void createDbTest(){
		CrudFacade cf = new JPACrudService("server");
		assertTrue(cf.count(Route.class)>=0);
	}
	
	@Test
	@Ignore
	public void routeCreationThenFindingTest(){
		CrudFacade cf = new JPACrudService("server");
		RouteDAO dao= new RouteDAO(cf);
		Route r = this.createDummyData();
		dao.instertRoute(r);
		List<Route> routes = dao.getAllRoutes();
		assertTrue(routes.size()>=1);
		r = routes.get(0);
		assertTrue(r.getSites().size()==2);
		for(Site s : r.getSites()){
			assertTrue(s.getPictures().size()==1);
		}
	}
	
	@Test
	public void routeCreationThenDeletingPictureOfSite(){
		CrudFacade cf = new JPACrudService("server");
		RouteDAO dao= new RouteDAO(cf);
		MediumDAO mdao = new MediumDAO(cf);
		Route r = this.createDummyData();
		dao.instertRoute(r);
		List<Route> routes = dao.getAllRoutes();
		assertTrue(routes.size()>=1);
		r = routes.get(0);
		assertTrue(r.getSites().size()==2);
		for(Site s : r.getSites()){
			assertTrue(s.getPictures().size()==1);
		}
	}
	
	@Test
	@Ignore
	public void localeTest(){
		CrudFacade cf = new JPACrudService("server");
		cf.create(new LocaleName(Locale.GERMAN, "test"));
		List<LocaleName> list = cf.findAll(LocaleName.class);
		assertTrue(list.get(0).getLocale().equals(Locale.GERMAN));
	}
	
	@Test
	@Ignore
	public void routeCreationThenFindingThenUpdateingTest(){
		CrudFacade cf = new JPACrudService("server");
		RouteDAO dao= new RouteDAO(cf);
		SiteDAO doa = new SiteDAO(cf);
		Route r = this.createDummyData();
		dao.instertRoute(r);
		List<Site> sites = doa.getSitesByName("test_german");
		assertTrue(sites.size()>=1);
		sites = doa.getSitesByName("test_German");
		assertTrue(sites.size()==0);
		List<Route> routes = dao.getAllRoutes();
		assertTrue(routes.size()>=1);
		r = routes.get(0);
		dao.updateRoute(r);
	}
	
	
	private Route createDummyData(){
		Picture p1 = new Picture();
	
		Coordinate co1 = new Coordinate();
		co1.setLatitude(49.257469);
		co1.setLongitude(7.045455);		
		
		Site s1 = new Site();
		Set<SiteCategory> cats = new HashSet<>();
		cats.add(SiteCategory.MANSION);
		s1.setCoordinate(co1);
		s1.addPicture(p1);
		s1.setCategories(cats);
		Picture p2 = new Picture();
		Coordinate co2 = new Coordinate();
		co2.setLatitude(49.257364);
		co2.setLongitude(7.043245);		
		
		Site s2 = new Site();
		s2.setCoordinate(co2);
		s2.addPicture(p2);
		LocaleName n1 = new LocaleName(Locale.GERMAN, "test_german");
		LocaleName n2 = new LocaleName(Locale.FRENCH, "test_french");
		LocaleName n3 = new LocaleName(Locale.ENGLISH, "test_english");
		s2.addLocaleName(n1);
		s2.addLocaleName(n2);
		s2.addLocaleName(n3);
		Route r1 = new Route();
		LocaleName s = new LocaleName(Locale.GERMAN, "test");
		List<LocaleName> names = new ArrayList<>();
		names.add(s);
		r1.setNames(names);
		List<LocaleDescription> descriptions = new ArrayList<>();
		descriptions.add(new LocaleDescription(Locale.GERMAN,"this is the best route ever guys"));
		r1.setDescriptions(descriptions);
		List<Coordinate> coordinates = new LinkedList<>();
		coordinates.add(co1);
		coordinates.add(co2);
		r1.setCoordinates(coordinates);
		Set<Site> sites = new HashSet<>();
		sites.add(s1);
		sites.add(s2);
		r1.setSites(sites);
		return r1;
	}

}
