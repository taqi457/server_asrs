package de.p39.asrs.server.inputControllerTests;

import de.p39.asrs.db.MockCategoryDAO;
import de.p39.asrs.db.MockMediaDAO;
import de.p39.asrs.db.MockRouteDAO;
import de.p39.asrs.db.MockSiteDAO;
import de.p39.asrs.server.controller.db.JPACrudService;
import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.controller.file.FileSystemStorage;
import de.p39.asrs.server.controller.input.RouteInputController;
import de.p39.asrs.server.controller.input.SiteInputController;
import de.p39.asrs.server.controller.input.info.RouteInfo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by bjornmohr on 25.07.17.
 */
public class RouteInputControllerTest {
    RouteInputController controller;
    MockSiteDAO sdao;
    RouteDAO rdao;
    CategoryDAO cdao;
    MediumDAO mdao;
    MultipartFile audio_en;
    MultipartFile audio_fr;
    MultipartFile audio_de;
    MultipartFile picture;
    FileSystemStorage storage;
    Model model;
    JPACrudService cf = new JPACrudService();

    @Before
    @Autowired
    public void setUp() throws IOException {
        Path path = Paths.get("/Users/bjornmohr/SE/Server-ASRS/resources/media/images/example.jpg");
        byte[] data = Files.readAllBytes(path);
        System.out.println("Setting up");
        audio_en = new MockMultipartFile("data", "example.mp3", "audio/mp3", "some xml".getBytes());
        audio_fr = new MockMultipartFile("data", "example.mp3", "audio/mp3", "some xml".getBytes());
        audio_de = new MockMultipartFile("data", "example.mp3", "audio/mp3", "some xml".getBytes());
        picture = new MockMultipartFile("image", ".img", "application/json", data);
        storage = new FileSystemStorage();
        cdao = new MockCategoryDAO(cf);
        mdao = new MockMediaDAO(cf);
        rdao = new MockRouteDAO(cf);
        controller = new RouteInputController(rdao, cdao, storage);
    }

    @Test
    public void RouteUploadTest(){
        RouteInfo routeInfo= new RouteInfo();
        routeInfo.setDescriptionDE("test");
        routeInfo.setNameEN("test");
        routeInfo.setDescriptionFR("test");
        routeInfo.setDescriptionEN("test");
        routeInfo.setNameDE("test");
        routeInfo.setNameFR("test");
        routeInfo.setCategory((long) 1);
        controller.handleRouteInfo(routeInfo, audio_de, audio_en, audio_fr);
    }

    @Test
    public void RouteEditTest(){
        RouteInfo routeInfo= new RouteInfo();
        routeInfo.setDescriptionDE("test");
        routeInfo.setNameEN("test");
        routeInfo.setDescriptionFR("test");
        routeInfo.setDescriptionEN("test");
        routeInfo.setNameDE("test");
        routeInfo.setNameFR("test");
        routeInfo.setCategory((long) 1);
        controller.editRoute(routeInfo, (long)1,(long)1 ,audio_de, audio_en, audio_fr);
    }
}
