package de.p39.asrs.model;

import de.p39.asrs.server.model.media.Audio;
import org.junit.Test;

import static org.junit.Assert.*;

public class Audio_test {

    @Test
    public void calcDurationMP3() {
        Audio audio = new Audio();
        audio.setPath("src/main/resources/audio_testfiles/ElectricGuitar.mp3");
        assertEquals(18077, audio.getDuration());
        System.out.println("length was : " + audio.getDuration() + "ms");
    }

    @Test
    public void calcDuarationWAV() {
        Audio audio = new Audio();
        audio.setPath("src/main/resources/audio_testfiles/test.wav");
        assertEquals(2938, audio.getDuration());
        System.out.println("length was : " + audio.getDuration() + "ms");
    }

}
