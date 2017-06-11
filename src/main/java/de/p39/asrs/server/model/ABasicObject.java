package de.p39.asrs.server.model;

import java.io.Serializable;
import java.util.Date;

public abstract class ABasicObject<K extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1172925949019006047L;
	private K id;
	private String name;
	private Date timestamp;

	public ABasicObject(K id, String name) {
		this.setId(id);
		this.setName(name);
		this.timestamp = new Date();
	}
	
	public ABasicObject(String name){
		this.setName(name);
		this.timestamp=new Date();
	}
	
	/**
	 * plain constructor
	 */
	public ABasicObject(){this.timestamp=new Date();}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ABasicObject<K> other = (ABasicObject<K>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
