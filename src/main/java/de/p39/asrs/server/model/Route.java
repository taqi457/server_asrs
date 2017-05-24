
package de.p39.asrs.server.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Route extends ABasicObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2711246951505591683L;
	
	@OneToMany(targetEntity=Coordinate.class, fetch=FetchType.EAGER)
	private List<Coordinate> coordinates;
	
	@OneToMany(targetEntity=Site.class, fetch=FetchType.EAGER)
	private List<Site> sites;

	public Route(Long id, String name) {
		super(id, name);
		this.init();
	}

	private void init() {
		this.coordinates=new ArrayList<>();
		this.sites=new ArrayList<>();
	}

	/**
	 * @return the coordinates
	 */
	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * @return the sites
	 */
	public List<Site> getSites() {
		return sites;
	}

	/**
	 * @param sites the sites to set
	 */
	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	
	@Id
	@GeneratedValue
	public Long getId(){
		return this.getId();
	}
	
	@Id
	@GeneratedValue
	public void setId(Long id){
		this.setId(id);
	}

}
