package sample.handlerthreads;

import java.io.File;
import lejos.hardware.Sound;

/**
 * A thread class for playing a sound track continuously until the exit condition is met.
 * It extends the HandlerThread class and overrides the run() method to handle sound playback.
 */
public class SoundHandlerThread extends HandlerThread {

    /**
     * The file representing the soundtrack to be played.
     */
    private File soundtrack;

    /**
     * The volume level of the soundtrack, an integer between 0 and 100.
     */
    private int volume;

    /**
     * Constructs a SoundHandlerThread object with default soundtrack and maximum volume.
     */
    public SoundHandlerThread() {
        this.soundtrack = new File("sounds/i_was_marios_tash.wav");
        this.volume = 100; // Default volume set to maximum
    }

    /**
     * Runs the sound playback loop until the exit condition is met.
     * Checks if the playback time is zero and then plays the soundtrack at the set volume.
     */
    @Override
    public void run() {
        this.setExitCondition(true);

        while (this.getExitCondition()) {
            if (Sound.getTime() <= 0) {
                Sound.playSample(this.soundtrack, this.volume);
            }
        }
    }
}