package de.p39.asrs.server.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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
    public String route(){
    	return "/routeoverview";
    }

}
