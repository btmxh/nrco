/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg;

import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.system.MemoryStack;

/**
 *
 * @author Welcome
 */
public class NVGImage {
    private long ctx;
    int id, width, height;

    public NVGImage(long ctx, int id) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            
            NanoVG.nvgImageSize(ctx, id, w, h);
            
            this.ctx = ctx;
            this.id = id;
            this.width = w.get();
            this.height = h.get();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void dispose() {
        NanoVG.nvgDeleteImage(ctx, id);
    }
}
