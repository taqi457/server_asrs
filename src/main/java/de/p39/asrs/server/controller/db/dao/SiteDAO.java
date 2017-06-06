package de.p39.asrs.server.controller.db.dao;

import java.util.Set;

import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Medium;
import de.p39.asrs.server.model.Site;
/**
 * 
 * @author adrianrebmann
 *
 */
public interface SiteDAO {
	
	public void insertSite(Site s);
	
	public void getSiteById(Long id);
	
	public Set<Site> getAllSites();
	
	public void deleteSite();
	
	public void updateSite(Site s);
	
	public void getSiteByName(String s);
	
	public void addMedium(Site s, Medium m);
	
	public void setCoordinate(Site s, Coordinate c);

}
