package de.p39.asrs.server.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.JPACrudService;
import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.controller.db.dao.impl.RouteDAOImpl;
import de.p39.asrs.server.controller.db.dao.impl.SiteDAOImpl;
import de.p39.asrs.server.controller.db.dao.impl.CategoryDAOImpl;
import de.p39.asrs.server.controller.db.dao.impl.MediumDAOImpl;

@SpringBootApplication
public class Application {
	
	private final CrudFacade cf = new JPACrudService();
	
	@Bean
	public RouteDAO RouteDAOImpl(){
		return new RouteDAOImpl(cf);
	}
	
	@Bean 
	public SiteDAO SiteDAOImpl(){
		return new SiteDAOImpl(cf);
	}
	
	@Bean
	public CategoryDAO CategoryDAOImpl(){
		return new CategoryDAOImpl(cf);
	}
	
	@Bean
	public MediumDAO MediumDAOImpl(){
		return new MediumDAOImpl(cf);
	}
	
	//@Bean
	//public Storage FileSystemStorage(){
	//	return new FileSystemStorage();
	//}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*@Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        return new ServletRegistrationBean(servlet, "*.jsf");
    }

    @Bean
    public FilterRegistrationBean rewriteFilter() {
        FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
        rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST,
                DispatcherType.ASYNC, DispatcherType.ERROR));
        rwFilter.addUrlPatterns("*//*");
        return rwFilter;
    }*/
}
