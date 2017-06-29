package de.p39.asrs.server.controller.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;
import javax.persistence.Query;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.model.media.Audio;
import de.p39.asrs.server.model.media.Medium;
import de.p39.asrs.server.model.media.Picture;
import de.p39.asrs.server.model.media.Video;

public class MediumDAO {

	private CrudFacade cf;
	
	public MediumDAO(CrudFacade cf) {
		super();
		this.cf=cf;
	}

	public List<Audio> getAllAudios() {
		return this.cf.findAll(Audio.class);
	}
	
	public List<Video> getAllVideos() {
		return this.cf.findAll(Video.class);
	}

	public List<Picture> getAllPictures() {
		return this.cf.findAll(Picture.class);
	}

	public List<Medium> getAllMedia() {
		List<Medium> res = new ArrayList<>();
		res.addAll(this.getAllAudios());
		res.addAll(this.getAllPictures());
		res.addAll(this.getAllVideos());
		return res;
	}

	public void insertAudio(Audio a) {
		this.cf.create(a);
	}

	public void insertVideo(Video v) {
		this.cf.create(v);
	}

	public void insertPicture(Picture p) {
		this.cf.create(p);
	}

	public void updateAudio(Audio a) {
		this.cf.update(a);
	}

	public void updateVideo(Video v) {
		this.cf.update(v);
	}

	public void updatePicture(Picture p) {
		this.cf.update(p);
	}

	public void deleteAudio(Long id) {
		this.cf.delete(id, Audio.class);
	}

	public void deleteVideo(Long id) {
		this.cf.delete(id, Video.class);
	}

	public void deletePicture(Long id) {
		this.cf.delete(id, Picture.class);
	}

	public Picture getPictureById(Long id) {
		return this.cf.find(id, Picture.class);
	}
	
	public Audio getAudioById(long id){
		return this.cf.find(id, Audio.class);
	}
	
	public Video getVideoById(Long id){
		return this.cf.find(id, Video.class);
	}
	
	public List<Picture> getPictureByPath(String path) {
		Query q = this.cf.createQuery("SELECT e FROM " + Picture.class.getName() + " e WHERE path = :path");
		q.setParameter("path", path);
		return (List<Picture>) q.getResultList();
	}

	public List<Audio> getAudiosByPath(String path) {
		Query q = this.cf.createQuery("SELECT e FROM " + Audio.class.getName() + " e WHERE path = :path");
		q.setParameter("path", path);
		return (List<Audio>) q.getResultList();
	}
}
