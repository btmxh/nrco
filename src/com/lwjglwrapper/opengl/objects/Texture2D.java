/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.objects;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author Welcome
 */
public class Texture2D extends Texture{
    private TextureData texData;

    public Texture2D(String path) {
        this(TextureData.fromPath(path));
    }

    public Texture2D() {
        super(GL11.GL_TEXTURE_2D);
        bind();
        configTexture(gl_id);
        unbind();
    }
    
    public Texture2D(TextureData texData) {
        super(GL11.GL_TEXTURE_2D);
        bind();
        setTextureData(texData);
        configTexture(gl_id);
        unbind();
        texData.free();
    }
    
    public final void setTextureData(TextureData texData) {
        this.texData = texData;
        bind();
        GL11.glTexImage2D(textureType, 0, GL11.GL_RGBA, texData.getWidth(), texData.getHeight(),
                0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, texData.getPixelData());
    }

    public TextureData getTextureData() {
        return texData;
    }
    
}
