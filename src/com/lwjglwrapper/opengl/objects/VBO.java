/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.objects;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Welcome
 */
public class VBO {
    private int id;
    private int type;
    private int usage;
    
    public VBO(int type) {
        this.type = type;
        id = GL20.glGenBuffers();
        usage = GL15.GL_STATIC_DRAW;
    }

    public void dispose() {
        GL20.glDeleteBuffers(id);
        
    }
    
    public void bind() {
        GL20.glBindBuffer(type, id);
    }
    
    public void unbind() {
        GL20.glBindBuffer(type, 0);
    }
    
    public void storeData(float[] data) {
        GL20.glBufferData(type, data, usage);
    }
    
    public void storeData(int[] data) {
        GL20.glBufferData(type, data, usage);
    }
    
    public void storeData(FloatBuffer data) {
        GL20.glBufferData(type, data, usage);
    }
    
    public void storeData(IntBuffer data) {
        GL20.glBufferData(type, data, usage);
    }
}
