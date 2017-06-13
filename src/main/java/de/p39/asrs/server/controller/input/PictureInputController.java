package de.p39.asrs.server.controller.input;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.p39.asrs.server.controller.ApplicationConstants;
import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.db.dao.impl.MediumDAOImpl;
import de.p39.asrs.server.model.media.Picture;
/**
 * 
 * @author adrianrebmann
 *
 */
@Component
public class PictureInputController {

	private MediumDAO dao;

	private String germanName;
	private String englishName;
	private String frenchName;

	private Part picture;
	
	private boolean upladed;

	@Autowired
	public PictureInputController(MediumDAOImpl dao) {
		super();
		this.dao = dao;
	}

	public void upload() {
		try {
			System.out.println("upload");
			InputStream in = picture.getInputStream();
			File f = new File(ApplicationConstants.PATH_TO_PICTURES + picture.getSubmittedFileName());
			f.createNewFile();
			FileOutputStream out = new FileOutputStream(f);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			out.close();
			in.close();
			upladed=true;
		} catch (IOException e) {
			// TODO log this
			e.printStackTrace();
		}
	}

	public void create() {
		
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
	 * @return the picture
	 */
	public Part getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(Part picture) {
		this.picture = picture;
	}

	/**
	 * @return the upladed
	 */
	public boolean isUpladed() {
		return upladed;
	}

	/**
	 * @param upladed the upladed to set
	 */
	public void setUpladed(boolean upladed) {
		this.upladed = upladed;
	}
	
	

}
