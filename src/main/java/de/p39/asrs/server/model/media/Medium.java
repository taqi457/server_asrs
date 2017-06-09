package de.p39.asrs.server.model.media;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import de.p39.asrs.server.model.ANamedObject;
/**
 * 
 * @author adrianrebmann
 *
 */
@Inheritance
public class Medium extends ANamedObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5552779801693557981L;

	
	public Medium(){super();}
	
	
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
