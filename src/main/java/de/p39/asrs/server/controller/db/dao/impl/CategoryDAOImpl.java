package de.p39.asrs.server.controller.db.dao.impl;

import java.util.List;

import javax.persistence.Query;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.Route;

public class CategoryDAOImpl implements CategoryDAO {

	private CrudFacade cf;
	
	
	
	public CategoryDAOImpl(CrudFacade cf) {
		super();
		this.cf = cf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoriesByName(String s) {
		Query q = this.cf.createQuery("SELECT e FROM " + Category.class.getName() + " e WHERE name = :name");
		q.setParameter("name", s);
		return (List<Category>) q.getResultList();
	}

	@Override
	public Category insertCategory(Category c) {
		return this.cf.create(c);
	}

	@Override
	public void updateCategory(Category c) {
		this.cf.update(c);
	}

	@Override
	public void deleteCategory(Long id) {
		this.cf.delete(id, Category.class);
	}

	@Override
	public List<Category> getAllCategories(){
		return this.cf.findAll(Category.class);
	}
	@Override
	public Category getCategoryById(Long id) {
		return this.cf.find(id, Category.class);
	}

	@Override
	public List<Category> getCategoriesByType(String type){
		Query q = this.cf.createQuery("SELECT e FROM " + Category.class.getName() + " e WHERE type = :type");
		q.setParameter("type", type);
		return (List<Category>) q.getResultList();
	}
}
