package de.p39.asrs.server.model;

import java.util.Locale;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
/**
 * 
 * @author adrianrebmann
 *
 */
@Inheritance
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
	@ManyToOne(targetEntity= Locale.class, fetch=FetchType.EAGER)
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	@ManyToOne(targetEntity= Locale.class, fetch=FetchType.EAGER)
	public void setLocale(Locale locale) {
		this.locale = locale;
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
