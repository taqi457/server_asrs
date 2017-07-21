package de.p39.asrs.server.inputControllerTests;


import com.sun.org.apache.xpath.internal.operations.Mult;
import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.controller.file.FileSystemStorage;
import de.p39.asrs.server.controller.input.SiteInputController;
import de.p39.asrs.server.controller.input.info.SiteInfo;
import de.p39.asrs.server.model.Site;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public class SiteInputControllerTest {

    SiteInputController controller;
    SiteDAO sdao;
    RouteDAO rdao;
    CategoryDAO cdao;
    MediumDAO mdao;
    MultipartFile audio_en;
    MultipartFile audio_fr;
    MultipartFile audio_de;
    MultipartFile picture;
    FileSystemStorage storage;
    Model model;

    @Before
    public void setUp(){
        System.out.println("Setting up");
        audio_en = new MockMultipartFile("data", "example.mp3", "audio/mp3", "some xml".getBytes());
        audio_fr = new MockMultipartFile("data", "example.mp3", "audio/mp3", "some xml".getBytes());
        audio_de = new MockMultipartFile("data", "example.mp3", "audio/mp3", "some xml".getBytes());
        picture = new MockMultipartFile("data", "example.mp3", "audio/mp3", "some xml".getBytes());
        controller = new SiteInputController(sdao, cdao, mdao, storage);
    }

    @Test
    public void SiteCreateTest(){
        MultipartFile[] audios= {audio_de,audio_fr,audio_en};
        SiteInfo info = new SiteInfo();
        String[] categorie = {"Villa"};
        controller.handleSiteInfo(info, audio_de, audio_en, audio_fr, picture, "continue", model, categorie);
    }
}
