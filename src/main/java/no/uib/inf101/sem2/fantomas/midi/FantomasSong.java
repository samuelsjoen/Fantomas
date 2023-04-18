package no.uib.inf101.sem2.fantomas.midi;

import java.io.InputStream;
import java.util.Random;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

/**
 * Play the Fantomaas music. Sample usage:
 * <code>
 * FantomasSong music = new FantomasSong();
 * music.run(); 
 * </code>
 */
public class FantomasSong implements Runnable {
    private static final String FANTOMASMUSIC = randomMidiSound();
    private Sequencer sequencer;

    @Override
    public void run() {
        InputStream song = FantomasSong.class.getClassLoader().getResourceAsStream(FANTOMASMUSIC);
        this.doPlayMidi(song, true);
    }

    // Chooses between random midi songs
    private static String randomMidiSound() {
        final String[] songs = {"vivaldi.midi", "chopin.midi", "mozart.midi", "dvorak.midi", "twin_peaks.midi",};
        Random random = new Random();
        int index = random.nextInt(songs.length);
        return songs[index];
    }

    private void doPlayMidi(final InputStream is, final boolean loop) {
        try {
            this.doStopMidiSounds();
            (this.sequencer = MidiSystem.getSequencer()).setSequence(MidiSystem.getSequence(is));
            if (loop) {
                this.sequencer.setLoopCount(-1);
            }
            this.sequencer.open();
            this.sequencer.start();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
    }

    public void doStopMidiSounds() {
        try {
            if (this.sequencer == null || !this.sequencer.isRunning()) {
                return;
            }
            this.sequencer.stop();
            this.sequencer.close();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
        this.sequencer = null;
    }

    public void doPauseMidiSounds() {
        try {
            if (this.sequencer == null || !this.sequencer.isRunning()) {
                return;
            }
            this.sequencer.stop();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
    }
    
    public void doUnpauseMidiSounds() {
        try {
            if (this.sequencer == null) {
                return;
            }
            this.sequencer.start();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
    }

    private void midiError(final String msg) {
        System.err.println("Midi error: " + msg);
        this.sequencer = null;
    }
}