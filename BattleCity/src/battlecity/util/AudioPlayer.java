package battlecity.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author xGod
 */
public class AudioPlayer implements Runnable {

    private File audioFile;
    private boolean repeat;

    public AudioPlayer(File audioFile) {
        this.audioFile = audioFile;
        repeat = false;
    }

    public AudioPlayer(File audioFile, boolean repeat) {
        this.audioFile = audioFile;
        this.repeat = repeat;
    }

    public synchronized File getAudioFile() {
        return audioFile;
    }

    public synchronized void setAudioFile(File audioFile) {
        this.audioFile = audioFile;
    }

    public synchronized boolean isRepeat() {
        return repeat;
    }

    public synchronized void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    @Override
    public void run() {
        do {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(getAudioFile());
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (isRepeat());
    }

}
