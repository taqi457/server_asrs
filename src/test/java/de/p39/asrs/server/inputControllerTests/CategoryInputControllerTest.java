package de.p39.asrs.server.inputControllerTests;

import de.p39.asrs.db.MockCategoryDAO;
import de.p39.asrs.server.controller.db.JPACrudService;
import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.controller.input.CategoryInputController;
import de.p39.asrs.server.controller.input.info.CategoryInfo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bjornmohr on 25.07.17.
 */
public class CategoryInputControllerTest {
    CategoryInputController controller;
    CategoryDAO cdao;
    JPACrudService cf = new JPACrudService();

    @Before
    @Autowired
    public void setUp(){
        System.out.println("Setting up");
        cdao = new MockCategoryDAO(cf);
        controller = new CategoryInputController(cdao);
    }

    /*@Test
    public void create(){
        CategoryInfo categoryInfo= new CategoryInfo();
        categoryInfo.setDescriptionDE("test");
        categoryInfo.setNameEN("test");
        categoryInfo.setDescriptionFR("test");
        categoryInfo.setDescriptionEN("test");
        categoryInfo.setNameDE("test");
        categoryInfo.setNameFR("test");
        controller.handleCategoryInfo(categoryInfo, null, "route");
    }
    @Test
    public void edit(){
        CategoryInfo categoryInfo= new CategoryInfo();
        categoryInfo.setDescriptionDE("test");
        categoryInfo.setNameEN("test");
        categoryInfo.setDescriptionFR("test");
        categoryInfo.setDescriptionEN("test");
        categoryInfo.setNameDE("test");
        categoryInfo.setNameFR("test");
        controller.handleSiteEdit(categoryInfo, null, (long)1,"route");
    }*/
}
