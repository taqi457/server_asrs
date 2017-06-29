package de.p39.asrs.server.model.media;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Picture extends Medium {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2524529774881275743L;
	
	private String path;
	private Text text;
	private Map<Size,String> paths = new HashMap<>();
	
	public Picture(){super();}
	

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

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
	


	@OneToOne(targetEntity = Text.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public Text getText() {
		return text;
	}

	@OneToOne(targetEntity = Text.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public void setText(Text text) {
		this.text = text;
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
	@Fetch(value = FetchMode.SUBSELECT)
	public List<LocaleDescription> getDescriptions() {
		return descriptions;
	}

	/**
	 * @param descriptions the descriptions to set
	 */
	@OneToMany(targetEntity = LocaleDescription.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public void setDescriptions(List<LocaleDescription> descriptions) {
		this.descriptions = descriptions;
	}
	
	public void addPath(Size s, String p){
		this.paths.put(s, p);
	}
	
	public void removeSize(Size s){
		this.paths.remove(s);
	}
	
	public String getPath(Size s){
		return this.paths.get(s);
	}


	@ElementCollection
	public Map<Size,String> getPaths() {
		return paths;
	}


	@ElementCollection
	public void setPaths(Map<Size,String> paths) {
		this.paths = paths;
	}
}
