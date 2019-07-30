/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.ui;

import org.joml.Rectanglef;

/**
 *
 * @author Welcome
 */
public class RectUtils {
    public static Rectanglef xywh(float x, float y, float w, float h) {
        return new Rectanglef(x, y, x + w, y + h);
    }
    
    public static Rectanglef center(float cx, float cy, float w, float h) {
        return xywh(cx - w / 2, cy - h / 2, w, h);
    }

    public static Rectanglef scaleCenter(Rectanglef rect, float scale) {
        float cx = (rect.minX + rect.maxX) / 2;
        float cy = (rect.minY + rect.maxY) / 2;
        float w = (rect.maxX - rect.minX) * scale;
        float h = (rect.maxY - rect.minY) * scale;
        return center(cx, cy, w, h);
    }

    public static float width(Rectanglef rect) {
        return rect.maxX - rect.minX;
    }

    public static float height(Rectanglef rect) {
        return rect.maxY - rect.minY;
    }
}
