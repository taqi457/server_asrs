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
public class Video extends Medium {

	/**
	 * 
	 */
	private static final long serialVersionUID = 19094117932109529L;
	
	private String link;

	public Video(Long id, String name,Locale locale) {
		super(id, name,locale);
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
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
