
package de.p39.asrs.server.model;

import java.util.ArrayList;
import java.util.List;

public class Route extends ABasicObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2711246951505591683L;
	
	
	private List<Coordinate> coordinates;
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

}
