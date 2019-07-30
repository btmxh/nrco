/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.objects;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;

/**
 *
 * @author Welcome
 */
public class TextureCube extends Texture{
    
    public TextureCube(String f0, String f1, String f2, String f3, String f4, String f5) {
        super(GL13.GL_TEXTURE_CUBE_MAP);
        bind(0);
        String[] paths = {f0, f1, f2, f3, f4, f5};
        for (int i = 0; i < paths.length; i++) {
            String path = paths[i];
            TextureData data = TextureData.fromPath(path);
            GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL11.GL_RGBA,
                    data.width, data.height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, data.pixels);
        }
        configTexture(gl_id);
    }
    
    public TextureCube(String dir, String ext, String f0, String f1, String f2, String f3, String f4, String f5) {
        this(dir + "/" + f0 + "." + ext,
             dir + "/" + f1 + "." + ext,
             dir + "/" + f2 + "." + ext,
             dir + "/" + f3 + "." + ext,
             dir + "/" + f4 + "." + ext,
             dir + "/" + f5 + "." + ext);
    }
    
    public TextureCube(String dir, String ext) {
        this(dir, ext, "right", "left", "top", "bottom", "back", "front");
    }

    @Override
    public void configTexture(int id) {
        super.configTexture(id);
        GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
    }
    
    
    
}
