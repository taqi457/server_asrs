package de.p39.asrs.db;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.model.Coordinate;
import de.p39.asrs.server.model.LocaleName;
import de.p39.asrs.server.model.Site;
import de.p39.asrs.server.model.media.Audio;
import de.p39.asrs.server.model.media.Picture;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjornmohr on 25.07.17.
 */
public class MockSiteDAO extends SiteDAO{

    private CrudFacade cf;
    private Site site;



    public MockSiteDAO(CrudFacade cf) {
        super(cf);
        this.cf=cf;
        site = new Site();

    }

    public Site insertSite(Site s) {
        site = s;
        site.setId((long) 1);

        return site;
    }

    public Site getSiteById(Long id) {
        return site;

    }

    public List<Site> getAllSites() {
        List list = new ArrayList();
        list.add(site);
        return list;
    }

    public List<Site> getAllSitesCompleted() {


        return getAllSites();
    }


    public void deleteSite(Long id) {
        return;
    }

    public Site updateSite(Site s) {
        site = s;
        return site;
    }

    public List<Site> getSitesByName(String s) {
        //Query q = this.cf.createQuery("SELECT e FROM " + Site.class.getName() + " e WHERE name = :name");
        //q.setParameter("name", s);
        //return (List<Site>) q.getResultList();

        return getAllSites();
    }

    public Site addPicture(Site s, Picture m) {
        s.addPicture(m);
        site = s;
        return site;
    }

    public Site removePicture(Site s, Picture m){
        s.removePicture(m);
        site = s;
        return site;
    }

    public void setCoordinate(Site s, Coordinate c) {
        s.setCoordinate(c);
    }
}
