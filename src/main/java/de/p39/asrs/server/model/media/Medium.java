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

	/**
	 * @return the nameDE
	 */
	public String getNameDE() {
		return super.getNameDE();
	}

	/**
	 * @param nameDE
	 *            the nameDE to set
	 */
	public void setNameDE(String nameDE) {
		super.setNameDE(nameDE);
	}

	/**
	 * @return the nameFR
	 */
	public String getNameFR() {
		return super.getNameFR();
	}

	/**
	 * @param nameFR
	 *            the nameFR to set
	 */
	public void setNameFR(String nameFR) {
		super.setNameFR(nameFR);
	}

	/**
	 * @return the nameEN
	 */
	public String getNameEN() {
		return super.getNameEN();
	}

	/**
	 * @param nameEN
	 *            the nameEN to set
	 */
	public void setNameEN(String nameEN) {
		super.setNameEN(nameEN);
	}

	/**
	 * @return the descriptionDE
	 */
	public String getDescriptionDE() {
		return super.getDescriptionDE();
	}

	/**
	 * @param descriptionDE
	 *            the descriptionDE to set
	 */
	public void setDescriptionDE(String descriptionDE) {
		super.setDescriptionDE(descriptionDE);
	}

	/**
	 * @return the descriptionFR
	 */
	public String getDescriptionFR() {
		return super.getDescriptionFR();
	}

	/**
	 * @param descriptionFR
	 *            the descriptionFR to set
	 */
	public void setDescriptionFR(String descriptionFR) {
		super.setDescriptionFR(descriptionFR);
	}

	/**
	 * @return the descriptionEN
	 */
	public String getDescriptionEN() {
		return super.getDescriptionEN();
	}

	/**
	 * @param descriptionEN
	 *            the descriptionEN to set
	 */
	public void setDescriptionEN(String descriptionEN) {
		super.setDescriptionEN(descriptionEN);
	}

}
