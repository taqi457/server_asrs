package de.p39.asrs.server.controller.file;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import java.util.List;
import javax.imageio.ImageIO;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.File;

import de.p39.asrs.server.model.media.Size;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import de.p39.asrs.server.controller.exceptions.StorageException;


/**
 * Based on https://spring.io/guides/gs/uploading-files/
 * 
 * @author adrianrebmann
 *
 */
@Service
public class FileSystemStorage implements Storage {


    private final Map<Size, Integer> sizes;
	private final Map<FileType, Path> rootLocations;

	public FileSystemStorage() {
		this.rootLocations = new HashMap<>();
		this.rootLocations.put(FileType.AUDIO, Paths.get("resources/media/audio"));
		this.rootLocations.put(FileType.KML, Paths.get("resources/kmls"));
		this.rootLocations.put(FileType.PICTURE, Paths.get("resources/media/images"));

		sizes = new HashMap<>();
		sizes.put(Size.SMALL, 300);
		sizes.put(Size.MEDIUM, 720);
		sizes.put(Size.LARGE,1080);
	}

	public String store(MultipartFile file, FileType type) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			Path p = this.rootLocations.get(type).resolve(file.getOriginalFilename());
			Files.copy(file.getInputStream(), p, StandardCopyOption.REPLACE_EXISTING);
			return p.toString();
		} catch (IOException e) {
			// TODO change this way of providing stack trace
			e.printStackTrace();
			throw new StorageException("Failed to store file ");
		}
	}

	public List<String> storePicture(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			List<String> result = new ArrayList<>();

            Image image = ImageIO.read(file.getInputStream());

			for(Size size : Size.values()) {
                Path p = rootLocations.get(FileType.PICTURE).resolve(file.getOriginalFilename()+"_"+size.toString());

                int newWidth = sizes.get(size);
                int scale = newWidth / image.getWidth(null);
                int newHeight = image.getHeight(null) * scale;
                BufferedImage scaledBI = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = scaledBI.createGraphics();
                g.drawImage(image, 0, 0, newWidth, newHeight, null);
                g.dispose();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(scaledBI, "jpg",baos);
                InputStream is = new ByteArrayInputStream(baos.toByteArray());
                Files.copy(is, p, StandardCopyOption.REPLACE_EXISTING);

                result.add(p.toString());
            }
			return result;
		} catch (IOException e) {
			// TODO change this way of providing stack trace
			e.printStackTrace();
			throw new StorageException("Failed to store file ");
		}
	}

	public Stream<Path> loadAll(FileType t) {
		try {
			return Files.walk(this.rootLocations.get(t), 1).filter(path -> !path.equals(this.rootLocations.get(t)))
					.map(path -> this.rootLocations.get(t).relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files");
		}

	}

	public Path load(FileType t, String filename) {
		return rootLocations.get(t).resolve(filename);
	}

	public Resource loadAsResource(FileType t, String filename) {
		try {
			Path file = load(t, filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageException("Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new StorageException("Could not read file: " + filename);
		}
	}
	
	public void delete(FileType t, String filename){
		Path file = load(t, filename);
		try{
			File f = file.toFile();
			f.delete();
		}catch(UnsupportedOperationException | SecurityException e){
			//TODO log this
		}
	}
	
	public void delete(String path){
		Path file = Paths.get(path);
		try{
			File f = file.toFile();
			f.delete();
		}catch(UnsupportedOperationException | SecurityException e){
			//TODO log this
		}
	}

	public void deleteAll(FileType t) {
		FileSystemUtils.deleteRecursively(rootLocations.get(t).toFile());
	}

	public void init() {
		try {
			for (Path p : this.rootLocations.values()) {
				Files.createDirectory(p);
			}
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage");
		}
	}

	public boolean check(MultipartFile file, FileType type) {
		Path p = this.rootLocations.get(type).resolve(file.getOriginalFilename());
		return p.toFile().exists();
	}

}
