package de.p39.asrs.server.controller.input;

import java.util.List;

import org.springframework.stereotype.Component;

import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.media.Medium;
/**
 * 
 * @author adrianrebmann
 *
 */
@Component
public class SiteInputController {
	
	private String germanName;
	private String englishName;
	private String frenchName;
	
	private List<Medium> selectedMedia;
	private Category selectedCategory;
	
	private Double longitude;
	private Double latitude;

	public SiteInputController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the germanName
	 */
	public String getGermanName() {
		return germanName;
	}

	/**
	 * @param germanName the germanName to set
	 */
	public void setGermanName(String germanName) {
		this.germanName = germanName;
	}

	/**
	 * @return the englishName
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * @param englishName the englishName to set
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	/**
	 * @return the frenchName
	 */
	public String getFrenchName() {
		return frenchName;
	}

	/**
	 * @param frenchName the frenchName to set
	 */
	public void setFrenchName(String frenchName) {
		this.frenchName = frenchName;
	}

	/**
	 * @return the selectedMedia
	 */
	public List<Medium> getSelectedMedia() {
		return selectedMedia;
	}

	/**
	 * @param selectedMedia the selectedMedia to set
	 */
	public void setSelectedMedia(List<Medium> selectedMedia) {
		this.selectedMedia = selectedMedia;
	}

	/**
	 * @return the selectedCategory
	 */
	public Category getSelectedCategory() {
		return selectedCategory;
	}

	/**
	 * @param selectedCategory the selectedCategory to set
	 */
	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	
	

}
