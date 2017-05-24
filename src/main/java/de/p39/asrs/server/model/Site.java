package de.p39.asrs.server.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
/**
 * 
 * @author Adrian Rebmann <adrianrebmann@gmail.com>
 *
 */
@Entity
public class Site extends ABasicObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4050480950169388654L;

	@OneToOne
	private Coordinate coordinate;
	@OneToMany(targetEntity=Audio.class, fetch=FetchType.EAGER)
	private List<Audio> audios;
	@OneToMany(targetEntity=Video.class, fetch=FetchType.EAGER)
	private List<Video> videos;
	@OneToMany(targetEntity=Text.class, fetch=FetchType.EAGER)
	private List<Text> texts;
	@OneToMany(targetEntity=Picture.class, fetch=FetchType.EAGER)
	private List<Picture> pictures;
	@ManyToOne(targetEntity=Category.class, fetch=FetchType.EAGER)
	private Category category;

	public Site(Long id, String name,Category category) {
		super(id, name);
		this.category=category;
		this.init();
	}
	
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

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Id
	@GeneratedValue
	public Long getId(){
		return this.getId();
	}
	
	@Id
	@GeneratedValue
	public void setId(Long id){
		this.setId(id);
	}

}
