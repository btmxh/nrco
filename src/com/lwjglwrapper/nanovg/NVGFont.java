/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg;

import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class NVGFont {
    long nanoVGID;
    int id;
    String fontName;

    public NVGFont(long nanoVGID, int id, String fontName) {
        this.nanoVGID = nanoVGID;
        this.id = id;
        this.fontName = fontName;
    }
    
    public void use() {
        NanoVG.nvgFontFace(nanoVGID, fontName);
    }
    
    
    
}
