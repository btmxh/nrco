/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.models;

import com.lwjglwrapper.opengl.objects.Texture;
import com.lwjglwrapper.opengl.objects.Texture2D;
import com.lwjglwrapper.opengl.objects.TexturedVAO;
import com.lwjglwrapper.opengl.objects.VAO;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjData;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;
import de.javagl.obj.ReadableObj;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Welcome
 */
public class OBJLoader {
    int positionAttrib, texCoordAttrib, normalAttrib;

    public OBJLoader() {
        this(0, 1, 2);
    }
    
    public OBJLoader(int positionAttrib, int texCoordAttrib, int normalAttrib) {
        this.positionAttrib = positionAttrib;
        this.texCoordAttrib = texCoordAttrib;
        this.normalAttrib = normalAttrib;
    }
    
    public TexturedVAO loadOBJ(InputStream object, Texture texture, int texSlot, boolean flipY) throws IOException {
        Obj obj = ObjUtils.convertToRenderable(ObjReader.read(object));
        
        float[] position = ObjData.getVerticesArray(obj);
        float[] texCoord = ObjData.getTexCoordsArray(obj, 2, flipY);
        float[] normals = ObjData.getNormalsArray(obj);
        int[] indices = ObjData.getFaceNormalIndicesArray(obj);
        
        TexturedVAO vao = new TexturedVAO(texSlot, texture);
        vao.bind();
        vao.createAttribute(positionAttrib, position, 3);
        vao.createAttribute(texCoordAttrib, texCoord, 2);
        vao.createAttribute(normalAttrib, normals, 3);
        vao.createIndexBuffer(indices);
        vao.unbind();
        
        return vao;
    }
    
    public VAO loadOBJ(InputStream object, boolean flipY) throws IOException {
        Obj obj = ObjUtils.convertToRenderable(ObjReader.read(object));
        
        float[] position = ObjData.getVerticesArray(obj);
        float[] texCoord = ObjData.getTexCoordsArray(obj, 2, flipY);
        float[] normals = ObjData.getNormalsArray(obj);
        int[] indices = ObjData.getFaceNormalIndicesArray(obj);
        
        VAO vao = new VAO();
        vao.bind();
        vao.createAttribute(positionAttrib, position, 3);
        vao.createAttribute(texCoordAttrib, texCoord, 2);
        vao.createAttribute(normalAttrib, normals, 3);
        vao.createIndexBuffer(indices);
        vao.unbind();
        
        return vao;
    }

}
