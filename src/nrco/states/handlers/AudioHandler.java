/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states.handlers;

import com.lwjglwrapper.openal.ALContext;
import com.lwjglwrapper.openal.SoundBuffer;
import com.lwjglwrapper.openal.Source;

/**
 *
 * @author Welcome
 */
public class AudioHandler {
    public static final int MUSIC = 0, DUMP = 1, WRONG = 2, BOOM = 3;
    
    private ALContext alContext;
    private SoundBuffer[] sounds;
    private Source[] sources;
    
    public boolean musicEnabled = false, soundEnabled = true;

    public AudioHandler() {
        alContext = new ALContext();
    }

    public void loadAudio() {
        sounds = new SoundBuffer[4];
        sources = new Source[sounds.length];
        
        sounds[MUSIC] = alContext.loadSound(AudioHandler.class, "/audio/music.ogg");
        sounds[DUMP] = alContext.loadSound(AudioHandler.class, "/audio/dump.ogg");
        sounds[WRONG] = alContext.loadSound(AudioHandler.class, "/audio/wrong.ogg");
        sounds[BOOM] = alContext.loadSound(AudioHandler.class, "/audio/boom.ogg");
        
        for (int i = 0; i < 4; i++) {
            sources[i] = new Source(sounds[i]);
        }
    }
    
    public void play(int audio, boolean loop) {
        if(audio == MUSIC) {
            if(!musicEnabled)    return;
        } else {
            if(!soundEnabled)    return;
        }
        
        sources[audio].repeat(loop);
        sources[audio].play();
    }
    
    public void dispose() {
        alContext.dispose();
    }

    public void stopMusic() {
        sources[MUSIC].stop();
    }
    
    
}
