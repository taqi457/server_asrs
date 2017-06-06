package de.p39.asrs.server.controller.db.dao;

import java.util.List;

import de.p39.asrs.server.model.Audio;
import de.p39.asrs.server.model.Medium;
import de.p39.asrs.server.model.Picture;
import de.p39.asrs.server.model.Text;
import de.p39.asrs.server.model.Video;

public interface MediumDAO {

	public List<Audio> getAllAudios();
	
	public List<Text> getAllTexts();
	
	public List<Video> getAllVideos();
	
	public List<Picture> getAllPictures();
	
	public List<Medium> getAllMedia();
}
