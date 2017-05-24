package de.p39.asrs.server.model;

import java.util.Locale;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;

@Inheritance
public class Medium extends ABasicObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5552779801693557981L;
	
	@ManyToOne(targetEntity= Locale.class, fetch=FetchType.EAGER)
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
	
	@Id
	@GeneratedValue
	public Long getId(){
		return this.getId();
	}
	
	@Id
	@GeneratedValue
	public void setId(Long id){
		this.setId(id);
	}

}
