package de.p39.asrs.server.dbTests;

import org.junit.Before;
import org.junit.Test;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.JPACrudService;
import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.model.CategoryType;

public class DAOTest {
	CrudFacade cf;
	
	@Before
	public void setUp(){
		System.out.println("Setting up");
		cf = new JPACrudService("server");
	}
	
	@Test
	public void roteDAOTest(){
		RouteDAO dao = new RouteDAO(cf);
		dao.getAllRoutes();
		dao.getAllRoutesCompleted();
		dao.getRouteById(0L);
	}
	public void siteDAOTest(){
		SiteDAO dao = new SiteDAO(cf);
		dao.getAllSites();
		dao.getAllSitesCompleted();
		dao.getSiteById(0L);
		dao.getSitesByName("");
	}
	public void mediaDAOTest(){
		MediumDAO dao = new MediumDAO(cf);
		dao.getAllAudios();
		dao.getAllPictures();
		dao.getAudioById(0L);
		dao.getAudiosByPath("");
		dao.getPictureById(0L);
		dao.getPictureByPath("");
		
	}
	public void categoryDAOTest(){
		CategoryDAO dao = new CategoryDAO(cf);
		dao.getAllCategories();
		dao.getCategoriesByType(CategoryType.ROUTE);
		dao.getCategoriesByType("");
		dao.getCategoryById(0L);
	}
	

}
