package de.p39.asrs.server.controller.db.dao;

import java.util.List;

import javax.persistence.Query;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.model.media.Audio;
import de.p39.asrs.server.model.media.Picture;

public class MediumDAO {

	private CrudFacade cf;
	
	public MediumDAO(CrudFacade cf) {
		super();
		this.cf=cf;
	}

	public List<Audio> getAllAudios() {
		return this.cf.findAll(Audio.class);
	}

	public List<Picture> getAllPictures() {
		return this.cf.findAll(Picture.class);
	}


	public Picture getPictureById(Long id) {
		return this.cf.find(id, Picture.class);
	}
	
	public Audio getAudioById(Long id){
		return this.cf.find(id, Audio.class);
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
