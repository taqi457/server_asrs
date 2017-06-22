package de.p39.asrs.server.controller.input.info;

public class RouteInfo {
	
	private String nameDE;
	private String nameFR;
	private String nameEN;
	
	private String descriptionDE;
	private String descriptionFR;
	private String descriptionEN;
	
	private Long durationByFoot;
	private Long durationByBike;
	private Double amplitude;
	
	
	
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
	public Long getDurationByFoot() {
		return durationByFoot;
	}
	public void setDurationByFoot(Long durationByFoot) {
		this.durationByFoot = durationByFoot;
	}
	public Long getDurationByBike() {
		return durationByBike;
	}
	public void setDurationByBike(Long durationByBike) {
		this.durationByBike = durationByBike;
	}
	public Double getAmplitude() {
		return amplitude;
	}
	public void setAmplitude(Double amplitude) {
		this.amplitude = amplitude;
	}
	
	

}
