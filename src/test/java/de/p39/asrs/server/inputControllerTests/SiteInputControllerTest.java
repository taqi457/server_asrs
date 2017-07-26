package de.p39.asrs.server.inputControllerTests;


import de.p39.asrs.db.MockCategoryDAO;
import de.p39.asrs.db.MockMediaDAO;
import de.p39.asrs.db.MockSiteDAO;
import de.p39.asrs.server.controller.db.JPACrudService;
import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.db.dao.RouteDAO;
import de.p39.asrs.server.controller.file.FileSystemStorage;
import de.p39.asrs.server.controller.input.SiteInputController;
import de.p39.asrs.server.controller.input.info.PictureInfo;
import de.p39.asrs.server.controller.input.info.SiteInfo;
import de.p39.asrs.server.model.media.Picture;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SiteInputControllerTest {

    SiteInputController controller;
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
    public void setUp() throws IOException{
        Path path = Paths.get("/Users/bjornmohr/SE/Server-ASRS/resources/media/images/example.jpg");
        byte[] data = Files.readAllBytes(path);
        System.out.println("Setting up");
        audio_en = new MockMultipartFile("data", "example.mp3", "audio/mp3", "some xml".getBytes());
        audio_fr = new MockMultipartFile("data", "example.mp3", "audio/mp3", "some xml".getBytes());
        audio_de = new MockMultipartFile("data", "example.mp3", "audio/mp3", "some xml".getBytes());
        picture = new MockMultipartFile("image", ".img", "application/json", data);
        storage = new FileSystemStorage();
        sdao = new MockSiteDAO(cf);
        cdao = new MockCategoryDAO(cf);
        mdao = new MockMediaDAO(cf);
        controller = new SiteInputController(sdao, cdao, mdao, storage);
    }

    @Test
    public void SiteCreateTest(){
        MultipartFile[] audios= {audio_de,audio_fr,audio_en};
        SiteInfo info = new SiteInfo();
        info.setCity("test");
        info.setCountry("test");
        info.setDescriptionDE("test");
        info.setDescriptionEN("test");
        info.setDescriptionFR("test");
        info.setLatitude(210.0);
        info.setLongitude(210.0);
        info.setNameDE("test");
        info.setNameEN("test");
        info.setNameFR("test");
        info.setStreet("test");
        info.setWebsite("test");
        info.setZip("23123");
        String[] categorie = {"Villa"};
        controller.handleSiteInfo(info, audio_de, audio_en, audio_fr, picture, "finish", model, categorie);

    }

    @Test
    public void SiteEditTest(){
        MultipartFile[] audios= {audio_de,audio_fr,audio_en};
        SiteInfo info = new SiteInfo();
        info.setCity("test");
        info.setCountry("test");
        info.setDescriptionDE("test");
        info.setDescriptionEN("test");
        info.setDescriptionFR("test");
        info.setLatitude(210.0);
        info.setLongitude(210.0);
        info.setNameDE("test");
        info.setNameEN("test");
        info.setNameFR("test");
        info.setStreet("test");
        info.setWebsite("test");
        info.setZip("23123");
        String[] categorie = {"Villa"};
        controller.handleSiteEdit(info, audio_de, audio_de, audio_en, picture, model, (long) 1, categorie, "finish");

    }

    @Test
    public void SiteDeleteTest(){
        controller.handleSiteDelete((long) 1);
    }

    @Test
    public void AudioDeleteTest(){
        controller.handleAudioDelete((long) 1);
    }

    @Test
    public void PictureDeleteTest(){
        controller.handlePictureDelete((long) 1,(long)1);
    }

    @Test
    public void PictureServeTest(){
        controller.servePicture("example.jpg");
    }

    @Test
    public void PictureUploadTest(){
        PictureInfo pictureInfo= new PictureInfo();
        pictureInfo.setEnglishDescription("test");
        pictureInfo.setEnglishName("test");
        pictureInfo.setFrenchDescription("test");
        pictureInfo.setFrenchName("test");
        pictureInfo.setGermanDescription("test");
        pictureInfo.setGermanName("test");
        controller.HandlePictureUpload(pictureInfo, model, audio_de, audio_en, audio_fr, picture, (long) 1, "finish");
        controller.HandlePictureUpload(pictureInfo, model, audio_de, audio_en, audio_fr, (long) 1, (long) 1, picture);
    }

    @Test
    public void ChangeOrderTest(){
        int[] array = {};
        controller.ChangePictureOrder(array, (long) 1);
    }
}
