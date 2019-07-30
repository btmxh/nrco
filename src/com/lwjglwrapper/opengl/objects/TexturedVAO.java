/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.objects;

import java.util.List;

/**
 *
 * @author Welcome
 */
public class TexturedVAO extends VAO{
    protected int textureSlot;
    protected Texture texture;

    public TexturedVAO(int textureSlot, Texture texture) {
        super();
        this.textureSlot = textureSlot;
        this.texture = texture;
    }

    @Override
    protected void bind() {
        super.bind();
        texture.bind(textureSlot);
    }

    @Override
    protected void unbind() {
        super.unbind();
        texture.unbind();
    }

    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
    }

    public Texture getTexture() {
        return texture;
    }
}
