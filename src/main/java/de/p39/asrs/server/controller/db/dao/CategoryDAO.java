package de.p39.asrs.server.controller.db.dao;

import java.util.List;

import javax.persistence.Query;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.CategoryType;

public class CategoryDAO{

	private CrudFacade cf;
	
	
	
	public CategoryDAO(CrudFacade cf) {
		super();
		this.cf = cf;
	}

	@SuppressWarnings("unchecked")
	public List<Category> getCategoriesByType(CategoryType s) {
		Query q = this.cf.createQuery("SELECT e FROM " + Category.class.getName() + " e WHERE type = :type");
		q.setParameter("type", s);
		return (List<Category>) q.getResultList();
	}

	public Category insertCategory(Category c) {
		return this.cf.create(c);
	}

	public void updateCategory(Category c) {
		this.cf.update(c);
	}

	public void deleteCategory(Long id) {
		this.cf.delete(id, Category.class);
	}

	public List<Category> getAllCategories(){
		return this.cf.findAll(Category.class);
	}
	public Category getCategoryById(Long id) {
		return this.cf.find(id, Category.class);
	}
}
