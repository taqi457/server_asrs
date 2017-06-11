package de.p39.asrs.server.controller.input;

import java.util.List;

import javax.servlet.http.Part;

import org.springframework.stereotype.Component;

import de.p39.asrs.server.model.Category;
import de.p39.asrs.server.model.Site;
/**
 * 
 * @author adrianrebmann
 *
 */
@Component
public class RouteInputController {

	private String germanName;
	private String englishName;
	private String frenchName;
	
	private List<Site> selectedSites;
	
	private Category selectedCategory;
	
	private Part kmlDocument;
	
	public RouteInputController() {
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
	 * @return the selectedSites
	 */
	public List<Site> getSelectedSites() {
		return selectedSites;
	}

	/**
	 * @param selectedSites the selectedSites to set
	 */
	public void setSelectedSites(List<Site> selectedSites) {
		this.selectedSites = selectedSites;
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
	 * @return the kmlDocument
	 */
	public Part getKmlDocument() {
		return kmlDocument;
	}

	/**
	 * @param kmlDocument the kmlDocument to set
	 */
	public void setKmlDocument(Part kmlDocument) {
		this.kmlDocument = kmlDocument;
	}
	
	
	

}
