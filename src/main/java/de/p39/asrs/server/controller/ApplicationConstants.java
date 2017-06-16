package de.p39.asrs.server.controller;

import java.util.HashMap;
import java.util.Map;

import de.p39.asrs.server.controller.file.FileType;

public abstract class ApplicationConstants {
	
	public final static String PATH_TO_FILES = "/Users/adrianrebmann/Desktop/";
	public final static Map<FileType, String> DIRECTORIES;
	static{
		DIRECTORIES = new HashMap<>();
		DIRECTORIES.put(FileType.AUDIO, "audios");
		DIRECTORIES.put(FileType.KML, "kmls");
		DIRECTORIES.put(FileType.PICTURE, "img");
		DIRECTORIES.put(FileType.VIDEO, "videos");
	}

}
