package de.p39.asrs.server.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import de.p39.asrs.server.controller.exceptions.BadRequestException;
import de.p39.asrs.server.model.media.Size;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.exceptions.InternalServerError;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/media")
public class MediaController {

    @Autowired
    private MediumDAO dao;

    @RequestMapping(value = "/img/example", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] imageExample() {
        Path path = Paths.get("media/images/example.jpg");
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new byte[1];
    }

    @RequestMapping(value = "/img/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] imageByID(@PathVariable Long id, @RequestParam(value = "size") String size) {
        Size pictureSize;
        switch (size) {
            case "thumb":
                pictureSize = Size.SMALL;
                break;
            case "medium":
                pictureSize = Size.MEDIUM;
                break;
            case "large":
                pictureSize = Size.LARGE;
                break;
            default:
                throw new BadRequestException("Provide size as thumb, medium or large!");
        }
        Path path = Paths.get(dao.getPictureById(id).getPath(pictureSize));
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new InternalServerError("Could not load image. Contact server admin");
        }
    }

    @RequestMapping(value = "/audio/example", method = RequestMethod.GET)
    public void audioExample(HttpServletResponse response) {
        try {
            setResponseValues(response, "resources/media/audio/example.mp3");
        } catch (IOException ex) {
            throw new InternalServerError("Could not load audio. Contact server admin");
        }
    }

    @RequestMapping(value = "/audio/{id}", method = RequestMethod.GET)
    public void audioByID(@PathVariable Long id, HttpServletResponse response) {
        Path path = Paths.get(dao.getAudioById(id).getPath());
        try {
            setResponseValues(response, path.toString());
        } catch (IOException ex) {
            throw new InternalServerError("Could not load audio. Contact server admin");
        }
    }

    // Helper
    private void setResponseValues(HttpServletResponse response, String path) throws IOException {
        InputStream is = new FileInputStream(path.toString());
        response.setHeader("Content-Disposition", "attachment; filename= audio.mp3");
        response.setContentType("audio/mpeg");
        response.setContentLength(is.available());
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }
}
