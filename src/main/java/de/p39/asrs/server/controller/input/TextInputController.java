package de.p39.asrs.server.controller.input;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.controller.db.dao.impl.MediumDAOImpl;
import de.p39.asrs.server.model.LocaleDescription;
import de.p39.asrs.server.model.LocaleName;
import de.p39.asrs.server.model.media.Text;

/**
 * 
 * @author adrianrebmann
 *
 */
@Component
public class TextInputController {

	private MediumDAO dao;

	private String germanName;
	private String englishName;
	private String frenchName;

	private String germanContent;
	private String englishContent;
	private String frenchContent;

	@Autowired
	public TextInputController(MediumDAOImpl dao) {
		super();
		this.dao = dao;
	}

	public Text create() {
		if (germanName != null && frenchName != null && englishName != null && germanContent != null
				&& frenchContent != null && englishContent != null) {
			Text t = new Text();
			LocaleDescription gd = new LocaleDescription();
			gd.setString(germanContent);
			gd.setLocale(Locale.GERMAN);
			LocaleDescription fd = new LocaleDescription();
			fd.setString(frenchContent);
			fd.setLocale(Locale.FRENCH);
			LocaleDescription ed = new LocaleDescription();
			ed.setString(englishContent);
			ed.setLocale(Locale.ENGLISH);
			LocaleName gn = new LocaleName();
			gn.setString(germanName);
			gn.setLocale(Locale.GERMAN);
			LocaleName fn = new LocaleName();
			fn.setString(frenchName);
			fn.setLocale(Locale.FRENCH);
			LocaleName en = new LocaleName();
			en.setString(englishName);
			en.setLocale(Locale.ENGLISH);
			t.addLocaleDescription(gd);
			t.addLocaleDescription(fd);
			t.addLocaleDescription(ed);
			t.addLocaleName(gn);
			t.addLocaleName(fn);
			t.addLocaleName(en);
			dao.insertText(t);
			return t;
		} else {
			// TODO notify user about failure
			return null;
		}
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

	/**
	 * @return the germanContent
	 */
	public String getGermanContent() {
		return germanContent;
	}

	/**
	 * @param germanContent
	 *            the germanContent to set
	 */
	public void setGermanContent(String germanContent) {
		this.germanContent = germanContent;
	}

	/**
	 * @return the englishContent
	 */
	public String getEnglishContent() {
		return englishContent;
	}

	/**
	 * @param englishContent
	 *            the englishContent to set
	 */
	public void setEnglishContent(String englishContent) {
		this.englishContent = englishContent;
	}

	/**
	 * @return the frenchContent
	 */
	public String getFrenchContent() {
		return frenchContent;
	}

	/**
	 * @param frenchContent
	 *            the frenchContent to set
	 */
	public void setFrenchContent(String frenchContent) {
		this.frenchContent = frenchContent;
	}

}
