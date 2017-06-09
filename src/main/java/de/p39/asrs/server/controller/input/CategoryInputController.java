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
	private String name;
	
	@Autowired
	public CategoryInputController(CrudFacade cf) {
		super();
		this.dao = new CategoryDAOImpl(cf);
	}

	public void create(){
		if(name!=null){
			Category c = new Category();
			c.setName(name);
			this.dao.insertCategory(c);
		}
	}

}
