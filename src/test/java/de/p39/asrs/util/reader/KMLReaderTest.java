package de.p39.asrs.util.reader;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import de.p39.asrs.server.controller.util.reader.KMLReader;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;

public class KMLReaderTest {

	@Test
	public void test() {
		Route r = new KMLReader("src/main/resources/kml/saarbrucken_tour.kml").parseKml();
		assertNotNull("Returned route was null",r);
		assertEquals("Auf den Spuren der Römer: Radtour Saarbrücken",r.getName());
		
		//check first and last koordinate
		List<Coordinate> coordList = r.getCoordinates();
		assertNotNull(coordList);
		assertFalse(coordList.isEmpty());
		Coordinate c = coordList.get(0);
		assertEquals(6.99107,c.getLongitude(),0.001);
		assertEquals(49.23092, c.getLatitude(),0.001);
		c = coordList.get(coordList.size()-1);
		assertEquals(6.99107,c.getLongitude(),0.001);
		assertEquals(49.23092, c.getLatitude(),0.001);
		
		//check sites
		Set<Site> sites = r.getSites();
		assertNotNull(sites);
		assertFalse(sites.isEmpty());
		/*Site site = new Site("Museum für Vor- und Frühgeschichte, Schloßstraße, Saarbrücken");
		site.setCoordinate(new Coordinate(6.9910729,49.2309227));
		assertTrue(sites.contains(site));
		site = new Site("Stiftskirche St. Arnual, St.Arnualer Markt, Saarbrücken");
		site.setCoordinate(new Coordinate(7.0178181,49.2177141));
		assertTrue(sites.contains(site));
		site = new Site("An der Römerbrücke 1, Saarbrücken");
		site.setCoordinate(new Coordinate(7.02466,49.2225925));
		assertTrue(sites.contains(site));
		site = new Site("Römerstadt 1, Saarbrücken");
		site.setCoordinate(new Coordinate(7.0264679,49.2247862));
		assertTrue(sites.contains(site));
		site = new Site("Mithraskapelle am Halberg, Brebacher Landstraße, Saarbrücken");
		site.setCoordinate(new Coordinate(7.0283524,49.2225203));
		assertTrue(sites.contains(site));*/
	}

}
