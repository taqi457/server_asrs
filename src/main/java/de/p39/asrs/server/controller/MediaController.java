package de.p39.asrs.server.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.exceptions.InternalServerError;

@RestController
@RequestMapping(value = "/media")
public class MediaController {
	
	@Autowired
	private MediumDAO dao;
	
	@RequestMapping(value = "/img/example", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] imageID() {
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
	public byte[] imageByID(@PathVariable Long id) {
	    Path path = Paths.get(dao.getPictureById(id).getPath());
	    try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			throw new InternalServerError("Could not load image");
		}
	}

	@RequestMapping(value = "/audio", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] testaudio() {
		Path path = Paths.get("media/audio/example.mp3");
	    try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new byte[1];
	}
	
	@RequestMapping(value = "/video", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] testvideo() {
		Path path = Paths.get("media/video/example.mp4");
	    try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new byte[1];
	}
}
