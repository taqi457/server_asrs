package de.p39.asrs.server.controller.db.dao.impl;

import java.util.ArrayList;
import java.util.List;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.model.Audio;
import de.p39.asrs.server.model.Medium;
import de.p39.asrs.server.model.Picture;
import de.p39.asrs.server.model.Text;
import de.p39.asrs.server.model.Video;

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

}
