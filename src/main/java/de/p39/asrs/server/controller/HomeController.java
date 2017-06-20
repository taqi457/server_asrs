package de.p39.asrs.server.controller;

import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.controller.db.dao.impl.RouteDAOImpl;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private RouteDAO RoutedaoInterface;
    @Autowired
    private SiteDAO SiteDaoInterface;

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/login")
    public String login() {
        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();

        if (!auth.getPrincipal().equals("anonymousUser")) {
        return "/home";
        }

        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
    
    @GetMapping("/upload")
    public String upload(){
    	return "/upload";
    }
    
    @GetMapping("/uploadkml")
    public String uploadkml(){
    	return "/uploadkml";
    }
    
    @GetMapping("/routeoverview")
    public String route(Model model){
    	model.addAttribute("allRoutes", RoutedaoInterface.getAllRoutes());

        return "/routeoverview";
    }

    @GetMapping("/locationoverview")
    public String site(Model model){
        model.addAttribute("allSites", SiteDaoInterface.getAllSites());

        return "/siteoverview";
    }
    @GetMapping("/newroute")
    public String createroute(Model model){

        return "/routeform";
    }

}
