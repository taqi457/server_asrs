package de.p39.asrs.server.controller.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.p39.asrs.server.controller.input.info.AudioInfo;
import de.p39.asrs.server.controller.input.info.PictureInfo;
import de.p39.asrs.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.controller.exceptions.StorageException;
import de.p39.asrs.server.controller.file.FileSystemStorage;
import de.p39.asrs.server.controller.file.FileType;
import de.p39.asrs.server.controller.input.info.RouteInfo;
import de.p39.asrs.server.controller.input.info.SiteInfo;
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
	
	
	private List<Picture> selectedPictures;
	private List<Audio> selectedAudios;
	private List<Text> selectedTexts;
	private List<Video> selectedVideos;
	private Site site;
	
	private MediumDAO mediadao;
	private SiteDAO sitedao;
	private FileSystemStorage storageService;
	

	@Autowired
	public SiteInputController(SiteDAO sdao, MediumDAO mdao, FileSystemStorage storage) {
		super();
		this.sitedao=sdao;
		this.mediadao=mdao;
		this.init();
	}
	
	private void init(){
		this.selectedAudios=new ArrayList<>();
		this.selectedPictures=new ArrayList<>();
		this.selectedTexts=new ArrayList<>();
		this.selectedVideos=new ArrayList<>();
	}
	
	@PostMapping("/newsite")
	public String handleSiteInfo(@ModelAttribute SiteInfo info, @RequestParam("audios") MultipartFile[] audios,
								 @RequestParam("pictures") MultipartFile[] pictures, Model model) {
		this.create(info, audios, pictures);
		model.addAttribute("siteInfo", new SiteInfo());
		return "/siteoverview";
	}
	
	private void create(SiteInfo info, MultipartFile[] audios, MultipartFile[] pictures) {
		this.site=new Site();
		for(MultipartFile a : audios){
			String path = storageService.store(a, FileType.AUDIO);
			Audio audio = new Audio();
			audio.setPath(path);
			ArrayList<LocaleName> names = new ArrayList<>();
			names.add(new LocaleName(Locale.GERMAN, a.getOriginalFilename()));
			audio.setNames(names);
			site.addMedium(audio);
		}
		for(MultipartFile p : pictures){
			String path = storageService.store(p, FileType.PICTURE);
			Picture picture = new Picture();
			picture.setPath(path);
			ArrayList<LocaleName> names = new ArrayList<>();
			names.add(new LocaleName(Locale.GERMAN, p.getOriginalFilename()));
			picture.setNames(names);
			site.addMedium(picture);
		}
		this.addInfo(site, info);
	}
	
	private Site addInfo(Site s, SiteInfo info) {
		if (info.getNameDE() != null) {
			LocaleName name = new LocaleName(Locale.GERMAN, info.getNameDE());
			s.addLocaleName(name);
		}
		if (info.getDescriptionEN() != null) {
			LocaleName name = new LocaleName(Locale.ENGLISH, info.getNameEN());
			s.addLocaleName(name);
		}
		if (info.getNameFR() != null) {
			LocaleName name = new LocaleName(Locale.FRENCH, info.getNameFR());
			s.addLocaleName(name);
		}
		if (info.getDescriptionDE() != null) {
			LocaleDescription description = new LocaleDescription(Locale.GERMAN, info.getDescriptionDE());
			s.addLocaleDescription(description);
		}
		if (info.getDescriptionEN() != null) {
			LocaleDescription description = new LocaleDescription(Locale.ENGLISH, info.getDescriptionEN());
			s.addLocaleDescription(description);
		}
		if (info.getDescriptionFR() != null) {
			LocaleDescription description = new LocaleDescription(Locale.FRENCH, info.getDescriptionFR());
			s.addLocaleDescription(description);
		}
		if (info.getLatitude() != null && info.getLongitude() != null) {
			//Coordinate coordinate = new Coordinate(info.getLatitude(), info.getLongitude());
			//s.setCoordinate(coordinate);
		}
		
		return s;
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
		this.selectedPictures.add(picture);
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
		this.selectedAudios.add(audio);
		//this.mediadao.insertAudio(audio);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		return "redirect:/upload";
	}
	
	
	@ExceptionHandler(StorageException.class)
	public ResponseEntity<Object> handleStorageFileNotFound(StorageException exc) {
		return ResponseEntity.notFound().build();
	}

	

}
