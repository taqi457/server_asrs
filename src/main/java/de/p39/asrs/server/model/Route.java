
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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author adrianrebmann
 *
 */
@Entity
public class Route extends ABasicObject<Long> {

	/**
	 * Stepwidth for distance calculation if performance breaks down
	 */
	private static final long serialVersionUID = 2711246951505591683L;
	
	private static final int stepwidth = 5;

	private List<Coordinate> coordinates;
	private String gpx;
	private Set<Site> sites;

	public Route(Long id, String name) {
		super(id, name);
		this.init();
	}

	public Route() {
		super();
		this.init();
	}

	public Route(String name) {
		super(name);
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
	 * @return the name
	 */
	public String getName() {
		return super.getName();
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		super.setName(name);
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
	
	public double calculateDist(Coordinate coord){
		double distance = Double.MAX_VALUE;
		for(int i = 0;i<coordinates.size();i+=stepwidth){
			Coordinate c = coordinates.get(i);
			distance = Double.min(c.getDistance(coord),distance);
		}
		return distance;
	}
}
