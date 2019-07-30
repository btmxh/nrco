/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.geom;

import com.lwjglwrapper.nanovg.NVGGraphics;
import org.joml.Rectanglef;

/**
 *
 * @author Welcome
 */
public interface Shape {

    public static final Shape EMPTY = new Shape() {
        @Override   public Rectanglef boundBox() {return new Rectanglef();}
        @Override   public void render(NVGGraphics g) {}
    };
    
    public Rectanglef boundBox();
    public void render(NVGGraphics g);
}
