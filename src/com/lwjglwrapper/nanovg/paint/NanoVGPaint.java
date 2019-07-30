/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg.paint;

import com.lwjglwrapper.nanovg.NVGGraphics;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;

/**
 * a simple wrapper for NVGPaint
 * @author Welcome
 */
public class NanoVGPaint implements Paint{

    protected NVGPaint nativePaint;
    
    public NanoVGPaint(NVGPaint nativePaint) {
        this.nativePaint = nativePaint;
    }

    @Override
    public void fill(long nvgID) {
        NanoVG.nvgFillPaint(nvgID, nativePaint);
        NanoVG.nvgFill(nvgID);
    }

    @Override
    public void stroke(long nvgID) {
        NanoVG.nvgStrokePaint(nvgID, nativePaint);
        NanoVG.nvgStroke(nvgID);
    }

    @Override
    public void text(long nvgID) {
        NanoVG.nvgFillPaint(nvgID, nativePaint);
    }
    
    
    
}
