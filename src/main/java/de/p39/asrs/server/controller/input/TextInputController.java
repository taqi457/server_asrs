package de.p39.asrs.server.controller.input;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.db.dao.impl.MediumDAOImpl;
import de.p39.asrs.server.model.media.Text;
/**
 * 
 * @author adrianrebmann
 *
 */
@Component
public class TextInputController {

	private MediumDAO dao;
	
	private String germanName;
	private String englishName;
	private String frenchName;
	
	private String germanContent;
	private String englishContent;
	private String frenchContent;
	
	@Autowired
	public TextInputController(CrudFacade cf) {
		super();
		this.dao=new MediumDAOImpl(cf);
	}
	
	private Text create(String content, String name){
		if(content!=null&&name!=null){
			Text t = new Text();
			t.setContent(content);
			t.setName(name);
			return t;
		}else{
			//TODO notify user about failure
			return null;
		}
	}
	
	public void create(){
		
	}

	/**
	 * @return the germanName
	 */
	public String getGermanName() {
		return germanName;
	}

	/**
	 * @param germanName the germanName to set
	 */
	public void setGermanName(String germanName) {
		this.germanName = germanName;
	}

	/**
	 * @return the englishName
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * @param englishName the englishName to set
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	/**
	 * @return the frenchName
	 */
	public String getFrenchName() {
		return frenchName;
	}

	/**
	 * @param frenchName the frenchName to set
	 */
	public void setFrenchName(String frenchName) {
		this.frenchName = frenchName;
	}

	/**
	 * @return the germanContent
	 */
	public String getGermanContent() {
		return germanContent;
	}

	/**
	 * @param germanContent the germanContent to set
	 */
	public void setGermanContent(String germanContent) {
		this.germanContent = germanContent;
	}

	/**
	 * @return the englishContent
	 */
	public String getEnglishContent() {
		return englishContent;
	}

	/**
	 * @param englishContent the englishContent to set
	 */
	public void setEnglishContent(String englishContent) {
		this.englishContent = englishContent;
	}

	/**
	 * @return the frenchContent
	 */
	public String getFrenchContent() {
		return frenchContent;
	}

	/**
	 * @param frenchContent the frenchContent to set
	 */
	public void setFrenchContent(String frenchContent) {
		this.frenchContent = frenchContent;
	}
	
	

}
