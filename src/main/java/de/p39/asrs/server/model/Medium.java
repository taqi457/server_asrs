package de.p39.asrs.server.model;

import java.util.Locale;

public class Medium extends ABasicObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5552779801693557981L;
	
	private Locale locale;

	public Medium(Long id, String name, Locale locale) {
		super(id, name);
		this.setLocale(locale);
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
