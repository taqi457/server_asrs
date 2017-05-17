package de.p39.asrs.server.model;

import java.io.Serializable;

public abstract class ABasicObject<K> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1172925949019006047L;
	private K id;
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public ABasicObject(K id, String name) {
		this.setId(id);
		this.setName(name);
	}

	/**
	 * @return the id
	 */
	public K getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(K id) {
		this.id = id;
	}

}
