package de.p39.asrs.server.controller.input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.controller.db.dao.impl.CategoryDAOImpl;
import de.p39.asrs.server.model.Category;
/**
 * 
 * @author adrianrebmann
 *
 */
@Component
public class CategoryInputController {

	private CategoryDAO dao;
	private String germanname;
	private String frenchname;
	private String englishname;
	
	@Autowired
	public CategoryInputController(CrudFacade cf) {
		super();
		this.dao = new CategoryDAOImpl(cf);
	}

	public void create(){
		if(germanname!=null&&englishname!=null&&frenchname!=null){
			Category c = new Category();
			c.setNameDE(germanname);
			c.setNameEN(englishname);
			c.setNameFR(frenchname);
			this.dao.insertCategory(c);
		}
	}

	/**
	 * @return the germanname
	 */
	public String getGermanname() {
		return germanname;
	}

	/**
	 * @param germanname the germanname to set
	 */
	public void setGermanname(String germanname) {
		this.germanname = germanname;
	}

	/**
	 * @return the frenchname
	 */
	public String getFrenchname() {
		return frenchname;
	}

	/**
	 * @param frenchname the frenchname to set
	 */
	public void setFrenchname(String frenchname) {
		this.frenchname = frenchname;
	}

	/**
	 * @return the englishname
	 */
	public String getEnglishname() {
		return englishname;
	}

	/**
	 * @param englishname the englishname to set
	 */
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	
	

}
