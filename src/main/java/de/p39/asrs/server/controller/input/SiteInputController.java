package de.p39.asrs.server.controller.input;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.controller.exceptions.StorageException;
import de.p39.asrs.server.controller.file.FileSystemStorage;
import de.p39.asrs.server.controller.file.FileType;
import de.p39.asrs.server.model.Site;
import de.p39.asrs.server.model.media.Audio;
import de.p39.asrs.server.model.media.Medium;
import de.p39.asrs.server.model.media.Picture;
import de.p39.asrs.server.model.media.Text;
import de.p39.asrs.server.model.media.Video;
/**
 * 
 * @author adrianrebmann
 *
 */
@Controller
public class SiteInputController {
	
	
	private List<Picture> selectedPicture;
	private List<Audio> selectedAudios;
	private List<Text> selectedTexts;
	private List<Video> selectedVideos;
	private Site site;
	
	private MediumDAO mediadao;
	private SiteDAO sitedao;
	private FileSystemStorage storageService;
	

	public SiteInputController(SiteDAO sdao, MediumDAO mdao, FileSystemStorage storage) {
		super();
		this.sitedao=sdao;
		this.mediadao=mdao;
	}
	
	@GetMapping("site/pictures/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> servePicture(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(FileType.PICTURE,filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/site/picture")
	public String handleFileUploadAndCreatePicture(@RequestParam("picture") MultipartFile file, RedirectAttributes redirectAttributes) {
		String path = storageService.store(file, FileType.PICTURE);
		Picture picture = new Picture();
		List<Picture> exists= this.mediadao.getPictureByPath(path);
		if(!exists.isEmpty()){
			Picture existing = exists.get(0);
			this.mediadao.deletePicture(existing.getId());
		}
		if(path!= null){
			picture.setPath(path);
		}
		
		//this.mediadao.insertPicture(picture);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		return "redirect:/upload";
	}
	
	@GetMapping("site/audios/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveAudio(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(FileType.AUDIO,filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/site/audio")
	public String handleFileUploadAndCreateAudio(@RequestParam("picture") MultipartFile file, RedirectAttributes redirectAttributes) {
		String path = storageService.store(file, FileType.PICTURE);
		Audio audio = new Audio();
		List<Audio> exists= this.mediadao.getAudiosByPath(path);
		if(!exists.isEmpty()){
			Audio existing = exists.get(0);
			this.mediadao.deleteAudio(existing.getId());
		}
		if(path!= null){
			audio.setPath(path);
		}
		
		//this.mediadao.insertAudio(audio);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		return "redirect:/upload";
	}
	
	
	@ExceptionHandler(StorageException.class)
	public ResponseEntity handleStorageFileNotFound(StorageException exc) {
		return ResponseEntity.notFound().build();
	}

	

}
