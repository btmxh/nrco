/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.cameras;

import com.lwjglwrapper.display.Window;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import static com.lwjglwrapper.utils.math.FloatMath.*;
import com.lwjglwrapper.utils.input.KeyBindings;
import com.lwjglwrapper.utils.math.FloatMath;

/**
 *
 * @author Welcome
 */
public class Camera {
    protected Vector3f position;
    protected Vector3f rotation;
    
    protected float fov;    //in degrees
    protected float zNear, zFar;
    protected Window window;

    public Camera(float fov, Window window, float zNear, float zFar) {
        this.fov = fov;
        this.window = window;
        this.zFar = zFar;
        this.zNear = zNear;
        
        this.position = new Vector3f();
        this.rotation = new Vector3f();
    }

    public Camera(Vector3f position, float fov, Window window, float zNear, float zFar) {
        this.fov = fov;
        this.window = window;
        this.zFar = zFar;
        this.zNear = zNear;
        
        this.position = position;
        this.rotation = new Vector3f();
    }
    
    public Camera(Vector3f position, Vector3f rotation, float fov, Window window, float zNear, float zFar) {
        this.fov = fov;
        this.window = window;
        this.zFar = zFar;
        this.zNear = zNear;
        
        this.position = position;
        this.rotation = rotation;
    }
    
    public Matrix4f getViewMatrix() {
        Matrix4f mat = new Matrix4f();
        mat.rotateXYZ(FloatMath.toRads(rotation.x), FloatMath.toRads(rotation.y), FloatMath.toRads(rotation.z))
           .translate(new Vector3f(position).negate());
        return mat;
    }
    
    public Matrix4f getProjectionMatrix() {
        return new Matrix4f().perspective(toRads(fov), window.getAspectRatio(), zNear, zFar);
    }
    
    public Matrix4f getCombinedMatrix() {
        return getProjectionMatrix().mul(getViewMatrix(), new Matrix4f());
    }
    
    public void move(KeyBindings keys, float delta){}

    /**
     * @return the position
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * @return the rotation
     */
    public Vector3f getRotation() {
        return rotation;
    }
}
