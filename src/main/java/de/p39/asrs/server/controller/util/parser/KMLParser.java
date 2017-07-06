package de.p39.asrs.server.controller.util.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.Transient;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.LocaleName;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;
import de.p39.asrs.server.controller.util.parser.kml.AbstractFeatureType;
import de.p39.asrs.server.controller.util.parser.kml.DocumentType;
import de.p39.asrs.server.controller.util.parser.kml.FolderType;
import de.p39.asrs.server.controller.util.parser.kml.KmlType;
import de.p39.asrs.server.controller.util.parser.kml.LineStringType;
import de.p39.asrs.server.controller.util.parser.kml.PlacemarkType;
import de.p39.asrs.server.controller.util.parser.kml.PointType;

/**
 * @author robin
 *         <p>
 *         The KMLReader parses a route from a KML file including locations.
 *         If route is not empty:
 *         Coordinate List is flushed
 *         Sites updated if existing
 */
public class KMLParser {

    private static final String errorMsg = "Error in Kml content: ";
    private static final float speedByFoot = 83.3333333f; // in meters per minute (= 5 km/h)
    private static final float speedByBike = 250.0f; // in meters per minute (= 15 km/h)

    private Unmarshaller unmarshaller;
    private SiteDAO dao;

    public KMLParser(SiteDAO dao) {
        this.dao = dao;
        init();
    }

    private void init() {
        try {
            JAXBContext jaxbcontext = JAXBContext.newInstance("de.p39.asrs.server.controller.util.parser.kml");
            unmarshaller = jaxbcontext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param path The path to the KML file
     * @return The parsed Route from the KML
     * @throws JAXBException when the syntax of the kml did not match
     */
    public void parseKml(String path, Route route) throws JAXBException {
        DocumentType document = openDocument(path);

        // set name
        List<LocaleName> names = route.getNames();
        if (names.isEmpty())
            names.add(new LocaleName(Locale.GERMAN, document.getName()));
        else
            for (LocaleName name : names) {
                if (name.getLocale().equals(Locale.GERMAN) &&
                        !name.getString().equals(document.getName()))
                    name.setString(document.getName());
            }

        List<JAXBElement<? extends AbstractFeatureType>> features =
                document.getAbstractFeatureGroup();
        if (features == null || features.isEmpty())
            throw new JAXBException(errorMsg + "Missing features in document");

        parseFolder((FolderType) features.get(0).getValue(), route);

        //calculate other data from coords
        calculateLength(route);
        calculateDurations(route);
    }

    private DocumentType openDocument(String path) throws JAXBException {
        @SuppressWarnings("unchecked")
        JAXBElement<KmlType> jaxbelement =
                (JAXBElement<KmlType>) unmarshaller.unmarshal(new File(path));
        KmlType kml = jaxbelement.getValue();

        if (kml == null)
            throw new JAXBException(errorMsg +
                    "Parser could not recognize kml format");
        DocumentType document =
                (DocumentType) kml.getAbstractFeatureGroup().getValue();
        if (document == null)
            throw new JAXBException(errorMsg + "Missing document value");
        return document;
    }

    private void parseFolder(FolderType folder, Route result)
            throws JAXBException {
        if (folder == null)
            throw new JAXBException(errorMsg +
                    "Missing folder in document's features");
        List<JAXBElement<? extends AbstractFeatureType>> placemarks =
                folder.getAbstractFeatureGroup();
        if (placemarks == null || placemarks.isEmpty())
            throw new JAXBException(errorMsg + "Missing placemark list in folder");

        PlacemarkType placemark = (PlacemarkType) placemarks.get(0).getValue();
        if (placemark == null)
            throw new JAXBException(errorMsg + "Missing placemark in placemark list");

        // LineString contains all coordinates
        LineStringType lineString =
                (LineStringType) placemark.getAbstractGeometryGroup().getValue();
        if (lineString == null)
            throw new JAXBException(errorMsg +
                    "First placemark did not contain a LineString");
        List<String> coordinates = lineString.getCoordinates();
        // flush coordinates list
        if (!result.getCoordinates().isEmpty())
            result.setCoordinates(new ArrayList<>(coordinates.size()));
        for (String coordinate : coordinates)
            insertCoordinate(result, coordinate);

        // Points contain the sites
        for (int i = 1; i < placemarks.size(); i++) {
            placemark = (PlacemarkType) placemarks.get(i).getValue();
            if (placemark == null)
                throw new JAXBException(errorMsg +
                        "Missing placemark in placemark list");
            insertSite(result, placemark);
        }
    }

    private void insertSite(Route result, PlacemarkType placemark)
            throws JAXBException {
        List<Site> sites = null;
        Site site = null;
        if (dao != null)
            sites = dao.getSitesByName(placemark.getName());
        if (sites == null || sites.isEmpty()) {
            site = new Site();
            LocaleName ln = new LocaleName();
            ln.setLocale(Locale.GERMAN);
            ln.setString(placemark.getName());
            site.addLocaleName(ln);
        } else
            site = sites.get(0);
        PointType point =
                (PointType) placemark.getAbstractGeometryGroup().getValue();
        if (point == null)
            throw new JAXBException(errorMsg + "Placemark did not contain a Point");
        List<String> coord = point.getCoordinates();
        if (coord == null || coord.isEmpty())
            throw new JAXBException(errorMsg +
                    "Coordinate list in Point was missing or empty");
        insertCoordinate(site, coord.get(0));
        result.addSite(site);
    }

    private void insertCoordinate(Site site, String coordString)
            throws JAXBException {
        String[] splited = coordString.split(",");
        if (splited.length != 3)
            throw new JAXBException(
                    errorMsg +
                            "Format of a coordiante was wrong. Expected longitude,latitude,elevation as float.");
        double lon, lat;
        try {
            lon = Double.parseDouble(splited[0]);
            lat = Double.parseDouble(splited[1]);
        } catch (NumberFormatException e) {
            throw new JAXBException(errorMsg + e.getMessage());
        }
        site.setCoordinate(new Coordinate(lat, lon));
    }

    private void insertCoordinate(Route result, String coordString)
            throws JAXBException {
        String[] splited = coordString.split(",");
        if (splited.length != 3)
            throw new JAXBException(
                    errorMsg +
                            "Format of a coordiante was wrong. Expected longitude,latitude,elevation as float.");
        double lon, lat;
        try {
            lon = Double.parseDouble(splited[0]);
            lat = Double.parseDouble(splited[1]);
        } catch (NumberFormatException e) {
            throw new JAXBException(errorMsg + e.getMessage());
        }
        result.addCoordinate(new Coordinate(lat, lon));
    }

    private void calculateLength(Route r){
        double length = 0.0;
        List<Coordinate> coordinates = r.getCoordinates();
        for(int i = 0; i < coordinates.size()-1; i++) {
            length += coordinates.get(i).getDistance(coordinates.get(i + 1));
        }

        r.setDistanceInMeters((int)Math.ceil(length));
    }

    private void calculateDurations(Route r){
        int length = r.getDistanceInMeters();

        r.setDurationByFoot((int)Math.ceil(length/speedByFoot));
        r.setDurationByBike((int)Math.ceil(length/speedByBike));
    }
}
