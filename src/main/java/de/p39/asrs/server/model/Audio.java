package de.p39.asrs.server.model;

import java.util.Locale;

public class Audio extends Medium {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1282454048651640596L;

	private String path;
	
	public Audio(Long id, String name,Locale locale) {
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

}
