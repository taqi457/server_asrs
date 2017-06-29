package de.p39.asrs.server.controller.db.dao;

import java.util.List;

import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.CategoryType;
/**
 * 
 * @author adrianrebmann
 *
 */
public interface CategoryDAO {
	
	public List<Category> getCategoriesByType(CategoryType s);
	
	public Category insertCategory(Category c);
	
	public void updateCategory(Category c);
	
	public void deleteCategory(Long id);

	public List<Category> getAllCategories();

	public Category getCategoryById(Long id);

	public List<Category> getCategoriesByType(String type);

}
