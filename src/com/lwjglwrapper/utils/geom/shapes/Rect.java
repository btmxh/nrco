/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.geom.shapes;

import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.utils.geom.Shape;
import com.lwjglwrapper.utils.math.MathUtils;
import java.awt.geom.Rectangle2D;
import org.joml.Rectanglef;
import org.joml.Vector2f;

/**
 *
 * @author Welcome
 */
public class Rect implements Shape{

    protected Rectanglef rect, bounds;

    public Rect(float x, float y, float w, float h) {
        this(jomlRect(x, y, w, h));
    }
    
    public Rect(float x, float y, float w, float h, float bx, float by, float bw, float bh) {
        this(jomlRect(x, y, w, h), jomlRect(bx, by, bw, bh));
    }
    
    public Rect(Rectanglef rect) {
        this(rect, rect);
    }

    public Rect(Rectanglef rect, Rectanglef bounds) {
        this.rect = rect;
        this.bounds = bounds;
    }
    
    @Override
    public Rectanglef boundBox() {
        return bounds;
    }

    @Override
    public void render(NVGGraphics g) {
        g.rect(rect.minX, rect.minY, rect.maxX - rect.minX, rect.maxY - rect.minY);
    }
    
    public static Rectanglef jomlRect(float x, float y, float w, float h) {
        return new Rectanglef(x, y, x + w, y + h);
    }
    
    public static Rectangle2D.Float awtRect(Rectanglef rect) {
        return new Rectangle2D.Float(rect.minX, rect.minY, rect.maxX - rect.minX, rect.maxY - rect.minY);
    }

    public Rectanglef getJOMLRect() {
        return rect;
    }

    public Rectanglef getJOMLBounds() {
        return bounds;
    }
    
    public boolean contains(Vector2f point) {
        return MathUtils.contains(rect, point);
    }
    
}
