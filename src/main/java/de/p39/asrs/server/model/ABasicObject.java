package de.p39.asrs.server.model;

import java.io.Serializable;
import java.util.Date;

public abstract class ABasicObject<K extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1172925949019006047L;
	private K id;
	private Date timestamp;

	public ABasicObject(K id) {
		this.setId(id);
		this.timestamp = new Date();
	}
	
	public ABasicObject(String name){
		this.timestamp=new Date();
	}
	
	/**
	 * plain constructor
	 */
	public ABasicObject(){this.timestamp=new Date();}


	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the id
	 */
	public K getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(K id) {
		this.id = id;
	}
}
