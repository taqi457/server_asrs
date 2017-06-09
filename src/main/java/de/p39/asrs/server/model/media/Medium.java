package de.p39.asrs.server.model.media;

import java.util.Locale;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;

import de.p39.asrs.server.model.ABasicObject;
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

	public Medium(Long id, String name) {
		super(id, name);
	}
	
	public Medium(){super();}
	
	public Medium(String name){
		super(name);
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId(){
		return super.getId();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public void setId(Long id){
		super.setId(id);
	}

}
