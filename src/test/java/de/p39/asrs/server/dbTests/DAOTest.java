package de.p39.asrs.server.dbTests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.JPACrudService;
import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.CategoryType;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;

public class DAOTest {
	CrudFacade cf;
	
	@Before
	public void setUp(){
		System.out.println("Setting up");
		cf = new JPACrudService("server");
	}
	
	@Test
	public void routeDAOTest(){
		RouteDAO dao = new RouteDAO(cf);
		dao.getAllRoutes();
		dao.getAllRoutesCompleted();
		dao.getRouteById(0L);
		Route r = new Route();
		dao.instertRoute(r);
		List<Route> list = dao.getAllRoutes();
		r = list.get(0);
		dao.deleteRoute(r.getId());
	}
	@Test
	public void siteDAOTest(){
		SiteDAO dao = new SiteDAO(cf);
		dao.getAllSites();
		dao.getAllSitesCompleted();
		dao.getSiteById(0L);
		dao.getSitesByName("");
		Site s = new Site();
		dao.insertSite(s);
		List<Site> list = dao.getAllSites();
		s = list.get(0);
		dao.deleteSite(s.getId());
	}
	@Test
	public void mediaDAOTest(){
		MediumDAO dao = new MediumDAO(cf);
		dao.getAllAudios();
		dao.getAllPictures();
		dao.getAudioById(0L);
		dao.getAudiosByPath("");
		dao.getPictureById(0L);
		
	}
	
	@Test
	public void categoryDAOTest(){
		CategoryDAO dao = new CategoryDAO(cf);
		dao.getAllCategories();
		dao.getCategoriesByType(CategoryType.ROUTE);
		dao.getCategoryById(0L);
		Category c = new Category();
		dao.insertCategory(c);
		List<Category> list = dao.getAllCategories();
		c = list.get(0);
		dao.deleteCategory(c.getId());
	}
	

}
