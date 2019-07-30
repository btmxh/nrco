/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.geom.shapes;

import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.utils.geom.Shape;
import org.joml.Rectanglef;

/**
 *
 * @author Welcome
 */
public class RoundRect extends Rect {

    private float topLeft, topRight, bottomRight, bottomLeft;

    public RoundRect(Rectanglef rect, float radius) {
        this(rect, rect, radius);
    }

    public RoundRect(Rectanglef rect, float topLeft, float topRight,
            float bottomRight, float bottomLeft) {
        this(rect, rect, topLeft, topRight, bottomRight, bottomLeft);
    }
    
    public RoundRect(Rectanglef rect, Rectanglef bounds, float radius) {
        this(rect, bounds, radius, radius, radius, radius);
    }

    public RoundRect(Rectanglef rect, Rectanglef bounds, float topLeft,
            float topRight, float bottomRight, float bottomLeft) {
        super(rect, bounds);
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomRight = bottomRight;
        this.bottomLeft = bottomLeft;
    }

    public RoundRect(float x, float y, float w, float h, float radius) {
        this(Rect.jomlRect(x, y, w, h), radius);
    }

    public RoundRect(float x, float y, float w, float h, float topLeft,
            float topRight, float bottomRight, float bottomLeft) {
        this(Rect.jomlRect(x, y, w, h), topLeft, topRight, bottomRight, bottomLeft);
    }

    public RoundRect(float x, float y, float w, float h, float bx, float by,
            float bw, float bh, float radius) {
        this(Rect.jomlRect(x, y, w, h), Rect.jomlRect(bx, by, bw, bh), radius);
    }

    public RoundRect(float x, float y, float w, float h, float bx, float by,
            float bw, float bh, float topLeft, float topRight, float bottomRight,
            float bottomLeft) {
        this(Rect.jomlRect(x, y, w, h), Rect.jomlRect(bx, by, bw, bh), 
                topLeft, topRight, bottomRight, bottomLeft);
    }

    

    @Override
    public void render(NVGGraphics g) {
        g.roundRect(rect.minX, rect.minY, rect.maxX - rect.minX, rect.maxY - rect.minY,
                topLeft, topRight, bottomRight, bottomLeft);
    }

    /**
     * @return the topLeft
     */
    public float getTopLeftRadius() {
        return topLeft;
    }

    /**
     * @param topLeft the topLeft to set
     */
    public void setTopLeftRadius(float topLeft) {
        this.topLeft = topLeft;
    }

    /**
     * @return the topRight
     */
    public float getTopRightRadius() {
        return topRight;
    }

    /**
     * @param topRight the topRight to set
     */
    public void setTopRightRadius(float topRight) {
        this.topRight = topRight;
    }

    /**
     * @return the bottomRight
     */
    public float getBottomRightRadius() {
        return bottomRight;
    }

    /**
     * @param bottomRight the bottomRight to set
     */
    public void setBottomRightRadius(float bottomRight) {
        this.bottomRight = bottomRight;
    }

    /**
     * @return the bottomLeft
     */
    public float getBottomLeftRadius() {
        return bottomLeft;
    }

    /**
     * @param bottomLeft the bottomLeft to set
     */
    public void setBottomLeftRadius(float bottomLeft) {
        this.bottomLeft = bottomLeft;
    }
    
    
}
