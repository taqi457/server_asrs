package de.p39.asrs.server.controller.util.reader;

import java.io.File;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Geometry;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LineString;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;

/**
 * @author robin
 *
 *	The KMLReader parses a route from a KML file including locations.
 */
public class KMLReader {
	
	private String path;
	
	
	public KMLReader(String path){
		this.path = path;
	}
	
	public Route parseKml(){
	    //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
		File f = new File(path);
	   
	    Kml kml = Kml.unmarshal(f);
	    Feature feature = kml.getFeature();
	    
	    return parseFeature(feature);
	}

	private Route parseFeature(Feature feature) {
		if(feature != null) {
	        if(feature instanceof Document) {
	            Document document = (Document) feature;
	            List<Feature> featureList = document.getFeature();
	            
	            Route res = new Route();
	            res.setNameDE(document.getName());
	            res.setNameEN(document.getName());
	            res.setNameFR(document.getName());
	            for(Feature documentFeature : featureList) {
	                if(documentFeature instanceof Folder) {
	                    Folder folder = (Folder) documentFeature;
	                    List<Feature> folderList = folder.getFeature();
	                    for (Feature folderFeature : folderList){
	                    	if(folderFeature instanceof Placemark){
	                    		Placemark placemark = (Placemark) folderFeature;
	                    		parsePlacemark(res, placemark);
	                    	}
	                    		
	                    }
	                }
	            }
	            return res;
	        }
	    }
		return null;
	}

	private void parsePlacemark(Route r, Placemark placemark) {
		if(placemark != null) {
			Geometry geometry = placemark.getGeometry();
	        if(geometry instanceof LineString) {
	            LineString lineString = (LineString) geometry;
	            List<de.micromata.opengis.kml.v_2_2_0.Coordinate> coordinateList = lineString.getCoordinates();
	            for (de.micromata.opengis.kml.v_2_2_0.Coordinate c : coordinateList){
	            	r.addCoordinate(transformCoord(c));
	            }
	        } else if (geometry instanceof Point){
	        	Point point = (Point) geometry;
	        	Site site = new Site(placemark.getName());
	        	
	        	site.setCoordinate(transformCoord(point.getCoordinates().get(0)));
	        	r.addSite(site);
	        }
	    }
		
	}
	
	private static de.p39.asrs.server.model.Coordinate transformCoord(de.micromata.opengis.kml.v_2_2_0.Coordinate c) {
		return new de.p39.asrs.server.model.Coordinate(c.getLatitude(), c.getLongitude());
	}
}
