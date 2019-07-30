/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.openal;

import org.lwjgl.openal.AL10;

/**
 *
 * @author Welcome
 */
public class SoundBuffer {
    private int buffer;

    public SoundBuffer(int buffer) {
        this.buffer = buffer;
    }
    
    public void dispose() {
        AL10.alDeleteBuffers(buffer);
    }
    
    public Source newSource() {
        return new Source(this);
    }

    int getID() {
        return buffer;
    }
}
