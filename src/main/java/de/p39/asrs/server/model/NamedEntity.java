package de.p39.asrs.server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

import de.p39.asrs.server.model.media.Audio;

/**
 * 
 * @author adrianrebmann
 *
 * @param <K>
 */
@Inheritance
public abstract class NamedEntity extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5313396633041880809L;

	protected List<LocaleName> names = new ArrayList<>();
	protected List<LocaleDescription> descriptions = new ArrayList<>();
	protected List<LocaleAudio> audios = new ArrayList<>();

	public NamedEntity() {
		super();
	}

	/**
	 * @return the names
	 */
	@OneToMany(targetEntity = LocaleName.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public List<LocaleName> getNames() {
		return names;
	}



	/**
	 * @param names the names to set
	 */
	@OneToMany(targetEntity = LocaleName.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public void setNames(List<LocaleName> names) {
		this.names = names;
	}

	/**
	 * @return the descriptions
	 */
	@OneToMany(targetEntity = LocaleDescription.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public List<LocaleDescription> getDescriptions() {
		return descriptions;
	}

	/**
	 * @param descriptions the descriptions to set
	 */
	@OneToMany(targetEntity = LocaleDescription.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public void setDescriptions(List<LocaleDescription> descriptions) {
		this.descriptions = descriptions;
	}
	
	@OneToMany(targetEntity = LocaleDescription.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public List<LocaleAudio> getAudios() {
		return audios;
	}
	@OneToMany(targetEntity = LocaleDescription.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public void setAudios(List<LocaleAudio> audios) {
		this.audios = audios;
	}

	public void addLocaleName(LocaleName l){
		for(LocaleName name : this.getNames()) {
			if (l.getLocale().equals(name.getLocale())) {
				this.names.remove(name);
			}
		}
		this.names.add(l);
	}
	
	public void addLocaleAudio(LocaleAudio a){
		for(LocaleAudio audio : this.getAudios()) {
			if (a.getLocale().equals(audio.getLocale())) {
				this.names.remove(audio);
			}
		}
		this.audios.add(a);
	}
	
	public void addLocaleDescription(LocaleDescription l){
		for(LocaleDescription description : this.getDescriptions()) {
			if (l.getLocale().equals(description.getLocale())) {
				this.names.remove(description);
			}
		}
		this.descriptions.add(l);
	}
	
	public void removeLocaleName(LocaleName l){
		if(this.names.contains(l))
			this.names.remove(l);
	}
	
	public void removeLocaleDescription(LocaleName l){
		if(this.descriptions.contains(l))
			this.descriptions.remove(l);
	}
	
	public void removeLocaleAudio(LocaleAudio a){
		if(this.audios.contains(a)){
			this.audios.remove(a);
		}
	}
	
	public String getNameByLocale(Locale l){
		for(LocaleName name : this.names){
			if(name.getLocale().equals(l))
				return name.getString();
		}
		return null;
	}
	
	public String getDescriptionByLocale(Locale l){
		for(LocaleDescription description : this.descriptions){
			if(description.getLocale().equals(l))
				return description.getString();
		}
		return null;
	}
	
	public Audio getAudioByLocale(Locale l){
		for(LocaleAudio audio : this.audios){
			if(audio.getLocale().equals(l))
				return audio.getAudio();
		}
		return null;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return super.getId();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public void setId(Long id) {
		super.setId(id);
	}

	
	

}
