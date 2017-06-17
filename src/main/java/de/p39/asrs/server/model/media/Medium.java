package de.p39.asrs.server.model.media;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

import de.p39.asrs.server.model.NamedObject;
import de.p39.asrs.server.model.LocaleDescription;
import de.p39.asrs.server.model.LocaleName;
/**
 * 
 * @author adrianrebmann
 *
 */
@Inheritance
public class Medium extends NamedObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5552779801693557981L;

	
	public Medium(){super();}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId(){
		return super.getId();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public void setId(Long id){
		super.setId(id);
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
	
}
