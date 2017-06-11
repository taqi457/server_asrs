package de.p39.asrs.server.controller.input;

import org.springframework.stereotype.Component;

import de.p39.asrs.server.controller.db.dao.MediumDAO;
/**
 * 
 * @author adrianrebmann
 *
 */
@Component
public class VideoInputController {

	private MediumDAO dao;

	private String germanName;
	private String englishName;
	private String frenchName;
	
	public VideoInputController() {
		// TODO Auto-generated constructor stub
	}

}
