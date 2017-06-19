package de.p39.asrs.util.reader;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.p39.asrs.server.controller.util.reader.KMLReader;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;

public class KMLReaderTest {

	@Test
	public void test() {
		Route r = null;
		try {
			r = new KMLReader().parseKml("src/main/resources/kml/saarbrucken_tour.kml");
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("An error occured when parsing kml");
		}
		
		assertNotNull("Returned route was null",r);
		//assertEquals("Auf den Spuren der Römer: Radtour Saarbrücken",r.getNameDE());
		
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
		Site site = new Site("Museum für Vor- und Frühgeschichte, Schloßstraße, Saarbrücken");
		site.setCoordinate(new Coordinate(49.2309227,6.9910729));
		assertTrue(sites.contains(site));
		site = new Site("Stiftskirche St. Arnual, St.Arnualer Markt, Saarbrücken");
		site.setCoordinate(new Coordinate(49.2177141,7.0178181));
		assertTrue(sites.contains(site));
		site = new Site("An der Römerbrücke 1, Saarbrücken");
		site.setCoordinate(new Coordinate(49.2225925,7.02466));
		assertTrue(sites.contains(site));
		site = new Site("Römerstadt 1, Saarbrücken");
		site.setCoordinate(new Coordinate(49.2247862,7.0264679));
		assertTrue(sites.contains(site));
		site = new Site("Mithraskapelle am Halberg, Brebacher Landstraße, Saarbrücken");
		site.setCoordinate(new Coordinate(49.2225203,7.0283524));
		assertTrue(sites.contains(site));
	}

}
