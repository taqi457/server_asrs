package de.p39.asrs.server.controller.db.dao.impl;

import java.util.List;

import javax.persistence.Query;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.Medium;
import de.p39.asrs.server.model.Site;

public class SiteDAOImpl implements SiteDAO {

	private CrudFacade cf;
	
	public SiteDAOImpl(CrudFacade cf) {
		this.cf=cf;
	}

	@Override
	public void insertSite(Site s) {
		this.cf.create(s);
	}

	@Override
	public Site getSiteById(Long id) {
		return this.cf.find(id, Site.class);

	}

	@Override
	public List<Site> getAllSites() {
		return this.cf.findAll(Site.class);
	}

	@Override
	public void deleteSite(Long id) {
		this.cf.delete(id, Site.class);
	}

	@Override
	public void updateSite(Site s) {
		this.cf.update(s);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Site> getSitesByName(String s) {
		Query q = this.cf.createQuery("SELECT e FROM " + Site.class.getName() + " e WHERE name = :name");
		q.setParameter("name", s);
		return (List<Site>) q.getResultList();
	}

	@Override
	public void addMedium(Site s, Medium m) {
		s.addMedium(m);
	}

	@Override
	public void setCoordinate(Site s, Coordinate c) {
		s.setCoordinate(c);
	}

}
