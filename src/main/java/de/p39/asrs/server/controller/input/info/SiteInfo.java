package de.p39.asrs.server.controller.input.info;

import de.p39.asrs.server.model.media.Audio;
import org.springframework.web.multipart.MultipartFile;

public class SiteInfo {
	
	private Long latitude;
	private Long longitude;
	
	private String nameDE;
	private String nameFR;
	private String nameEN;
	
	private String descriptionDE;
	private String descriptionFR;
	private String descriptionEN;

	private MultipartFile[] audios;
	private MultipartFile[] pictures;

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
	public Long getLatitude() {
		return latitude;
	}
	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	public Long getLongitude() {
		return longitude;
	}
	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}
	public MultipartFile[] getAudios(){return audios;};
	public void setAudios(MultipartFile[] audios){this.audios = audios; }
	public MultipartFile[] getPictures(){return pictures;};
	public void setPictures(MultipartFile[] pictures){this.pictures = pictures; }



}
