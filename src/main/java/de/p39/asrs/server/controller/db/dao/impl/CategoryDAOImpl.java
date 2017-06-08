package de.p39.asrs.server.controller.db.dao.impl;

import java.util.List;

import javax.persistence.Query;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.model.Category;

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
	public void insertCategory(Category c) {
		this.cf.create(c);
	}

	@Override
	public void updateCategory(Category c) {
		this.cf.update(c);
	}

	@Override
	public void deleteCategory(Long id) {
		this.cf.delete(id, Category.class);
	}

}
