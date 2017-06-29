package de.p39.asrs.server.controller.input.info;

import de.p39.asrs.server.model.media.Audio;
import org.springframework.web.multipart.MultipartFile;

public class SiteInfo {
	
	private Double latitude;
	private Double longitude;
	private String website;
	
	private String nameDE;
	private String nameFR;
	private String nameEN;
	
	private String descriptionDE;
	private String descriptionFR;
	private String descriptionEN;

	private Long category;

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	/*private MultipartFile[] audios;
	private MultipartFile[] pictures;*/

	public String getNameDE() {
		return nameDE;
	}
	public void setNameDE(String nameDE) {
		this.nameDE = nameDE;
	}
	public String getNameFR() {
		return nameFR;
	}
	public void setNameFR(String nameFR) {
		this.nameFR = nameFR;
	}
	public String getNameEN() {
		return nameEN;
	}
	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}
	public String getDescriptionDE() {
		return descriptionDE;
	}
	public void setDescriptionDE(String descriptionDE) {
		this.descriptionDE = descriptionDE;
	}
	public String getDescriptionFR() {
		return descriptionFR;
	}
	public void setDescriptionFR(String descriptionFR) {
		this.descriptionFR = descriptionFR;
	}
	public String getDescriptionEN() {
		return descriptionEN;
	}
	public void setDescriptionEN(String descriptionEN) {
		this.descriptionEN = descriptionEN;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	/*public MultipartFile[] getAudios(){return audios;};
	public void setAudios(MultipartFile[] audios){this.audios = audios; }
	public MultipartFile[] getPictures(){return pictures;};
	public void setPictures(MultipartFile[] pictures){this.pictures = pictures; }*/



}
