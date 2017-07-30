package de.p39.asrs.server.model.media;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.sound.sampled.*;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import de.p39.asrs.server.model.LocaleDescription;
import de.p39.asrs.server.model.LocaleName;
import de.p39.asrs.server.model.NamedEntity;

/**
 * @author adrianrebmann
 */
@Entity
public class Audio extends NamedEntity {

    /**
     *
     */
    private static final long serialVersionUID = -1282454048651640596L;

    private static MpegAudioFileReader mpegAudioFileReader = new MpegAudioFileReader();

    private String path;

    private long duration;

    public Audio() {
        super();
    }

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
        try {
            File file = new File(path);
            if (path.endsWith(".wav")) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                AudioFormat format = audioInputStream.getFormat();
                duration = Math.round(file.length() / format.getSampleRate() / (format.getSampleSizeInBits() / 8.0) / format.getChannels() * 1000); // convert from seconds to ms
            } else if (path.endsWith(".mp3")) {
                AudioFileFormat baseFileFormat = mpegAudioFileReader.getAudioFileFormat(file);
                duration = (long) baseFileFormat.properties().get("duration") / 1000; //convert from micro sec to ms
            } else {
                duration = 0L;
            }
        } catch (IOException | UnsupportedAudioFileException e) {
            duration = 0L;
        }

    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
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
     * @param timestamp the timestamp to set
     */
    @Temporal(TemporalType.TIMESTAMP)
    public void setTimestamp(Date timestamp) {
        super.setTimestamp(timestamp);
    }

    /**
     * @return the names
     */
    @OneToMany(targetEntity = LocaleName.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<LocaleName> getNames() {
        return names;
    }

    /**
     * @param names the names to set
     */
    @OneToMany(targetEntity = LocaleName.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public void setNames(List<LocaleName> names) {
        this.names = names;
    }

    /**
     * @return the descriptions
     */
    @OneToMany(targetEntity = LocaleDescription.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<LocaleDescription> getDescriptions() {
        return descriptions;
    }

    /**
     * @param descriptions the descriptions to set
     */
    @OneToMany(targetEntity = LocaleDescription.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public void setDescriptions(List<LocaleDescription> descriptions) {
        this.descriptions = descriptions;
    }
}
