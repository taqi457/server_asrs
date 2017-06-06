package de.p39.asrs.server.controller.db.dao;

import de.p39.asrs.server.model.Category;
/**
 * 
 * @author adrianrebmann
 *
 */
public interface CategoryDAO {
	
	public Category getCategoryByName();
	
	public void insertCategory(Category c);
	
	public void updateCategory(Category c);
	
	public void deleteCategory(Long id);

}
