package de.p39.asrs.server.controller.input;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.LocaleName;
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
	public CategoryInputController(CategoryDAO dao) {
		super();
		this.dao = dao;
	}

	@PostMapping("/category")
	public void create(){
		if(germanname!=null&&englishname!=null&&frenchname!=null){
			Category c = new Category();
			LocaleName gn = new LocaleName();
			gn.setString(germanname);
			gn.setLocale(Locale.GERMAN);
			LocaleName fn = new LocaleName();
			fn.setString(frenchname);
			fn.setLocale(Locale.FRENCH);
			LocaleName en = new LocaleName();
			en.setString(englishname);
			en.setLocale(Locale.ENGLISH);
			c.addLocaleName(gn);
			c.addLocaleName(fn);
			c.addLocaleName(en);
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
