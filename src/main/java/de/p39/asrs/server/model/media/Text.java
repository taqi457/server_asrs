package de.p39.asrs.server.model.media;

import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

	private String content;
	
	public Text(Long id, String name,Locale locale) {
		super(id, name,locale);
	}
	
	public Text(String name){super(name);}
	
	public Text(){super();}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @return the name
	 */
	public String getName() {
		return super.getName();
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		super.setName(name);
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

}
