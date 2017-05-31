package de.p39.asrs.server.control.db;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.Test;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.JPACrudService;
import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.controller.db.dao.impl.RouteDAOImpl;
import de.p39.asrs.server.model.Audio;
import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Picture;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;
import de.p39.asrs.server.model.Text;
import de.p39.asrs.server.model.Video;

public class DBTest {

	
	@Test
	public void createDbTest(){
		CrudFacade cf = new JPACrudService("server");
		assertTrue(cf.count(Route.class)==0);
	}
	
	@Test
	public void routeCreationThenFindingTest(){
		CrudFacade cf = new JPACrudService("server");
		RouteDAO dao= new RouteDAOImpl(cf);
		Route r = this.createDummyData();
		dao.instertRoute(r);
	}
	
	
	
	private Route createDummyData(){
		Audio a1 = new Audio("test_audio");
		a1.setLocale(Locale.GERMAN);
		Video v1 = new Video("test_video");
		v1.setLocale(Locale.GERMAN);
		Text t1 = new Text("test_text");
		t1.setLocale(Locale.GERMAN);
		Picture p1 = new Picture("test_pic");
		p1.setLocale(Locale.GERMAN);
		
		Category c1 = new Category("interesting_stuff");
		Coordinate co1 = new Coordinate("icoffee");
		co1.setLatitude(49.257469);
		co1.setLongitude(7.045455);		
		
		Site s1 = new Site("iCoffee");
		s1.setCategory(c1);
		s1.setCoordinate(co1);
		s1.addMedium(a1);
		s1.addMedium(v1);
		s1.addMedium(t1);
		s1.addMedium(p1);
		
		Audio a2 = new Audio("test_audio");
		a2.setLocale(Locale.GERMAN);
		Video v2 = new Video( "test_video");
		v2.setLocale(Locale.GERMAN);
		Text t2 = new Text("test_text");
		t2.setLocale(Locale.GERMAN);
		Picture p2 = new Picture("test_pic");
		p2.setLocale(Locale.GERMAN);
		
		Category c2 = new Category("interesting_stuff");
		Coordinate co2 = new Coordinate("mensa");
		co2.setLatitude(49.257364);
		co2.setLongitude(7.043245);		
		
		Site s2 = new Site("Mensa");
		s2.setCategory(c2);
		s2.setCoordinate(co2);
		s2.addMedium(a2);
		s2.addMedium(v2);
		s2.addMedium(t2);
		s2.addMedium(p2);
		
		Route r1 = new Route("iCoffe to Mensa");
		Set<Coordinate> coordinates = new HashSet<>();
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
