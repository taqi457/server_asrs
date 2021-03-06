package de.p39.asrs.server.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import de.p39.asrs.server.model.media.Picture;

/**
 * 
 * @author Adrian Rebmann <adrianrebmann@gmail.com>
 *
 */
@Entity
public class Site extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4050480950169388654L;

	private Coordinate coordinate;
	
	private Picture thumbnail;

	private Set<Picture> pictures=new HashSet<>();

	private boolean isCompleted;

	private Set<SiteCategory> categories;
	
	private Map<String, String> meta = new HashMap<>();
	
	private Long route;

	public Site() {
		super();
	}

	/**
	 * @return the coordinate
	 */
	@OneToOne(cascade = CascadeType.ALL)
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate
	 *            the coordinate to set
	 */
	@OneToOne(cascade = CascadeType.ALL)
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * @return the pictures
	 */
	@OneToMany(targetEntity = Picture.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Picture> getPictures() {
		return pictures;
	}

	/**
	 * @param pictures
	 *            the pictures to set
	 */
	@OneToMany(targetEntity = Picture.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
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
	 * @return the names
	 */
	@OneToMany(targetEntity = LocaleName.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<LocaleName> getNames() {
		return names;
	}

	/**
	 * @param names
	 *            the names to set
	 */
	@OneToMany(targetEntity = LocaleName.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public void setNames(List<LocaleName> names) {
		this.names = names;
	}

	/**
	 * @return the descriptions
	 */
	@OneToMany(targetEntity = LocaleDescription.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<LocaleDescription> getDescriptions() {
		return descriptions;
	}

	/**
	 * @param descriptions
	 *            the descriptions to set
	 */
	@OneToMany(targetEntity = LocaleDescription.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public void setDescriptions(List<LocaleDescription> descriptions) {
		this.descriptions = descriptions;
	}
	
	@OneToMany(targetEntity = LocaleAudio.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<LocaleAudio> getAudios() {
		return audios;
	}
	@OneToMany(targetEntity = LocaleAudio.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public void setAudios(List<LocaleAudio> audios) {
		this.audios = audios;
	}

	/**
	 * a site is completed if it has the names and descriptions in all the
	 * required languages and has a category and has a coordinate and has at
	 * least one medium
	 * 
	 * @return
	 */
	private boolean checkCompleted() {
		if(this.descriptions.size()<3)
			return false;
		if(this.names.size()<3)
			return false;
		for (LocaleDescription ld : this.descriptions) {
			if (ld == null || ld.getString() == null || ld.getLocale() == null) {
				return false;
			}
		}
		for (LocaleName ln : this.names) {
			if (ln == null || ln.getString() == null || ln.getLocale() == null) {
				return false;
			}
		}
		if (this.categories == null)
			return false;
		if (this.coordinate == null)
			return false;
		if (this.thumbnail == null)
			return false;
		return true;
	}

	public boolean isCompleted() {
		this.setCompleted(this.checkCompleted());
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
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

	@ElementCollection(fetch = FetchType.EAGER)
	public Map<String, String> getMeta() {
		return meta;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}
	
	public void addMetaData(String title, String content){
		this.meta.put(title, content);
	}
	
	public String getMetaData(String title){
		if(this.meta.get(title)==null)
			return "";
		else return this.meta.get(title);
	}
	
	public void removeMeta(String title){
		this.meta.remove(title);
	}

	public void addPicture(Picture picture) {
		if(!this.pictures.contains(picture))
			this.pictures.add(picture);
	}
	
	public void removePicture(Picture picture){
		if(this.pictures.contains(picture)){
			System.out.println("picture is removed");
			this.pictures.remove(picture);
		}
			
	}

	@OneToOne(cascade = CascadeType.ALL,targetEntity= Picture.class,fetch = FetchType.EAGER)
	public Picture getThumbnail() {
		return thumbnail;
	}

	@OneToOne(cascade = CascadeType.ALL,targetEntity=Picture.class,fetch = FetchType.EAGER)
	public void setThumbnail(Picture thumbnail) {
		this.thumbnail = thumbnail;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	public Set<SiteCategory> getCategories() {
		return categories;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	public void setCategories(Set<SiteCategory> categories) {
		this.categories = categories;
	}

	public Long getRoute() {
		return route;
	}

	public void setRoute(Long route) {
		this.route = route;
	}
	
	
}
