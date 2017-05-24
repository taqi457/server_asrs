package de.p39.asrs.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category extends ABasicObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5143652688538221831L;

	private String category;
	
	public Category(Long id, String name) {
		super(id, name);
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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
