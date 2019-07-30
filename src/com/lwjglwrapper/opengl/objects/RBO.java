/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.objects;

import org.lwjgl.opengl.GL30;

/**
 *
 * @author Welcome
 */
public class RBO {
    private int id;
    
    public RBO(int type, int width, int height) {
        id = GL30.glGenRenderbuffers();
        bind();
        GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, type, width, height);
    }
    
    public final void bind(){
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, id);
    }
    
    public final void unbind(){
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, 0);
    }
    
    public final void dispose(){
        GL30.glDeleteRenderbuffers(id);
    }

    public int getId() {
        return id;
    }
}
