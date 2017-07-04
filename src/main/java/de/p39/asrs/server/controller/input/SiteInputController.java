package de.p39.asrs.server.controller.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.sun.org.apache.xpath.internal.operations.Mult;
import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.input.info.PictureInfo;
import de.p39.asrs.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import de.p39.asrs.server.controller.db.dao.SiteDAO;
import de.p39.asrs.server.controller.exceptions.StorageException;
import de.p39.asrs.server.controller.file.FileSystemStorage;
import de.p39.asrs.server.controller.file.FileType;
import de.p39.asrs.server.controller.input.info.SiteInfo;
import de.p39.asrs.server.model.media.Audio;
import de.p39.asrs.server.model.media.Picture;
import de.p39.asrs.server.model.media.Size;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author adrianrebmann
 */
@Controller
public class SiteInputController {
    private CategoryDAO categoryDAO;
    private MediumDAO mediumDAO;
    private SiteDAO sitedao;
    private FileSystemStorage storageService;


    @Autowired
    public SiteInputController(SiteDAO sdao, CategoryDAO cdao, MediumDAO mdao, FileSystemStorage storage) {
        super();
        this.sitedao = sdao;
        this.storageService = storage;
        this.categoryDAO = cdao;
        this.mediumDAO = mdao;
    }

    @GetMapping("/deletesite/{id}")
    public String handleSiteDelete(@PathVariable("id") Long id) {
        sitedao.deleteSite(id);
        return "redirect:/siteoverview";
    }

    @GetMapping("/deleteaudio/{id}")
    public String handleAudioDelete(@PathVariable("id") Long id) {
        //TODO do this differently->so this option is only possible for pictures
        return "redirect:/site";
    }

    @GetMapping("/deletepicture/{site}/{id}")
    public String handlePictureDelete(@PathVariable("site") Long site, @PathVariable("id") Long id) {
        Site s = this.sitedao.getSiteById(site);
        System.out.println(s.getPictures().size());
        for (Picture p : s.getPictures()) {
            if (p.getId().equals(id)) {
                this.sitedao.removePicture(s, p);
                this.storageService.delete(p.getPath(Size.LARGE));
                break;
            }
        }
        System.out.println(s.getPictures().size());
        return "redirect:/siteedit/" + site + "/";
    }


    @PostMapping("/newsite")
    public String handleSiteInfo(@ModelAttribute SiteInfo info, @RequestParam("audio_de") MultipartFile audio_de,
                                 @RequestParam("audio_en") MultipartFile audio_en, @RequestParam("audio_fr") MultipartFile audio_fr,
                                 @RequestParam("picture") MultipartFile picture, @RequestParam("button") String button, Model model) {
        MultipartFile[] audios = {audio_de, audio_fr, audio_en};
        Site site = this.create(info, audios, picture);
        Site new_site = sitedao.insertSite(site);
        if (button.equals("complete"))
            return "redirect:siteedit/" + new_site.getId();
        else if(button.equals("upload")){
            model.addAttribute("id", site.getId());
            model.addAttribute("PictureInfo", new PictureInfo());
            return "uploadpicture";
        }
        return "redirect:siteoverview";
    }

    @PostMapping("editsite")
    public String handleSiteEdit(@ModelAttribute SiteInfo info, @RequestParam("audio_de") MultipartFile audio_de,
                                 @RequestParam("audio_en") MultipartFile audio_en, @RequestParam("audio_fr") MultipartFile audio_fr,
                                 @RequestParam("pictures") MultipartFile picture, Model model, @RequestParam("id")
                                         Long id, @RequestParam("category") Long category, @RequestParam("button") String button) {
        MultipartFile[] audios = {audio_de, audio_fr, audio_en};
        Site site = this.edit(info, id, audios, picture, category);
        site = sitedao.updateSite(site);
        if (button.equals("complete"))
            return "redirect:siteedit/" + site.getId();
        else if(button.equals("upload")){
            model.addAttribute("id", site.getId());
            model.addAttribute("PictureInfo", new PictureInfo());
            return "uploadpicture";
        }
        return "redirect:siteoverview";
    }

    private Site create(SiteInfo info, MultipartFile[] audios, MultipartFile picture) {
        Site site = new Site();
        this.uploadMedia(site, audios, picture);
        this.addInfo(site, info);
        return site;
    }

