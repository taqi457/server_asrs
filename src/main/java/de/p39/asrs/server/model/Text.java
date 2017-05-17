package de.p39.asrs.server.model;

import java.util.Locale;

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

}
