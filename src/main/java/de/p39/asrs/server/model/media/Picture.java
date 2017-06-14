package de.p39.asrs.server.model.media;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 
 * @author adrianrebmann
 *
 */
@Entity
public class Picture extends Medium {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2524529774881275743L;
	
	private String path;
	private Text text;
	
	public Picture(){super();}
	

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
	 * @return the timestamp
	 */
	@Temporal(TemporalType.TIMESTAMP) 
	public Date getTimestamp() {
		return super.getTimestamp();
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	@Temporal(TemporalType.TIMESTAMP) 
	public void setTimestamp(Date timestamp) {
		super.setTimestamp(timestamp);
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


	@OneToOne(targetEntity = Text.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public Text getText() {
		return text;
	}

	@OneToOne(targetEntity = Text.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public void setText(Text text) {
		this.text = text;
	}
}
