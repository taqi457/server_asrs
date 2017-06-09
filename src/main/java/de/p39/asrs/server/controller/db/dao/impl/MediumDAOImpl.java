package de.p39.asrs.server.controller.db.dao.impl;

import java.util.ArrayList;
import java.util.List;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.model.media.Audio;
import de.p39.asrs.server.model.media.Medium;
import de.p39.asrs.server.model.media.Picture;
import de.p39.asrs.server.model.media.Text;
import de.p39.asrs.server.model.media.Video;

public class MediumDAOImpl implements MediumDAO {

	private CrudFacade cf;
	
	public MediumDAOImpl(CrudFacade cf) {
		super();
		this.cf=cf;
	}

	@Override
	public List<Audio> getAllAudios() {
		return this.cf.findAll(Audio.class);
	}

	@Override
	public List<Text> getAllTexts() {
		return this.cf.findAll(Text.class);
	}

	@Override
	public List<Video> getAllVideos() {
		return this.cf.findAll(Video.class);
	}

	@Override
	public List<Picture> getAllPictures() {
		return this.cf.findAll(Picture.class);
	}

	@Override
	public List<Medium> getAllMedia() {
		List<Medium> res = new ArrayList<>();
		res.addAll(this.getAllAudios());
		res.addAll(this.getAllPictures());
		res.addAll(this.getAllTexts());
		res.addAll(this.getAllVideos());
		return res;
	}

	@Override
	public void insertAudio(Audio a) {
		this.cf.create(a);
	}

	@Override
	public void insertText(Text t) {
		this.cf.create(t);
	}

	@Override
	public void insertVideo(Video v) {
		this.cf.create(v);
	}

	@Override
	public void insertPicture(Picture p) {
		this.cf.create(p);
	}

	@Override
	public void updateAudio(Audio a) {
		this.cf.update(a);
	}

	@Override
	public void updateText(Text t) {
		this.cf.update(t);
	}

	@Override
	public void updateVideo(Video v) {
		this.cf.update(v);
	}

	@Override
	public void updatePicture(Picture p) {
		this.cf.update(p);
	}

	@Override
	public void deleteAudio(Long id) {
		this.cf.delete(id, Audio.class);
	}

	@Override
	public void deleteText(Long id) {
		this.cf.delete(id, Text.class);
	}

	@Override
	public void deleteVideo(Long id) {
		this.cf.delete(id, Video.class);
	}

	@Override
	public void deletePicture(Long id) {
		this.cf.delete(id, Picture.class);
	}
}
