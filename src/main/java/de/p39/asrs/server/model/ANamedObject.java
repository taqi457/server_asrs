package de.p39.asrs.server.model;

import java.io.Serializable;
/**
 * 
 * @author adrianrebmann
 *
 * @param <K>
 */
public abstract class ANamedObject<K extends Serializable> extends ABasicObject<K> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5313396633041880809L;

	private String nameDE;
	private String nameFR;
	private String nameEN;

	private String descriptionDE;
	private String descriptionFR;
	private String descriptionEN;

	public ANamedObject() {
		super();
	}
	
	public ANamedObject(String name){
		super();
		nameDE = name;
	}

	public ANamedObject(K id, String nameDE, String nameFR, String nameEN, String descriptionDE, String descriptionFR,
			String descriptionEN) {
		super(id);
		this.nameDE = nameDE;
		this.nameFR = nameFR;
		this.nameEN = nameEN;
		this.descriptionDE = descriptionDE;
		this.descriptionFR = descriptionFR;
		this.descriptionEN = descriptionEN;
	}

	/**
	 * @return the nameDE
	 */
	public String getNameDE() {
		return nameDE;
	}

	/**
	 * @param nameDE
	 *            the nameDE to set
	 */
	public void setNameDE(String nameDE) {
		this.nameDE = nameDE;
	}

	/**
	 * @return the nameFR
	 */
	public String getNameFR() {
		return nameFR;
	}

	/**
	 * @param nameFR
	 *            the nameFR to set
	 */
	public void setNameFR(String nameFR) {
		this.nameFR = nameFR;
	}

	/**
	 * @return the nameEN
	 */
	public String getNameEN() {
		return nameEN;
	}

	/**
	 * @param nameEN
	 *            the nameEN to set
	 */
	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	/**
	 * @return the descriptionDE
	 */
	public String getDescriptionDE() {
		return descriptionDE;
	}

	/**
	 * @param descriptionDE
	 *            the descriptionDE to set
	 */
	public void setDescriptionDE(String descriptionDE) {
		this.descriptionDE = descriptionDE;
	}

	/**
	 * @return the descriptionFR
	 */
	public String getDescriptionFR() {
		return descriptionFR;
	}

	/**
	 * @param descriptionFR
	 *            the descriptionFR to set
	 */
	public void setDescriptionFR(String descriptionFR) {
		this.descriptionFR = descriptionFR;
	}

	/**
	 * @return the descriptionEN
	 */
	public String getDescriptionEN() {
		return descriptionEN;
	}

	/**
	 * @param descriptionEN
	 *            the descriptionEN to set
	 */
	public void setDescriptionEN(String descriptionEN) {
		this.descriptionEN = descriptionEN;
	}

}
