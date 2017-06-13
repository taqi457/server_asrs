package de.p39.asrs.server.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.p39.asrs.server.model.media.Audio;
import de.p39.asrs.server.model.media.Medium;
import de.p39.asrs.server.model.media.Picture;
import de.p39.asrs.server.model.media.Text;
import de.p39.asrs.server.model.media.Video;

/**
 * 
 * @author Adrian Rebmann <adrianrebmann@gmail.com>
 *
 */
@Entity
public class Site extends ANamedObject<Long> {

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

	public Site() {
		super();
		this.init();
	}
	
	public Site(String name){
		super(name);
		this.init();
	}


	private void init() {
		this.audios = new HashSet<>();
		this.videos = new HashSet<>();
		this.pictures = new HashSet<>();
		this.texts = new HashSet<>();
	}

	/**
	 * @return the coordinate
	 */
	@OneToOne(cascade=CascadeType.ALL)
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate
	 *            the coordinate to set
	 */
	@OneToOne(cascade=CascadeType.ALL)
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * @return the audios
	 */
	@OneToMany(targetEntity = Audio.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Audio> getAudios() {
		return audios;
	}

	/**
	 * @param audios
	 *            the audios to set
	 */
	@OneToMany(targetEntity = Audio.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public void setAudios(Set<Audio> audios) {
		this.audios = audios;
	}

	/**
	 * @return the videos
	 */
	@OneToMany(targetEntity = Video.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Video> getVideos() {
		return videos;
	}

	/**
	 * @param videos
	 *            the videos to set
	 */
	@OneToMany(targetEntity = Video.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	/**
	 * @return the texts
	 */
	@OneToMany(targetEntity = Text.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Text> getTexts() {
		return texts;
	}

	/**
	 * @param texts
	 *            the texts to set
	 */
	@OneToMany(targetEntity = Text.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public void setTexts(Set<Text> texts) {
		this.texts = texts;
	}

	/**
	 * @return the pictures
	 */
	@OneToMany(targetEntity = Picture.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Picture> getPictures() {
		return pictures;
	}

	/**
	 * @param pictures
	 *            the pictures to set
	 */
	@OneToMany(targetEntity = Picture.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	/**
	 * @return the category
	 */
	@ManyToOne(targetEntity = Category.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	@ManyToOne(targetEntity = Category.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public void setCategory(Category category) {
		this.category = category;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return super.getId();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public void setId(Long id) {
		super.setId(id);
	}


	/**
	 * @return the timestamp
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTimestamp() {
		return super.getTimestamp();
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public void setTimestamp(Date timestamp) {
		super.setTimestamp(timestamp);
	}
	
	/**
	 * @return the nameDE
	 */
	public String getNameDE() {
		return super.getNameDE();
	}

	/**
	 * @param nameDE
	 *            the nameDE to set
	 */
	public void setNameDE(String nameDE) {
		super.setNameDE(nameDE);
	}

	/**
	 * @return the nameFR
	 */
	public String getNameFR() {
		return super.getNameFR();
	}

	/**
	 * @param nameFR
	 *            the nameFR to set
	 */
	public void setNameFR(String nameFR) {
		super.setNameFR(nameFR);
	}

	/**
	 * @return the nameEN
	 */
	public String getNameEN() {
		return super.getNameEN();
	}

	/**
	 * @param nameEN
	 *            the nameEN to set
	 */
	public void setNameEN(String nameEN) {
		super.setNameEN(nameEN);
	}

	/**
	 * @return the descriptionDE
	 */
	public String getDescriptionDE() {
		return super.getDescriptionDE();
	}

	/**
	 * @param descriptionDE
	 *            the descriptionDE to set
	 */
	public void setDescriptionDE(String descriptionDE) {
		super.setDescriptionDE(descriptionDE);
	}

	/**
	 * @return the descriptionFR
	 */
	public String getDescriptionFR() {
		return super.getDescriptionFR();
	}

	/**
	 * @param descriptionFR
	 *            the descriptionFR to set
	 */
	public void setDescriptionFR(String descriptionFR) {
		super.setDescriptionFR(descriptionFR);
	}

	/**
	 * @return the descriptionEN
	 */
	public String getDescriptionEN() {
		return super.getDescriptionEN();
	}

	/**
	 * @param descriptionEN
	 *            the descriptionEN to set
	 */
	public void setDescriptionEN(String descriptionEN) {
		super.setDescriptionEN(descriptionEN);
	}

	/**
	 * add and remove methods
	 */
	public void addMedium(Medium m) {
		if (m instanceof Text) {
			this.texts.add((Text) m);
		} else if (m instanceof Audio) {
			this.audios.add((Audio) m);
		} else if (m instanceof Video) {
			this.videos.add((Video) m);
		} else if (m instanceof Picture) {
			this.pictures.add((Picture) m);
		}
	}

	public void removeMedium(Medium m) {
		if (m instanceof Text) {
			this.texts.remove((Text) m);
		} else if (m instanceof Audio) {
			this.audios.remove((Audio) m);
		} else if (m instanceof Video) {
			this.videos.remove((Video) m);
		} else if (m instanceof Picture) {
			this.pictures.remove((Picture) m);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Site other = (Site) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		return true;
	}

	public Double calculateDist(Coordinate coord) {
		return coordinate.getDistance(coord);
	}
}
