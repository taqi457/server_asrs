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
	
	public void addLocaleName(LocaleName l){
		for(LocaleName name : this.getNames()) {
			if (l.getLocale().equals(name.getLocale())) {

				this.names.remove(name);
			}
		}
		this.names.add(l);
	}
	
	public void addLocaleDescription(LocaleDescription l){
		this.descriptions.add(l);
	}
	
	public void removeLocaleName(LocaleName l){
		this.names.remove(l);
	}
	
	public void removeLocaleDescription(LocaleName l){
		this.descriptions.remove(l);
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
