/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.geom.shapes;

import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.utils.geom.Shape;
import org.joml.Rectanglef;
import org.joml.Vector2f;

/**
 *
 * @author Welcome
 */
public class Circle implements Shape{
    public Vector2f center;
    public float radius;

    public Circle(Vector2f center, float radius) {
        this.center = center;
        this.radius = radius;
    }
    
    @Override
    public Rectanglef boundBox() {
        return new Rectanglef(new Vector2f(center).sub(radius, radius),
                              new Vector2f(center).add(radius, radius));
    }

    @Override
    public void render(NVGGraphics g) {
        g.circle(center.x, center.y, radius);
    }

    public boolean contains(Vector2f point) {
        return new Vector2f(center).distanceSquared(point) < radius * radius;
    }

}
