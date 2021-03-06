package de.p39.asrs.util.reader;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.p39.asrs.server.controller.util.parser.KMLParser;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.LocaleName;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;

public class KMLParserTest {

    @Test
    public void testSbTour() {
        Route r = new Route();
        try {
            new KMLParser().parseKml("src/main/resources/kml_testfiles/saarbrucken_tour.kml", r);
        } catch (JAXBException e) {
            e.printStackTrace();
            fail("An error occured when parsing kml");
        }

        assertNotNull("Returned route was null", r);
        assertEquals("Auf den Spuren der Römer: Radtour Saarbrücken", r.getNameByLocale(Locale.GERMAN));

        // check first and last koordinate
        List<Coordinate> coordList = r.getCoordinates();
        assertNotNull(coordList);
        assertFalse(coordList.isEmpty());
        Coordinate c = coordList.get(0);
        assertEquals(6.99107, c.getLongitude(), 0.001);
        assertEquals(49.23092, c.getLatitude(), 0.001);
        c = coordList.get(coordList.size() - 1);
        assertEquals(6.99107, c.getLongitude(), 0.001);
        assertEquals(49.23092, c.getLatitude(), 0.001);

        // check sites
        Set<Site> sites = r.getSites();
        assertNotNull(sites);
        assertFalse(sites.isEmpty());
        Site site = new Site();
        LocaleName ln = new LocaleName();
        ln.setLocale(Locale.GERMAN);
        ln.setString("Museum für Vor- und Frühgeschichte, Schloßstraße, Saarbrücken");
        site.addLocaleName(ln);
        site.setCoordinate(new Coordinate(49.2309227, 6.9910729));
        assertTrue(sites.contains(site));
        site = new Site();
        ln = new LocaleName();
        ln.setLocale(Locale.GERMAN);
        ln.setString("Stiftskirche St. Arnual, St.Arnualer Markt, Saarbrücken");
        site.addLocaleName(ln);
        site.setCoordinate(new Coordinate(49.2177141, 7.0178181));
        assertTrue(sites.contains(site));
        site = new Site();
        ln = new LocaleName();
        ln.setLocale(Locale.GERMAN);
        ln.setString("An der Römerbrücke 1, Saarbrücken");
        site.addLocaleName(ln);
        site.setCoordinate(new Coordinate(49.2225925, 7.02466));
        assertTrue(sites.contains(site));
        site = new Site();
        ln = new LocaleName();
        ln.setLocale(Locale.GERMAN);
        ln.setString("Römerstadt 1, Saarbrücken");
        site.addLocaleName(ln);
        site.setCoordinate(new Coordinate(49.2247862, 7.0264679));
        assertTrue(sites.contains(site));
        site = new Site();
        ln = new LocaleName();
        ln.setLocale(Locale.GERMAN);
        ln.setString("Mithraskapelle am Halberg, Brebacher Landstraße, Saarbrücken");
        site.addLocaleName(ln);
        site.setCoordinate(new Coordinate(49.2225203, 7.0283524));
        assertTrue(sites.contains(site));

        // distance
        // assertEquals(30.7,(double)r.getDistanceInKilometers());
        assertEquals(123, (int) r.getDurationByBike());
        assertTrue(368 <= (int) r.getDurationByFoot() && 369 >= (int) r.getDurationByFoot());
    }

    @Test
    public void testNullInput() {
        try {
            new KMLParser().parseKml(null, null);
        } catch (JAXBException e) {

        }
    }

    @Test
    public void testEmptyPath() {
        Route r = new Route();
        try {
            new KMLParser().parseKml("", r);
        } catch (JAXBException e) {

        }
    }

    @Test
    public void testBrokenKML() {
        Route r = new Route();
        try {
            new KMLParser().parseKml("src/main/resources/kml_testfiles/broken1.kml", r);
        } catch (JAXBException e) {

        }
    }

    @Test
    public void testBrokenKML2() {
        Route r = new Route();
        try {
            new KMLParser().parseKml("src/main/resources/kml_testfiles/broken2.kml", r);
        } catch (JAXBException e) {

        }
    }

    @Test
    public void testBrokenKML3() {
        Route r = new Route();
        try {
            new KMLParser().parseKml("src/main/resources/kml_testfiles/broken3.kml", r);
        } catch (JAXBException e) {

        }
    }

    @Test
    public void testBrokenKML4() {
        Route r = new Route();
        try {
            new KMLParser().parseKml("src/main/resources/kml_testfiles/broken4.kml", r);
        } catch (JAXBException e) {

        }
    }

    @Test
    public void testBrokenKML5() {
        Route r = new Route();
        try {
            new KMLParser().parseKml("src/main/resources/kml_testfiles/broken5.kml", r);
        } catch (JAXBException e) {

        }
    }

    @Test
    public void testBrokenKML6() {
        Route r = new Route();
        try {
            new KMLParser().parseKml("src/main/resources/kml_testfiles/broken6.kml", r);
        } catch (JAXBException e) {

        }
    }

    @Test
    public void testGoodKml() {
        Route r = new Route();
        try {
            new KMLParser().parseKml("src/main/resources/kml_testfiles/Another_tour.kml", r);
        } catch (JAXBException e) {
            e.printStackTrace();
            fail("An error occured when parsing kml");
        }
    }
}
