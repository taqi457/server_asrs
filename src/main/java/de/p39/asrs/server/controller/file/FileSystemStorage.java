package de.p39.asrs.server.controller.file;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import de.p39.asrs.server.controller.ApplicationConstants;
import de.p39.asrs.server.controller.exceptions.StorageException;

/**
 * Based on https://spring.io/guides/gs/uploading-files/
 * 
 * @author adrianrebmann
 *
 */
@Service
public class FileSystemStorage implements Storage {

	final Map<FileType, Path> rootLocations;

	public FileSystemStorage() {
		this.rootLocations = new HashMap<>();
		this.rootLocations.put(FileType.AUDIO, Paths.get(ApplicationConstants.PATH_TO_AUDIOS));
		this.rootLocations.put(FileType.KML, Paths.get(ApplicationConstants.PATH_TO_KLMS));
		this.rootLocations.put(FileType.PICTURE, Paths.get(ApplicationConstants.PATH_TO_PICTURES));
	}

	@Override
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

}
