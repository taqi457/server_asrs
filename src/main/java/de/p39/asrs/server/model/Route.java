
package de.p39.asrs.server.model;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author adrianrebmann
 *
 */
@Entity
public class Route extends ANamedObject<Long> {

	/**
	 * Stepwidth for distance calculation if performance breaks down
	 */
	private static final long serialVersionUID = 2711246951505591683L;
	
	private static final int stepwidth = 5;

	private List<Coordinate> coordinates;
	
	private String gpx;
	private Set<Site> sites;
	/**
	 * in ms
	 */
	private Long duration;
	private Category category;
	private Double amplitude;
	

	public Route() {
		super();
		this.init();
	}


	private void init() {
		this.coordinates = new LinkedList<>();
		this.sites = new HashSet<>();
	}

	/**
	 * @return the coordinates
	 */
	@OneToMany(targetEntity = Coordinate.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates
	 *            the coordinates to set
	 */
	@OneToMany(targetEntity = Coordinate.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * @return the sites
	 */
	@OneToMany(targetEntity = Site.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Site> getSites() {
		return sites;
	}

	/**
	 * @param sites
	 *            the sites to set
	 */
	@OneToMany(targetEntity = Site.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public void setSites(Set<Site> sites) {
		this.sites = sites;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return super.getId();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public void setId(Long id) {
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
	 * @return the gpx
	 */
	public String getGpx() {
		return gpx;
	}

	/**
	 * @param gpx
	 *            the gpx to set
	 */
	public void setGpx(String gpx) {
		this.gpx = gpx;
	}
	
	
	
	/**
	 * @return the duration
	 */
	public Long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}

	/**
	 * @return the category
	 */
	@ManyToOne(targetEntity = Category.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	@ManyToOne(targetEntity = Category.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public void setCategory(Category category) {
		this.category = category;
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

	/**
	 * @return the amplitude
	 */
	public Double getAmplitude() {
		return amplitude;
	}

	/**
	 * @param amplitude the amplitude to set
	 */
	public void setAmplitude(Double amplitude) {
		this.amplitude = amplitude;
	}

	/**
	 * add and remove methods
	 */
	public void addCoordinate(Coordinate c){
		this.coordinates.add(c);
	}
	
	public void removeCoordinate(Coordinate c){
		this.coordinates.remove(c);
	}
	
	public void addSite(Site s){
		this.sites.add(s);
	}
	
	public void removeSite(Site s){
		this.sites.remove(s);
	}

	public double calculateDist(Coordinate coord){
		double distance = Double.MAX_VALUE;
		for(int i = 0;i<coordinates.size();i+=stepwidth){
			Coordinate c = coordinates.get(i);
			distance = Double.min(c.getDistance(coord),distance);
		}
		return distance;
	}

}
