package de.p39.asrs.server.controller;

import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.controller.input.info.CategoryInfo;
import de.p39.asrs.server.controller.input.info.RouteInfo;
import de.p39.asrs.server.controller.input.info.SiteInfo;
import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.CategoryType;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private RouteDAO RoutedaoInterface;
    @Autowired
    private SiteDAO SiteDaoInterface;
    @Autowired
    private CategoryDAO CategoryDaoInterface;

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

        return "/kmlupload";
    }

    @GetMapping("/newsite")
    public String createsite(Model model){
        model.addAttribute("SiteInfo", new SiteInfo());
        model.addAttribute("categories", CategoryDaoInterface.getCategoriesByType(CategoryType.SITE));

        return "/siteform";
    }

    @GetMapping("/siteedit/{id}")
    public String editsite(Model model, @PathVariable Long id){
        model.addAttribute("site", SiteDaoInterface.getSiteById(id));
        model.addAttribute("SiteInfo", new SiteInfo());
        model.addAttribute("categories", CategoryDaoInterface.getCategoriesByType(CategoryType.SITE));
        return "siteedit";
    }

    @GetMapping("/newcategory")
    public String createcategory(Model model){
        model.addAttribute("CategoryInfo", new CategoryInfo());
        return "/newcategory";
    }

    @GetMapping("/categoryedit/{id}")
    public String editcategory(Model model, @PathVariable Long id){
        model.addAttribute("category", CategoryDaoInterface.getCategoryById(id));
        model.addAttribute("CategoryInfo", new CategoryInfo());
        return "categoryedit";
    }

    @GetMapping("/categoriesoverview")
    public String categoriesoverview(Model model){
        model.addAttribute("categories", CategoryDaoInterface.getAllCategories());
        return "categoriesoverview";
    }

}
