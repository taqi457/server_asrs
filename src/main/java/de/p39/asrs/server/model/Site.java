package de.p39.asrs.server.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

	private Coordinate coordinate;

	private Set<Audio> audios;

	private Set<Video> videos;

	private Set<Text> texts;

	private Set<Picture> pictures;
	
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
		this.audios=new HashSet<>();
		this.videos=new HashSet<>();
		this.pictures=new HashSet<>();
		this.texts=new HashSet<>();
	}

	/**
	 * @return the coordinate
	 */
	@OneToOne
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate
	 *            the coordinate to set
	 */
	@OneToOne
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * @return the audios
	 */
	@OneToMany(targetEntity=Audio.class, fetch=FetchType.EAGER)
	public Set<Audio> getAudios() {
		return audios;
	}

	/**
	 * @param audios the audios to set
	 */
	@OneToMany(targetEntity=Audio.class, fetch=FetchType.EAGER)
	public void setAudios(Set<Audio> audios) {
		this.audios = audios;
	}

	/**
	 * @return the videos
	 */
	@OneToMany(targetEntity=Video.class, fetch=FetchType.EAGER)
	public Set<Video> getVideos() {
		return videos;
	}

	/**
	 * @param videos the videos to set
	 */
	@OneToMany(targetEntity=Video.class, fetch=FetchType.EAGER)
	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	/**
	 * @return the texts
	 */
	@OneToMany(targetEntity=Text.class, fetch=FetchType.EAGER)
	public Set<Text> getTexts() {
		return texts;
	}

	/**
	 * @param texts the texts to set
	 */
	@OneToMany(targetEntity=Text.class, fetch=FetchType.EAGER)
	public void setTexts(Set<Text> texts) {
		this.texts = texts;
	}

	/**
	 * @return the pictures
	 */
	@OneToMany(targetEntity=Picture.class, fetch=FetchType.EAGER)
	public Set<Picture> getPictures() {
		return pictures;
	}

	/**
	 * @param pictures the pictures to set
	 */
	@OneToMany(targetEntity=Picture.class, fetch=FetchType.EAGER)
	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	/**
	 * @return the category
	 */
	@ManyToOne(targetEntity=Category.class, fetch=FetchType.EAGER)
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	@ManyToOne(targetEntity=Category.class, fetch=FetchType.EAGER)
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId(){
		return this.getId();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public void setId(Long id){
		this.setId(id);
	}

}
