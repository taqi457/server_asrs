package de.p39.asrs.server.controller.db.dao;

import java.util.List;

import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Site;
import de.p39.asrs.server.model.media.Medium;
/**
 * 
 * @author adrianrebmann
 *
 */
public interface SiteDAO {
	
	public void insertSite(Site s);
	
	public Site getSiteById(Long id);
	
	public List<Site> getAllSites();
	
	public void deleteSite(Long id);
	
	public void updateSite(Site s);
	
	public List<Site> getSitesByName(String s);
	
	public void addMedium(Site s, Medium m);
	
	public void setCoordinate(Site s, Coordinate c);

}
