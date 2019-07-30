/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.data;

import com.lwjglwrapper.utils.math.MathUtils;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 *
 * @author Welcome
 */
public class ObjectData {
    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    public ObjectData(Vector3f position) {
        this(position, new Vector3f());
    }

    public ObjectData(Vector3f position, Vector3f rotation) {
        this(position, rotation, new Vector3f(1));
    }

    public ObjectData(Vector3f position, Vector3f rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

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

    /**
     * @return the scale
     */
    public Vector3f getScale() {
        return scale;
    }
   
    public Matrix4f toTransformationMatrix() {
        return MathUtils.transformationMatrix(position, rotation, scale);
    }
}
