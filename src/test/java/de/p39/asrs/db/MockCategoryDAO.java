package de.p39.asrs.db;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.CategoryType;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjornmohr on 25.07.17.
 */
public class MockCategoryDAO extends CategoryDAO{

    private CrudFacade cf;
    private Category category;


    public MockCategoryDAO(CrudFacade cf) {
        super(cf);
        this.cf = cf;
    }

    @SuppressWarnings("unchecked")
    public List<Category> getCategoriesByType(CategoryType s) {
        Query q = this.cf.createQuery("SELECT e FROM " + Category.class.getName() + " e WHERE type = :type");
        q.setParameter("type", s);
        return (List<Category>) q.getResultList();
    }

    public Category insertCategory(Category c) {
        category = c;
        c.setId((long) 1);
        return (category);
    }

    public void updateCategory(Category c) {
        category = c;
    }

    public void deleteCategory(Long id) {
        return;
    }

    public List<Category> getAllCategories(){
        List list = new ArrayList();

        list.add(category);
        return list;
    }
    public Category getCategoryById(Long id) {
        return category;
    }
}
