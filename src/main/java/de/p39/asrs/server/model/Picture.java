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
public class Picture extends Medium {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2524529774881275743L;
	
	private String path;

	public Picture(Long id, String name,Locale locale) {
		super(id, name,locale);
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
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
