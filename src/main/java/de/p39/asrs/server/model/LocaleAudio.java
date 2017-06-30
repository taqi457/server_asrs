package de.p39.asrs.server.model;

import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.p39.asrs.server.model.media.Audio;

@Entity
public class LocaleAudio extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1579668623620959689L;
	
	private Locale locale;
	private Audio audio;
	public LocaleAudio(Locale locale, Audio audio) {
		super();
		this.locale = locale;
		this.audio = audio;
	}
	public LocaleAudio() {
		super();
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	@OneToOne(targetEntity = Audio.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Audio getAudio() {
		return audio;
	}
	@OneToOne(targetEntity = Audio.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public void setAudio(Audio audio) {
		this.audio = audio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((audio == null) ? 0 : audio.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocaleAudio other = (LocaleAudio) obj;
		if (audio == null) {
			if (other.audio != null)
				return false;
		} else if (!audio.equals(other.audio))
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		return true;
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
