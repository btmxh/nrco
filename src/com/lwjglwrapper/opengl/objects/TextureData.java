/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.objects;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

/**
 *
 * @author Welcome
 */
public class TextureData {

    int width, height;
    ByteBuffer pixels;

    public TextureData(int width, int height, ByteBuffer pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ByteBuffer getPixelData() {
        return pixels;
    }

    public void free() {
        if(pixels == null)  return;
        MemoryUtil.memFree(pixels);
    }

    public static TextureData fromPath(String path) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            ByteBuffer pixels = STBImage.stbi_load(path, w, h, comp, 4);

            return new TextureData(w.get(), h.get(), pixels);
        }
    }

    public float getAspectRatio() {
        return (float) width / (float) height;
    }
}
