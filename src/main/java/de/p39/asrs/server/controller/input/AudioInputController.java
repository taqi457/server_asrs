package de.p39.asrs.server.controller.input;


import java.util.List;
import java.util.Locale;

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

import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.exceptions.StorageException;
import de.p39.asrs.server.controller.file.FileSystemStorage;
import de.p39.asrs.server.controller.file.FileType;
import de.p39.asrs.server.model.LocaleDescription;
import de.p39.asrs.server.model.LocaleName;
import de.p39.asrs.server.model.Route;
import de.p39.asrs.server.model.media.Audio;
/**
 * 
 * @author adrianrebmann
 *
 */
@Controller
public class AudioInputController {


	private MediumDAO dao;
	private FileSystemStorage storageService;

	private String germanName;
	private String englishName;
	private String frenchName;
	
	private String germanDescription;
	private String englishDescription;
	private String frenchDescription;


	@Autowired
	public AudioInputController(MediumDAO dao, FileSystemStorage storage) {
		super();
		this.dao = dao;
		this.storageService = storage;
	}

	@GetMapping("/audios/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(FileType.AUDIO,filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/audio")
	public String handleFileUploadAndCreatePicture(@RequestParam("picture") MultipartFile file, RedirectAttributes redirectAttributes) {
		String path = storageService.store(file, FileType.PICTURE);
		Audio audio = new Audio();
		List<Audio> exists= this.dao.getAudiosByPath(path);
		if(!exists.isEmpty()){
			Audio existing = exists.get(0);
			this.dao.deleteAudio(existing.getId());
		}
		if(path!= null){
			audio.setPath(path);
		}
		this.addNamesAndDescriptions(audio);
		//System.out.println(path);
		this.dao.insertAudio(audio);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		return "redirect:/upload";
	}
	
	private Audio addNamesAndDescriptions(Audio r) {
		if (this.englishName != null) {
			LocaleName name = new LocaleName(Locale.GERMAN, this.germanName);
			r.addLocaleName(name);
		}
		if (this.germanName != null) {
			LocaleName name = new LocaleName(Locale.ENGLISH, this.englishName);
			r.addLocaleName(name);
		}
		if (this.frenchName != null) {
			LocaleName name = new LocaleName(Locale.FRENCH, this.frenchName);
			r.addLocaleName(name);
		}
		if (this.englishDescription != null) {
			LocaleDescription description = new LocaleDescription(Locale.GERMAN, this.germanDescription);
			r.addLocaleDescription(description);
		}
		if (this.germanDescription != null) {
			LocaleDescription description = new LocaleDescription(Locale.ENGLISH, this.englishDescription);
			r.addLocaleDescription(description);
		}
		if (this.frenchDescription != null) {
			LocaleDescription description = new LocaleDescription(Locale.FRENCH, this.frenchDescription);
			r.addLocaleDescription(description);
		}
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

}
