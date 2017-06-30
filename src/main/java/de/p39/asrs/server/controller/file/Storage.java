package de.p39.asrs.server.controller.file;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface Storage {
	void init();
	
	boolean check(MultipartFile file, FileType type);

    String store(MultipartFile file, FileType type);

    Stream<Path> loadAll(FileType t);

    Path load(FileType t, String filename);

    Resource loadAsResource(FileType t, String filename);

    void deleteAll(FileType t);
    
    void delete(FileType t, String filename);
    
    public void delete(String path);
}
