package de.p39.asrs.server.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Coordinate extends BaseEntity<Long> {

	/**
	 * The earth radius in meters.
	 */
	static final double earthRadius = 6378137.0; // WGS84 standard earth radius

	static final double MIN_LON = -180.0;
	static final double MAX_LON = 180.0;
	/** maximum latitude (north) for mercator display */
	static final double MIN_LAT = -85.05112877980659;
	/** minimum latitude (south) for mercator display */
	static final double MAX_LAT = 85.05112877980659;

	private static final long serialVersionUID = 1109989200634217707L;

	private Double latitude;
	private Double longitude;

	public Coordinate(Long id) {
		super(id);
	}
	
	public Coordinate(Double lat, Double lon) {
		super();
		latitude = lat;
		longitude = lon;
	}

	public Coordinate() {
		super();
	}


	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
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
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}

	/**
	 * Compute the geographic distance between coordinates.
	 * 
	 * @param lat1
	 *            The latitude of the first coordinate.
	 * @param lng1
	 *            The longitude of the first coordinate.
	 * @param lat2
	 *            The latitude of the second coordinate.
	 * @param lng2
	 *            The longitude of the second coordinate.
	 * 
	 * @return The distance in meters between the coordinates.
	 */
	public static double distance(double lat1, double lng1, double lat2, double lng2) {

		// Always compute the distance from the smaller to the bigger
		// coordinate.
		if (lat1 > lat2 || (lat1 == lat2 && lng1 > lng2))
			return distance(lat2, lng2, lat1, lng1);

		double phi1 = Math.toRadians(90.0 - lat1);
		double phi2 = Math.toRadians(90.0 - lat2);

		double theta1 = Math.toRadians(lng1);
		double theta2 = Math.toRadians(lng2);

		double cos = Math.sin(phi1) * Math.sin(phi2) * Math.cos(theta1 - theta2);
		cos += (Math.cos(phi1) * Math.cos(phi2));

		double arc;
		if (cos < -1 || cos > 1)
			arc = 0;
		else
			arc = Math.acos(cos);

		return arc * earthRadius;
	}

	/**
	 * @param c
	 *            The other coordinate
	 * 
	 * @return The distance in meters between these and the given coordinates.
	 */
	public double getDistance(Coordinate c) {
		return distance(getLatitude(), getLongitude(), c.getLatitude(), c.getLongitude());
	}
}
