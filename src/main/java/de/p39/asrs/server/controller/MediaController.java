package de.p39.asrs.server.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import de.p39.asrs.server.controller.exceptions.BadRequestException;
import de.p39.asrs.server.model.media.Size;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.exceptions.InternalServerError;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/audio/example", method = RequestMethod.GET, produces = "audio/mpeg")
    public StreamingResponseBody audioExample() {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                FileInputStream is = new FileInputStream("resources/media/audio/ElectricGuitar.mp3");
                IOUtils.copy(is, outputStream);
            }
        };
    }

   /* @RequestMapping(value = "/audio/{id}", method = RequestMethod.GET)
    public HttpEntity<byte[]> audioByID(@PathVariable Long id) {
        Path path = Paths.get(dao.getAudioById(id).getPath());
        try {
            return getAudioResponse(path.toString());
        } catch (IOException ex) {
            throw new InternalServerError("Could not load audio. Contact server admin");
        }
    }
    // Helper
    private HttpEntity<byte[]> getAudioResponse(String path) throws IOException {
        // Put the file to the output stream
        FileInputStream is = new FileInputStream(path);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        IOUtils.copy(is, os);
        byte[] documentBody = os.toByteArray();

        // Set the headers
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Accept-Ranges", "bytes");
        responseHeaders.add("Connection", "close");
        responseHeaders.add("Content-Length", Integer.toString(documentBody.length));
        responseHeaders.add("Content-Type", "audio/mpeg");
        responseHeaders.add("Date", "Mon, 24 Jul 2017 12:00:00 GMT");

        return new HttpEntity<>(documentBody, responseHeaders);
    }
    
    @RequestMapping(value="/audio/test", method=RequestMethod.GET)
    public void getAudioMedia(HttpServletRequest request, HttpServletResponse response) throws IOException{
        byte[] bytes = Files.readAllBytes(Paths.get("resources/media/audio/ElectricGuitar.mp3"));
        response.getOutputStream().write(bytes);
    }*/
}
