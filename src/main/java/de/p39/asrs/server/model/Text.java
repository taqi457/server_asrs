package de.p39.asrs.server.model;

import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 
 * @author adrianrebmann
 *
 */
@Entity
public class Text extends Medium {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5139030591840399032L;

	private String content;
	
	public Text(Long id, String name,Locale locale) {
		super(id, name,locale);
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId(){
		return this.getId();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public void setId(Long id){
		this.setId(id);
	}

}
