/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.math;

/**
 *
 * @author Welcome
 */
public class FloatMath {
    public static final float PI = 3.141592653589793f, E = 2.718281828459045235f;
    
    public static float sin(float x) {
        return (float) Math.sin(x);
    }
    
    public static float cos(float x) {
        return (float) Math.cos(x);
    }
    
    public static float tan(float x) {
        return (float) Math.tan(x);
    }
    
    public static float toRads(float deg) {
        return deg / 180f * PI;
    }
    
    public static float toDegs(float rad) {
        return rad / PI * 180f;
    }
    
    public static float sinDeg(float d) {
        return sin(toRads(d));
    }
    
    public static float cosDeg(float d) {
        return cos(toRads(d));
    }
    
    public static float tanDeg(float d) {
        return tan(toRads(d));
    }
}