    private Site edit(SiteInfo info, Long id, MultipartFile[] audios, MultipartFile picture, Long category) {
        Site site = sitedao.getSiteById(id);
        System.out.println(site.getPictures().size());
        this.uploadMedia(site, audios, picture);
        this.addInfo(site, info);
        site.setCategory(categoryDAO.getCategoryById(category));
        System.out.println(site.getPictures().size());
        return site;
    }

    private void uploadMedia(Site site, MultipartFile[] audios, MultipartFile p) {
        for (int i = 0; i < audios.length; i++) {
            if (audios[i].isEmpty()) {
                site.addLocaleAudio(site.getAudios().get(i));
                continue;
            }
            String path = storageService.store(audios[i], FileType.AUDIO);
            Audio audio = new Audio();
            audio.setPath(path);
            List<LocaleName> names = new ArrayList<>();
            names.add(new LocaleName(Locale.GERMAN, audios[i].getOriginalFilename()));
            audio.setNames(names);
            if (i == 0){
                site.addLocaleAudio(new LocaleAudio(Locale.GERMAN, audio));
            }
            else if (i == 1){
                site.addLocaleAudio(new LocaleAudio(Locale.FRENCH, audio));
            }
            else if (i==2){
                site.addLocaleAudio(new LocaleAudio(Locale.ENGLISH, audio));
            }
        }
        if (p.isEmpty())
            return;
        String path = storageService.store(p, FileType.PICTURE);
        Picture picture = new Picture();
        //TODO create different sizes and add paths like this:
        picture.addPath(Size.LARGE, path);
        List<LocaleName> names = new ArrayList<>();
        names.add(new LocaleName(Locale.GERMAN, p.getOriginalFilename()));
        picture.setNames(names);
        site.addPicture(picture);
        //sitedao.addPicture(site,picture);

    }

    private void uploadMediaPicture(Site site, MultipartFile[] audios, MultipartFile p, Picture picture) {
        for (int i = 0; i < audios.length; i++) {
            if (audios[i].isEmpty()) {
                picture.addLocaleAudio(picture.getAudios().get(i));
                continue;
            }
            String path = storageService.store(audios[i], FileType.AUDIO);
            Audio audio = new Audio();
            audio.setPath(path);
            List<LocaleName> names = new ArrayList<>();
            names.add(new LocaleName(Locale.GERMAN, audios[i].getOriginalFilename()));
            audio.setNames(names);
            if (i == 0){
                picture.addLocaleAudio(new LocaleAudio(Locale.GERMAN, audio));
            }
            else if (i == 1){
                picture.addLocaleAudio(new LocaleAudio(Locale.FRENCH, audio));
            }
            else if (i==2){
                picture.addLocaleAudio(new LocaleAudio(Locale.ENGLISH, audio));
            }
        }
        if (p.isEmpty())
            return;
        String path = storageService.store(p, FileType.PICTURE);
        //TODO create different sizes and add paths like this:
        picture.addPath(Size.LARGE, path);
        List<LocaleName> names = new ArrayList<>();
        names.add(new LocaleName(Locale.GERMAN, p.getOriginalFilename()));
        picture.setNames(names);
        site.addPicture(picture);
        sitedao.addPicture(site,picture);

    }

    private Site addInfo(Site s, SiteInfo info) {
        List<LocaleName> names = new ArrayList<LocaleName>();
        List<LocaleDescription> descriptions = new ArrayList<LocaleDescription>();
        if (info.getNameDE() != null) {
            LocaleName name = new LocaleName(Locale.GERMAN, info.getNameDE());
            names.add(name);
        }
        if (info.getDescriptionEN() != null) {
            LocaleName name = new LocaleName(Locale.ENGLISH, info.getNameEN());
            names.add(name);

        }
        if (info.getNameFR() != null) {
            LocaleName name = new LocaleName(Locale.FRENCH, info.getNameFR());
            names.add(name);

        }
        if (info.getDescriptionDE() != null) {
            LocaleDescription description = new LocaleDescription(Locale.GERMAN, info.getDescriptionDE());
            descriptions.add(description);
        }
        if (info.getDescriptionEN() != null) {
            LocaleDescription description = new LocaleDescription(Locale.ENGLISH, info.getDescriptionEN());
            descriptions.add(description);
        }
        if (info.getDescriptionFR() != null) {
            LocaleDescription description = new LocaleDescription(Locale.FRENCH, info.getDescriptionFR());
            descriptions.add(description);
        }
        if (info.getLatitude() != null && info.getLongitude() != null) {
            Coordinate coordinate = new Coordinate(info.getLatitude(), info.getLongitude());
            s.setCoordinate(coordinate);
        }
        if (info.getCategory() != null) {
            s.setCategory(categoryDAO.getCategoryById(info.getCategory()));
        }
        if (info.getWebsite() != null) {
            s.addMetaData("website", info.getWebsite());
        }
        if (info.getStreet() != null) {
            s.addMetaData("street", info.getStreet());
        }
        if (info.getZip() != null) {
            s.addMetaData("zip", info.getZip());
        }
        if (info.getCity() != null) {
            s.addMetaData("city", info.getCity());
        }
        if (info.getCountry() != null) {
            s.addMetaData("country", info.getCountry());
        }
        s.setDescriptions(descriptions);
        s.setNames(names);
        return s;
    }

