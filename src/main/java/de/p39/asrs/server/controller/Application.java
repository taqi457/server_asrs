package de.p39.asrs.server.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.JPACrudService;

@SpringBootApplication
public class Application {
	
	@Bean
    public CrudFacade JPACrudService() {
        return new JPACrudService();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
