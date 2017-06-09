package de.p39.asrs.server.controller.db.dao;

import java.util.List;

import de.p39.asrs.server.model.media.Audio;
import de.p39.asrs.server.model.media.Medium;
import de.p39.asrs.server.model.media.Picture;
import de.p39.asrs.server.model.media.Text;
import de.p39.asrs.server.model.media.Video;

public interface MediumDAO {

	public List<Audio> getAllAudios();
	
	public List<Text> getAllTexts();
	
	public List<Video> getAllVideos();
	
	public List<Picture> getAllPictures();
	
	public List<Medium> getAllMedia();
}
