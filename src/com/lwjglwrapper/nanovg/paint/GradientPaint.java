/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg.paint;

import com.lwjglwrapper.utils.IColor;
import org.lwjgl.nanovg.NVGPaint;

/**
 *
 * @author Welcome
 */
public abstract class GradientPaint extends NanoVGPaint{
    protected IColor icolor;
    protected IColor ocolor;

    public GradientPaint(NVGPaint paint, IColor icolor, IColor ocolor) {
        super(paint);
        this.icolor = icolor;
        this.ocolor = ocolor;
    }
    
    
    
    public void setColor(IColor icolor, IColor ocolor) {
        this.icolor = icolor;
        this.ocolor = ocolor;
        resetPaint();
    }

    protected abstract void resetPaint();

    /**
     * @return the icolor
     */
    public IColor getIColor() {
        return icolor;
    }

    /**
     * @return the ocolor
     */
    public IColor getOColor() {
        return ocolor;
    }
    
    
}
