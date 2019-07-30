/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.objects;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;

/**
 *
 * @author Welcome
 */
public class TextureRect extends Texture{
    
    private TextureData texData;

    public TextureRect(String path) {
        this(TextureData.fromPath(path));
    }

    public TextureRect() {
        super(GL31.GL_TEXTURE_RECTANGLE);
        bind();
        configTexture(gl_id);
        unbind();
    }
    
    public TextureRect(TextureData texData) {
        super(GL31.GL_TEXTURE_RECTANGLE);
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
    
}
