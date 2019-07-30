/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.models;

import com.lwjglwrapper.opengl.objects.VAO;
import com.lwjglwrapper.utils.collections.FloatArrayStack;
import com.lwjglwrapper.utils.collections.IntArrayStack;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.joml.Vector4f;
import org.joml.Vector4i;

/**
 *
 * @author Welcome
 */
public class VAOBuilder {

    private FloatArrayStack[] floatBuffers;
    private IntArrayStack[] intBuffers;
    private IntArrayStack indexBuffer;
    private int[] sizePerVertex;

    public VAOBuilder(int floatBuffers, int intBuffers) {
        this.floatBuffers = new FloatArrayStack[floatBuffers];
        this.intBuffers = new IntArrayStack[intBuffers];
        this.sizePerVertex = new int[floatBuffers + intBuffers];
    }
    
    public void createFloatBuffer(int size, int index, int sizePerVertex) {
        floatBuffers[index] = new FloatArrayStack(size);
        this.sizePerVertex[index] = sizePerVertex;
    }
    
    public void createIntBuffer(int size, int index, int sizePerVertex) {
        intBuffers[index] = new IntArrayStack(size);
        this.sizePerVertex[index + floatBuffers.length] = sizePerVertex;
    }
    
    public void createIndexBuffer(int size) {
        indexBuffer = new IntArrayStack(size);
    }
    
    public VAOBuilder put(int attrib, float... data) {
        floatBuffers[attrib].put(data);
        return this;
    }
    
    public VAOBuilder put(int attrib, int... data) {
        intBuffers[attrib].put(data);
        return this;
    }
    
    public VAOBuilder put(int attrib, Vector4f data) {
        return put(attrib, data.x, data.y, data.z, data.w);
    }
    
    public VAOBuilder put(int attrib, Vector4i data) {
        return put(attrib, data.x, data.y, data.z, data.w);
    }
    
    public VAOBuilder put(int attrib, Vector3f data) {
        return put(attrib, data.x, data.y, data.z);
    }
    
    public VAOBuilder put(int attrib, Vector3i data) {
        return put(attrib, data.x, data.y, data.z);
    }
    
    public VAOBuilder put(int attrib, Vector2f data) {
        return put(attrib, data.x, data.y);
    }
    
    public VAOBuilder put(int attrib, Vector2i data) {
        return put(attrib, data.x, data.y);
    }
    
    public VAOBuilder putIndex(int... indices) {
        indexBuffer.put(indices);
        return this;
    }
    
    public VAO createVAO() {
        VAO vao = new VAO();
        vao.bind();
        for (int i = 0; i < floatBuffers.length; i++) {
            float[] buffer = floatBuffers[i].getArray();
            vao.createAttribute(i, buffer, sizePerVertex[i]);
        }
        for (int i = 0; i < intBuffers.length; i++) {
            int[] buffer = intBuffers[i].getArray();
            vao.createIntAttribute(i + floatBuffers.length, buffer, sizePerVertex[i + floatBuffers.length]);
        }
        vao.createIndexBuffer(indexBuffer.getArray());
        vao.unbind();
        
        return vao;
    }
}
