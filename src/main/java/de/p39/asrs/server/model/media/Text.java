package de.p39.asrs.server.model.media;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import de.p39.asrs.server.model.LocaleDescription;
import de.p39.asrs.server.model.LocaleName;
/**
 * 
 * @author adrianrebmann
 *
 */
@Entity
public class Text extends Medium {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5139030591840399032L;
	
	public Text(){super();}
	
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
	 * @return the timestamp
	 */
	@Temporal(TemporalType.TIMESTAMP) 
	public Date getTimestamp() {
		return super.getTimestamp();
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	@Temporal(TemporalType.TIMESTAMP) 
	public void setTimestamp(Date timestamp) {
		super.setTimestamp(timestamp);
	}
	
	/**
	 * @return the names
	 */
	@OneToMany(targetEntity = LocaleName.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<LocaleName> getNames() {
		return names;
	}



	/**
	 * @param names the names to set
	 */
	@OneToMany(targetEntity = LocaleName.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
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
