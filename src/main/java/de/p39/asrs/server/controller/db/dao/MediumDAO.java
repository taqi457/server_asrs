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

	public void insertAudio(Audio a);

	public void insertText(Text t);

	public void insertVideo(Video v);

	public void insertPicture(Picture p);

	public void updateAudio(Audio a);

	public void updateText(Text t);

	public void updateVideo(Video v);

	public void updatePicture(Picture p);

	public void deleteAudio(Long a);

	public void deleteText(Long t);

	public void deleteVideo(Long v);

	public void deletePicture(Long p);
}
