package de.p39.asrs.server.controller.db.dao;

import java.util.ArrayList;
import java.util.List;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.LocaleName;
import de.p39.asrs.server.model.Site;
import de.p39.asrs.server.model.media.Picture;

public class SiteDAO{

	private CrudFacade cf;
	
	public SiteDAO(CrudFacade cf) {
		this.cf=cf;
	}

	public Site insertSite(Site s) {
		return this.cf.create(s);
	}

	public Site getSiteById(Long id) {
		return this.cf.find(id, Site.class);

	}

	public List<Site> getAllSites() {
		return this.cf.findAll(Site.class);
	}

	public void deleteSite(Long id) {
		this.cf.delete(id, Site.class);
	}

	public Site updateSite(Site s) {
		return this.cf.update(s);
	}

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

	public Site addPicture(Site s, Picture m) {
		s.addPicture(m);
		return this.cf.update(s);
	}
	
	public Site removePicture(Site s, Picture m){
		s.removePicture(m);
		s=this.cf.update(s);
		this.cf.delete(m.getId(), Picture.class);
		return s;
	}

	public void setCoordinate(Site s, Coordinate c) {
		s.setCoordinate(c);
	}
	
}
