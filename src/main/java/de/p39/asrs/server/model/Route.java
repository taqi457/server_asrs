
package de.p39.asrs.server.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 * 
 * @author adrianrebmann
 *
 */
@Entity
public class Route extends ABasicObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2711246951505591683L;
	
	private Set<Coordinate> coordinates;
	private String gpx;
	private Set<Site> sites;

	public Route(Long id, String name) {
		super(id, name);
		this.init();
	}

	private void init() {
		this.coordinates=new HashSet<>();
		this.sites=new HashSet<>();
	}

	/**
	 * @return the coordinates
	 */
	@OneToMany(targetEntity=Coordinate.class, fetch=FetchType.EAGER)
	public Set<Coordinate> getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates the coordinates to set
	 */
	@OneToMany(targetEntity=Coordinate.class, fetch=FetchType.EAGER)
	public void setCoordinates(Set<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * @return the sites
	 */
	@OneToMany(targetEntity=Site.class, fetch=FetchType.EAGER)
	public Set<Site> getSites() {
		return sites;
	}

	/**
	 * @param sites the sites to set
	 */
	@OneToMany(targetEntity=Site.class, fetch=FetchType.EAGER)
	public void setSites(Set<Site> sites) {
		this.sites = sites;
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

	/**
	 * @return the gpx
	 */
	public String getGpx() {
		return gpx;
	}

	/**
	 * @param gpx the gpx to set
	 */
	public void setGpx(String gpx) {
		this.gpx = gpx;
	}
	
	

}
