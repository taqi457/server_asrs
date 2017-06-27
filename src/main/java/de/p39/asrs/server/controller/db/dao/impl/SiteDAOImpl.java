package de.p39.asrs.server.controller.db.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.LocaleName;
import de.p39.asrs.server.model.Site;
import de.p39.asrs.server.model.media.Medium;

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


	@Override
	public List<Site> getSitesByName(String s) {
		//Query q = this.cf.createQuery("SELECT e FROM " + Site.class.getName() + " e WHERE name = :name");
		//q.setParameter("name", s);
		//return (List<Site>) q.getResultList();
		List<Site> result = new ArrayList<>();
		List<Site> all = this.cf.findAll(Site.class);
		for(Site site : all){
			List<LocaleName> names = site.getNames();
			for(LocaleName name : names){
				if(name.getString().equals(s)&&!result.contains(site))
					result.add(site);
			}
		}
		return result;
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
