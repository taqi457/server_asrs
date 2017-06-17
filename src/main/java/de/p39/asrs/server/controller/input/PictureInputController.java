package de.p39.asrs.server.controller.input;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.p39.asrs.server.controller.ApplicationConstants;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.exceptions.StorageException;
import de.p39.asrs.server.controller.file.FileSystemStorage;
import de.p39.asrs.server.controller.file.FileType;
import de.p39.asrs.server.model.LocaleDescription;
import de.p39.asrs.server.model.LocaleName;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.media.Picture;
import de.p39.asrs.server.model.media.Text;

/**
 * 
 * @author adrianrebmann
 *
 */
@Controller
public class PictureInputController {

	private MediumDAO dao;
	private FileSystemStorage storageService;

	private String germanName;
	private String englishName;
	private String frenchName;
	
	private String germanDescription;
	private String englishDescription;
	private String frenchDescription;

	private Part picture;

	private boolean upladed;

	@Autowired
	public PictureInputController(MediumDAO dao, FileSystemStorage storage) {
		super();
		this.dao = dao;
		this.storageService = storage;
	}

	@Deprecated
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
			upladed = true;
		} catch (IOException e) {
			// TODO log this
			e.printStackTrace();
		}
	}

	@GetMapping("/pictures/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(FileType.PICTURE,filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/picture")
	public String handleFileUploadAndCreatePicture(@RequestParam("picture") MultipartFile file, RedirectAttributes redirectAttributes) {
		String path = storageService.store(file, FileType.PICTURE);
		Picture picture = new Picture();
		List<Picture> exists= this.dao.getPictureByPath(path);
		if(!exists.isEmpty()){
			Picture existing = exists.get(0);
			this.dao.deletePicture(existing.getId());
		}
		if(path!= null){
			picture.setPath(path);
		}
		this.addNamesAndDescriptions(picture);
		//System.out.println(path);
		this.dao.insertPicture(picture);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		return "redirect:/upload";
	}
	
	private Picture addNamesAndDescriptions(Picture r) {
		Text t = new Text();
		if (this.englishName != null) {
			LocaleName name = new LocaleName(Locale.GERMAN, this.germanName);
			r.addLocaleName(name);
			t.addLocaleName(name);
		}
		if (this.germanName != null) {
			LocaleName name = new LocaleName(Locale.ENGLISH, this.englishName);
			r.addLocaleName(name);
			t.addLocaleName(name);
		}
		if (this.frenchName != null) {
			LocaleName name = new LocaleName(Locale.FRENCH, this.frenchName);
			r.addLocaleName(name);
			t.addLocaleName(name);
		}
		if (this.englishDescription != null) {
			LocaleDescription description = new LocaleDescription(Locale.GERMAN, this.germanDescription);
			r.addLocaleDescription(description);
			t.addLocaleDescription(description);
		}
		if (this.germanDescription != null) {
			LocaleDescription description = new LocaleDescription(Locale.ENGLISH, this.englishDescription);
			r.addLocaleDescription(description);
			t.addLocaleDescription(description);
		}
		if (this.frenchDescription != null) {
			LocaleDescription description = new LocaleDescription(Locale.FRENCH, this.frenchDescription);
			r.addLocaleDescription(description);
			t.addLocaleDescription(description);
		}
		r.setText(t);
		return r;
	}

	@ExceptionHandler(StorageException.class)
	public ResponseEntity handleStorageFileNotFound(StorageException exc) {
		return ResponseEntity.notFound().build();
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
	 * @param germanName
	 *            the germanName to set
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
	 * @param englishName
	 *            the englishName to set
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
	 * @param frenchName
	 *            the frenchName to set
	 */
	public void setFrenchName(String frenchName) {
		this.frenchName = frenchName;
	}
	
	

	public String getGermanDescription() {
		return germanDescription;
	}

	public void setGermanDescription(String germanDescription) {
		this.germanDescription = germanDescription;
	}

	public String getEnglishDescription() {
		return englishDescription;
	}

	public void setEnglishDescription(String englishDescription) {
		this.englishDescription = englishDescription;
	}

	public String getFrenchDescription() {
		return frenchDescription;
	}

	public void setFrenchDescription(String frenchDescription) {
		this.frenchDescription = frenchDescription;
	}

	/**
	 * @return the picture
	 */
	public Part getPicture() {
		return picture;
	}

	/**
	 * @param picture
	 *            the picture to set
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
	 * @param upladed
	 *            the upladed to set
	 */
	public void setUpladed(boolean upladed) {
		this.upladed = upladed;
	}

}
