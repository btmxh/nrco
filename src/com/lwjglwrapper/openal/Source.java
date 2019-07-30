/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.openal;

import org.joml.Vector3f;
import org.lwjgl.openal.AL10;

/**
 *
 * @author Welcome
 */
public class Source {
    private int sourceID;
    private SoundBuffer buffer;
    
    private Vector3f position;
    private float pitch, gain;
    
    private float rollOff = 1, referenceDist = 5, maxDist = 15;

    public Source() {
        sourceID = AL10.alGenSources();
        
        setPitch(1);
        setVolume(1);
        setRollOffFactor(1);
        setReferenceDistance(5);
        setMaxDistance(15);
    }
    
    public Source(SoundBuffer buffer) {
        this();
        setSound(buffer);
    }
    
    public void setSound(SoundBuffer buffer) {
        this.buffer = buffer;
        AL10.alSourcei(sourceID, AL10.AL_BUFFER, buffer.getID());
    }
    
    public void play() {
        AL10.alSourcePlay(sourceID);
    }
    
    public void pause() {
        AL10.alSourcePause(sourceID);
    }
    
    public void stop() {
        AL10.alSourceStop(sourceID);
    }
    
    public void rewind() {
        AL10.alSourceRewind(sourceID);
    }
    
    public void repeat(boolean repeat) {
        AL10.alSourcei(sourceID, AL10.AL_LOOPING, repeat? AL10.AL_TRUE : AL10.AL_FALSE);
    }
    
    public void updatePosition() {
        AL10.alSource3f(sourceID, AL10.AL_POSITION, position.x, position.y, position.z);
    }

    public void setRollOffFactor(float rollOff) {
        this.rollOff = rollOff;
        AL10.alSourcef(sourceID, AL10.AL_ROLLOFF_FACTOR, rollOff);
    }

    public void setReferenceDistance(float referenceDist) {
        this.referenceDist = referenceDist;
        AL10.alSourcef(sourceID, AL10.AL_REFERENCE_DISTANCE, referenceDist);
    }

    public void setMaxDistance(float maxDist) {
        this.maxDist = maxDist;
        AL10.alSourcef(sourceID, AL10.AL_MAX_DISTANCE, maxDist);
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
        AL10.alSourcef(sourceID, AL10.AL_PITCH, pitch);
    }

    public void setVolume(float gain) {
        this.gain = gain;
        AL10.alSourcef(sourceID, AL10.AL_GAIN, gain);
    }
    
    public void setPosition(Vector3f position) {
        this.position = position;
        updatePosition();
    }

    public void dispose() {
        AL10.alDeleteSources(sourceID);
    }
    
    
}
