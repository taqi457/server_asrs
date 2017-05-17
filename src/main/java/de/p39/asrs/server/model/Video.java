package de.p39.asrs.server.model;

import java.util.Locale;

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

}
