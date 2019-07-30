/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.math;

import com.lwjglwrapper.utils.geom.shapes.Rect;
import static com.lwjglwrapper.utils.math.FloatMath.*;
import org.joml.Matrix4f;
import org.joml.Rectanglef;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author Welcome
 */
public class MathUtils {

    public static Vector2f rotate(Vector2f vec2, float angdeg) {
        return new Vector2f(vec2.x * cosDeg(angdeg) - vec2.y * sinDeg(angdeg),
                vec2.x * sinDeg(angdeg) + vec2.y * cosDeg(angdeg));
    }

    public static <T extends Comparable<T>> T clamp(T min, T value, T max) {
        if (min.compareTo(max) > 1) {
            return clamp(max, value, min);
        }
        if (value.compareTo(min) < 1) {
            return min;
        } else if (value.compareTo(max) > -1) {
            return max;
        } else {
            return value;
        }
    }

    public static Matrix4f transformationMatrix(Vector3f position,
            Vector3f rotation, Vector3f scale) {
        return new Matrix4f().translate(position).rotateXYZ(toRads(rotation.x), toRads(rotation.y), toRads(rotation.z)).scale(scale);
    }

    public static boolean contains(Rectanglef rect, Vector2f vec2) {
        return ((rect.minX <= vec2.x && rect.maxX >= vec2.x) | (rect.minX >= vec2.x && rect.maxX <= vec2.x))
            && ((rect.minY <= vec2.y && rect.maxY >= vec2.y) | (rect.minY >=vec2.y && rect.maxY <= vec2.y));
    }

    public static float map(float value, float istart, float istop, float ostart, float ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }
    
    public static float cx(Rectanglef rect) {
        return (rect.minX + rect.maxX) / 2;
    }
    
    public static float cy(Rectanglef rect) {
        return (rect.minY + rect.maxY) / 2;
    }
}
