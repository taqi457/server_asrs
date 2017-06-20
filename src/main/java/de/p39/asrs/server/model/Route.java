
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 
 * @author adrianrebmann
 *
 */
@Entity
public class Route extends NamedObject {

	/**
	 * Stepwidth for distance calculation if performance breaks down
	 */
	private static final long serialVersionUID = 2711246951505591683L;
	private static final int stepwidth = 5;

	private List<Coordinate> coordinates;
	private Set<Site> sites;
	private String pathToKml;
	/**
	 * in ms
	 */
	private Long durationByFoot;
	private Long durationByBike;

	private Category category;
	private Double amplitude;
	
	private boolean isCompleted;

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
	@OneToMany(targetEntity = Coordinate.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates
	 *            the coordinates to set
	 */
	@OneToMany(targetEntity = Coordinate.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * @return the sites
	 */
	@OneToMany(targetEntity = Site.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Site> getSites() {
		return sites;
	}

	/**
	 * @param sites
	 *            the sites to set
	 */
	@OneToMany(targetEntity = Site.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
	 * @return the category
	 */
	@ManyToOne(targetEntity = Category.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	@ManyToOne(targetEntity = Category.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the names
	 */
	@OneToMany(targetEntity = LocaleName.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<LocaleName> getNames() {
		return names;
	}

	/**
	 * @param names
	 *            the names to set
	 */
	@OneToMany(targetEntity = LocaleName.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public void setNames(List<LocaleName> names) {
		this.names = names;
	}

	/**
	 * @return the descriptions
	 */
	@OneToMany(targetEntity = LocaleDescription.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<LocaleDescription> getDescriptions() {
		return descriptions;
	}

	/**
	 * @param descriptions
	 *            the descriptions to set
	 */
	@OneToMany(targetEntity = LocaleDescription.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public void setDescriptions(List<LocaleDescription> descriptions) {
		this.descriptions = descriptions;
	}

	public Long getDurationByFoot() {
		return durationByFoot;
	}

	public void setDurationByFoot(Long durationByFoot) {
		this.durationByFoot = durationByFoot;
	}

	public Long getDurationByBike() {
		return durationByBike;
	}

	public void setDurationByBike(Long durationByBike) {
		this.durationByBike = durationByBike;
	}

	/**
	 * @return the amplitude
	 */
	public Double getAmplitude() {
		return amplitude;
	}

	/**
	 * @param amplitude
	 *            the amplitude to set
	 */
	public void setAmplitude(Double amplitude) {
		this.amplitude = amplitude;
	}
	
	

	public boolean isCompleted() {
		this.setCompleted(this.checkCompleted());
		return isCompleted;
	}
	
	private boolean checkCompleted(){
		if(this.descriptions.size()<3)
			return false;
		if(this.names.size()<3)
			return false;
		for(LocaleDescription ld : this.descriptions){
			if(ld==null||ld.getString()==null||ld.getLocale()==null){
				return false;
			}
		}
		for(LocaleName ln : this.names){
			if(ln==null||ln.getString()==null||ln.getLocale()==null){
				return false;
			}
		}
		if (this.sites.isEmpty())
			return false;
		if(this.pathToKml==null)
			return false;
		if(this.coordinates.isEmpty())
			return false;
		if(this.category==null)
			return false;
		for(Site s : this.sites){
			if(!s.isCompleted()){
				return false;
			}
		}
		return true;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	/**
	 * add and remove methods
	 */
	public void addCoordinate(Coordinate c) {
		this.coordinates.add(c);
	}

	public void removeCoordinate(Coordinate c) {
		this.coordinates.remove(c);
	}

	public void addSite(Site s) {
		this.sites.add(s);
	}

	public void removeSite(Site s) {
		this.sites.remove(s);
	}

	public double calculateDist(Coordinate coord) {
		double distance = Double.MAX_VALUE;
		for (int i = 0; i < coordinates.size(); i += stepwidth) {
			Coordinate c = coordinates.get(i);
			distance = Double.min(c.getDistance(coord), distance);
		}
		return distance;
	}

	public String getPathToKml() {
		return pathToKml;
	}

	public void setPathToKml(String pathToKml) {
		this.pathToKml = pathToKml;
	}
}
