/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.cameras;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.display.Window;
import com.lwjglwrapper.utils.data.ObjectData;
import com.lwjglwrapper.utils.input.KeyBindings;
import com.lwjglwrapper.utils.math.FloatMath;
import com.lwjglwrapper.utils.math.MathUtils;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Welcome
 */
public class ThirdPersonCamera extends Camera{
    
    private float minDist = 1f;
    private float dist = minDist;
    private float angle = 0;
    
    private ObjectData data;

    public ThirdPersonCamera(ObjectData data, float fov, Window window, float zNear, float zFar) {
        super(fov, window, zNear, zFar);
        this.data = data;
    }
    
    public ThirdPersonCamera(ObjectData data, Vector3f position, float fov, Window window,
            float zNear, float zFar) {
        super(position, fov, window, zNear, zFar);
        this.data = data;
    }
    
    public ThirdPersonCamera(ObjectData data, Vector3f position, Vector3f rotation, float fov,
            Window window, float zNear, float zFar) {
        super(position, rotation, fov, window, zNear, zFar);
        this.data = data;
    }

    @Override
    public void move(KeyBindings bindings, float delta) {
        super.move(bindings, delta);
        
        if(LWJGL.mouse.mouseDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
            float dx = LWJGL.mouse.getDeltaX() * 5;
            angle += dx;
            
            float dy = LWJGL.mouse.getDeltaY() * 5;
            rotation.x += dy;
        }
        if(LWJGL.mouse.mouseDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)){
            float dx = LWJGL.mouse.getDeltaX() / 10f;
            dist += dx;
            
            dist = MathUtils.clamp(minDist, dist, dist + 1);
        }
        
        //rotation.x = 0;
        
        float horDist = dist * FloatMath.cosDeg(-rotation.x);
        float yOff = -dist * FloatMath.sinDeg(-rotation.x);
        
        float theta = angle + data.getRotation().y;
        float xOff = -horDist * FloatMath.cosDeg(theta);
        float zOff = -horDist * FloatMath.sinDeg(theta);
        
        position = data.getPosition().add(xOff, yOff, zOff, new Vector3f());
        rotation.y = 90 + theta;
    }
    
    
}
