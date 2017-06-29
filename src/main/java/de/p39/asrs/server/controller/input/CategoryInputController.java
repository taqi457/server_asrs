package de.p39.asrs.server.controller.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.p39.asrs.server.controller.db.dao.CategoryDAO;
import de.p39.asrs.server.controller.input.info.*;
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
public class CategoryInputController {

	private Category category;
	private CategoryDAO categoryDAO;

	@Autowired
	public CategoryInputController(CategoryDAO cdao) {
		super();
		this.categoryDAO = cdao;
	}

	@PostMapping("/newcategory")
	public String handleCategoryInfo(@ModelAttribute CategoryInfo info, Model model) {
		this.create(info);
		Category new_Category = categoryDAO.insertCategory(category);
		model.addAttribute("allCategorys", categoryDAO.getAllCategories());
		return "redirect:categoryedit/" + new_Category.getId();
	}
	@PostMapping("editcategory")
	public String handleSiteEdit(@ModelAttribute CategoryInfo info, Model model, @RequestParam("id")
										 Long id, @RequestParam("type") String type){
		this.edit(info,id);
		categoryDAO.updateCategory(category);
		category.setType(CategoryType.SITE);
		model.addAttribute("category", categoryDAO.getCategoryById(id));
		return "redirect:categoryedit/" + id;
	}

	private void create(CategoryInfo info) {
		this.category=new Category();
		this.addInfo(category, info);

	}

	private void edit(CategoryInfo info, Long id) {
		category = categoryDAO.getCategoryById(id);
		this.addInfo(category, info);
	}


	private Category addInfo(Category c, CategoryInfo info) {
		ArrayList<LocaleName> names = new ArrayList<LocaleName>();
		ArrayList<LocaleDescription> descriptions = new ArrayList<LocaleDescription>();
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
		if (info.getType() != null) {
			category.setType(info.getType());
		}
		c.setDescriptions(descriptions);
		c.setNames(names);
		return c;
	}



	@ExceptionHandler(StorageException.class)
	public ResponseEntity<Object> handleStorageFileNotFound(StorageException exc) {
		return ResponseEntity.notFound().build();
	}



}
