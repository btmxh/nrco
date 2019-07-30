/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.objects;

import com.lwjglwrapper.LWJGL;
import java.io.PrintStream;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author Welcome
 */
public class FBO {
    private int id;
    private int type;

    public FBO(int type) {
        id = GL30.glGenFramebuffers();
        this.type = type;
    }
    
    public void bind() {
        GL30.glBindFramebuffer(type, id);
    }
    
    public void unbind() {
        GL30.glBindFramebuffer(type, 0);
    }
    
    public Texture2D createTexture() {
        Texture2D texture = new Texture2D();
        texture.setTextureData(new TextureData(LWJGL.window.getWidth(), LWJGL.window.getHeight(), null));
        GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture.gl_id, 0);
        return texture;
    }
    
    public void checkProgress(PrintStream stream, boolean logIfCompleted) {
        bind();
        int progress = GL30.glCheckFramebufferStatus(GL30.GL_FRAMEBUFFER);
        if(progress == GL30.GL_FRAMEBUFFER_COMPLETE){
            if(logIfCompleted)  stream.println("FBO completed");
        } else {
            stream.println("FBO hasn't been completed: " + progress);
        }
    }
    
    public void attachRBO(int type, RBO rbo){
        bind();
        GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, type, GL30.GL_RENDERBUFFER, rbo.getId());
    }
}
