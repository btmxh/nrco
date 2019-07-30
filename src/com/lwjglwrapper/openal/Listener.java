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
public class Listener {
    private Vector3f pos, vel;

    public Listener() {
        pos = new Vector3f();
        vel = new Vector3f();
    }
    
    public void update() {
        AL10.alListener3f(AL10.AL_POSITION, pos.x, pos.y, pos.z);
    }

    /**
     * @return the pos
     */
    public Vector3f getPosition() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPosition(Vector3f pos) {
        this.pos = pos;
    }
}
