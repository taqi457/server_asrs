package de.p39.asrs.model;

import de.p39.asrs.server.model.media.Audio;
import org.junit.Test;

import static org.junit.Assert.*;

public class Audio_test {

    @Test
    public void calcDuration() {
        Audio audio = new Audio();
        audio.setPath("src/main/resources/audio_testfiles/ElectricGuitar.mp3");
        assertNotEquals(0L, audio.getDuration());
        System.out.println("length was : " + audio.getDuration() + "ms");
    }

}