    private Picture addInfoPicture(Picture p, PictureInfo info) {
        List<LocaleDescription> descriptions = new ArrayList<LocaleDescription>();
        if (info.getGermanDescription() != null) {
            LocaleDescription description = new LocaleDescription(Locale.GERMAN, info.getGermanDescription());
            descriptions.add(description);
        }
        if (info.getEnglishDescription() != null) {
            LocaleDescription description = new LocaleDescription(Locale.ENGLISH, info.getEnglishDescription());
            descriptions.add(description);
        }
        if (info.getFrenchDescription() != null) {
            LocaleDescription description = new LocaleDescription(Locale.FRENCH, info.getFrenchDescription());
            descriptions.add(description);
        }
        p.setDescriptions(descriptions);
        return p;
    }

    @GetMapping("site/pictures/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> servePicture(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(FileType.PICTURE, filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping("newpicture")
    public String HandlePictureUpload(@ModelAttribute PictureInfo info, Model model, @RequestParam("audio_de") MultipartFile audio_de,
                                      @RequestParam("audio_fr") MultipartFile audio_fr, @RequestParam("audio_en") MultipartFile audio_en,
                                      @RequestParam("picture") MultipartFile picture, @RequestParam("id") Long id, @RequestParam("button") String button){
        Picture p = new Picture();
        MultipartFile[] audios = {audio_de, audio_fr, audio_en};
        p = addInfoPicture(p, info);
        this.uploadMediaPicture(sitedao.getSiteById(id), audios, picture, p);
        if (button.equals("complete"))
            return "redirect:siteedit/" + id;
        else if(button.equals("upload")){
            model.addAttribute("id", id);
            model.addAttribute("PictureInfo", new PictureInfo());
            return "uploadpicture";
        }
        return "redirect:siteoverview";
    }

    @GetMapping("siteedit/{siteid}/editpicture/{pictureid}")
    public String GetSiteEditPicture(Model model, @PathVariable("siteid") Long siteid, @PathVariable("pictureid") Long pictureid){
        Picture picture = mediumDAO.getPictureById(pictureid);
        model.addAttribute("picture", picture);
        model.addAttribute("siteid", siteid);

        return "editpicture";
    }

    @PostMapping("editpicture")
    public String HandlePictureUpload(@ModelAttribute PictureInfo info, Model model, @RequestParam("audio_de") MultipartFile audio_de,
                                      @RequestParam("audio_fr") MultipartFile audio_fr, @RequestParam("audio_en") MultipartFile audio_en,
                                      @RequestParam("picture") MultipartFile picture, @RequestParam("siteid") Long siteid,@RequestParam("pictureid") Long pictureid){
        Picture p = mediumDAO.getPictureById(pictureid);
        MultipartFile[] audios = {audio_de, audio_fr, audio_en};
        p = addInfoPicture(p, info);
        this.uploadMediaPicture(sitedao.getSiteById(siteid), audios, picture, p);

        return "redirect:siteedit/"+ siteid +"/editpicture/"+ pictureid;
    }

	/*
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
			picture.addPath(Size.LARGE,path);
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
	}*/


    @ExceptionHandler(StorageException.class)
    public ResponseEntity<Object> handleStorageFileNotFound(StorageException exc) {
        return ResponseEntity.notFound().build();
    }


}
