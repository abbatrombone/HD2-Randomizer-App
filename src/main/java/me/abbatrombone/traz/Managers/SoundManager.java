package me.abbatrombone.traz.Managers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoundManager {
    private static final Logger logger = Logger.getLogger(SoundManager.class.getName());
    private static final SettingsManager settingsManager = new SettingsManager();

    private static Clip currentClip;

    public SoundManager(){

    }

    public static void playSound(String filename){
        if (!settingsManager.soundIsOn()) {
            return;
        }
        stopSound();

        try {
            InputStream is = SoundManager.class.getClassLoader().getResourceAsStream(filename);
            assert is != null;

            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
            currentClip = AudioSystem.getClip();
            currentClip.open(inputStream);
            currentClip.start();

        }catch (Exception e){
            logger.log(Level.WARNING, "Failed to play sound. Printing Stack Trace");
            logger.log(Level.WARNING,e.toString());
        }

    }
    public static void stopSound(){
        if (currentClip != null) {
            currentClip.stop();
            currentClip.close();
            currentClip = null;
        }
    }
}
