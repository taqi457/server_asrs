package de.p39.asrs.db;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.dao.MediumDAO;
import de.p39.asrs.server.model.media.Audio;
import de.p39.asrs.server.model.media.Picture;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjornmohr on 25.07.17.
 */
public class MockMediaDAO extends MediumDAO {
    private CrudFacade cf;
    private Picture picture;
    private Audio audio;
    private List pictures;
    private List audios;

    public MockMediaDAO(CrudFacade cf) {
        super(cf);
        this.cf=cf;
        picture = new Picture();
        audio = new Audio();
        pictures = new ArrayList();
        audios = new ArrayList();
    }

    public List<Audio> getAllAudios() {
        return audios;
    }

    public List<Picture> getAllPictures() {
        return pictures;
    }


    public Picture getPictureById(Long id) {
        return picture;
    }

    public Audio getAudioById(Long id){
        return audio;
    }



    public List<Audio> getAudiosByPath(String path) {

        return audios;
    }
}
