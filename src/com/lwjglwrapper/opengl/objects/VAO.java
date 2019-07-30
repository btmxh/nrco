/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.objects;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author Welcome
 */
public class VAO {

    public static final int RENDER_ALL = -1;

    private int id;
    private int vertexCount;

    private List<Integer> lastEnabled;
    private List<VBO> vbos;
    private VBO indexVBO;

    private List<Integer> allAttribs;

    public VAO() {
        id = GL30.glGenVertexArrays();
        vbos = new ArrayList<>();
        vertexCount = -1;
        allAttribs = new ArrayList<>();
        lastEnabled = new ArrayList<>();
    }

    public void bind(int... attribs) {
        lastEnabled.clear();
        IntStream.of(attribs).forEach(lastEnabled::add);
        bind();
        IntStream.of(attribs).forEach(GL30::glEnableVertexAttribArray);
    }

    public void bind(List<Integer> attribs) {
        lastEnabled.clear();
        lastEnabled.addAll(attribs);
        bind();
        attribs.stream().forEach(GL30::glEnableVertexAttribArray);
    }

    public void bindAll() {
        bind(allAttribs);
    }

    //Unbind all vbo you last enabled when call the bind(attribs) method
    public void unbindByLastBind() {
        unbind(lastEnabled);
        lastEnabled.clear();
    }

    public void unbind(int... attribs) {
        IntStream.of(attribs).forEach(GL30::glDisableVertexAttribArray);
        unbind();
    }
    
    public void unbind(List<Integer> attribs) {
        attribs.forEach(GL30::glDisableVertexAttribArray);
        unbind();
    }

    protected void bind() {
        GL30.glBindVertexArray(id);
    }

    protected void unbind() {
        GL30.glBindVertexArray(0);
    }

    public void createIndexBuffer(int[] indices) {
        this.indexVBO = new VBO(GL15.GL_ELEMENT_ARRAY_BUFFER);
        indexVBO.bind();
        indexVBO.storeData(indices);
        this.vertexCount = indices.length;
    }
    
    public void createIndexBuffer(IntBuffer indexBuffer, int vertexCount) {
        this.indexVBO = new VBO(GL15.GL_ELEMENT_ARRAY_BUFFER);
        indexVBO.bind();
        indexBuffer.flip();
        indexVBO.storeData(indexBuffer);
        this.vertexCount = vertexCount;
    }

    public void createAttribute(int attribute, float[] data, int attrSize) {
        VBO vbo = new VBO(GL15.GL_ARRAY_BUFFER);
        vbo.bind();
        vbo.storeData(data);
        GL20.glVertexAttribPointer(attribute, attrSize, GL11.GL_FLOAT, false,
                attrSize * Float.BYTES, 0);
        vbo.unbind();
        vbos.add(vbo);
        allAttribs.add(attribute);
        
        if(indexVBO == null)    vertexCount = data.length / attrSize;
    }

    public void createIntAttribute(int attribute, int[] data, int attrSize) {
        VBO vbo = new VBO(GL15.GL_ARRAY_BUFFER);
        vbo.bind();
        
        vbo.storeData(data);
        GL30.glVertexAttribIPointer(attribute, attrSize, GL11.GL_INT,
                attrSize * Integer.BYTES, 0);
        vbo.unbind();
        vbos.add(vbo);
        allAttribs.add(attribute);
    
        if(indexVBO == null)    vertexCount = data.length / attrSize;
    }
    
    public void createAttribute(int attribute, FloatBuffer data, int attrSize) {
        VBO vbo = new VBO(GL15.GL_ARRAY_BUFFER);
        vbo.bind();
        data.flip();
        vbo.storeData(data);
        GL20.glVertexAttribPointer(attribute, attrSize, GL11.GL_FLOAT, false,
                attrSize * Float.BYTES, 0);
        vbo.unbind();
        vbos.add(vbo);
        allAttribs.add(attribute);
    }

    public void createIntAttribute(int attribute, IntBuffer data, int attrSize) {
        VBO vbo = new VBO(GL15.GL_ARRAY_BUFFER);
        vbo.bind();
        data.flip();
        vbo.storeData(data);
        GL30.glVertexAttribIPointer(attribute, attrSize, GL11.GL_INT,
                attrSize * Integer.BYTES, 0);
        vbo.unbind();
        vbos.add(vbo);
        allAttribs.add(attribute);
    }

    
    
    public void dispose() {
        for (VBO vbo : vbos) {
            vbo.dispose();
        }

        GL30.glDeleteVertexArrays(id);
        if (indexVBO != null) {
            indexVBO.dispose();
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }
    
    public void renderArray(int mode, int first, int count) {
        if(count == -1) count = vertexCount;
        GL11.glDrawArrays(mode, first, count);
    }
    
    public void renderElement(int mode, int first, int count) {
        if(count == -1) count = vertexCount;
        GL11.glDrawElements(mode, count, GL11.GL_UNSIGNED_INT, first);
    }

    
}
