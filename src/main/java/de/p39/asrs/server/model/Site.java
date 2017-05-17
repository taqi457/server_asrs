package de.p39.asrs.server.model;

import java.util.ArrayList;
import java.util.List;

public class Site extends ABasicObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4050480950169388654L;

	private Coordinate coordinate;
	private List<Audio> audios;
	private List<Video> videos;
	private List<Text> texts;
	private List<Picture> pictures;

	public Site(Long id, String name) {
		super(id, name);
		this.init();
	}

	private void init() {
		this.audios=new ArrayList<>();
		this.videos=new ArrayList<>();
		this.pictures=new ArrayList<>();
		this.texts=new ArrayList<>();
	}

	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate
	 *            the coordinate to set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * @return the audios
	 */
	public List<Audio> getAudios() {
		return audios;
	}

	/**
	 * @param audios the audios to set
	 */
	public void setAudios(List<Audio> audios) {
		this.audios = audios;
	}

	/**
	 * @return the videos
	 */
	public List<Video> getVideos() {
		return videos;
	}

	/**
	 * @param videos the videos to set
	 */
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	/**
	 * @return the texts
	 */
	public List<Text> getTexts() {
		return texts;
	}

	/**
	 * @param texts the texts to set
	 */
	public void setTexts(List<Text> texts) {
		this.texts = texts;
	}

	/**
	 * @return the pictures
	 */
	public List<Picture> getPictures() {
		return pictures;
	}

	/**
	 * @param pictures the pictures to set
	 */
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

}
