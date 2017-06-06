package de.p39.asrs.server.controller.db.dao.impl;

import java.util.List;

import de.p39.asrs.server.controller.db.JPACrudService;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Medium;
import de.p39.asrs.server.model.Site;

public class SiteDAOImpl implements SiteDAO {

	public SiteDAOImpl(JPACrudService jpaCrudService) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insertSite(Site s) {
		// TODO Auto-generated method stub

	}

	@Override
	public Site getSiteById(Long id) {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public List<Site> getAllSites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSite() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSite(Site s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getSiteByName(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMedium(Site s, Medium m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCoordinate(Site s, Coordinate c) {
		// TODO Auto-generated method stub
		
	}

}
