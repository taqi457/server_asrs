package de.p39.asrs.server.model;

public class Coordinate extends ABasicObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1109989200634217707L;
	
	private Double latitude;
	private Double longitude;
	
	public Coordinate(Long id, String name) {
		super(id, name);
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}


	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}


	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	

}
