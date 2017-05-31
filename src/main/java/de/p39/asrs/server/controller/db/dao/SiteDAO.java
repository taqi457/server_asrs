package de.p39.asrs.server.controller.db.dao;

import java.util.Set;

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

}
