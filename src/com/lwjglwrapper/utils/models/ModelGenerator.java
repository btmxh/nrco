/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.models;

import com.lwjglwrapper.opengl.objects.Texture2D;
import com.lwjglwrapper.opengl.objects.TextureCube;
import com.lwjglwrapper.opengl.objects.TexturedVAO;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.opengl.objects.VAO;
import com.lwjglwrapper.utils.math.FloatMath;
import com.lwjglwrapper.utils.models.objs.OBJ;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joml.Vector3f;

/**
 *
 * @author Welcome
 */
public class ModelGenerator {

    public List<IColor> colors = new ArrayList<>();

    public OBJLoader loader;

    public ModelGenerator(OBJLoader loader) {
        this.loader = loader;
    }

    public ModelGenerator() {
        this(new OBJLoader());
    }

    /**
     * Create a cube VAO, that could be transformed when render
     *
     * @param center center of the cube
     * @return the cube VAO
     */
    public VAO cube() {
        try {
            return loader.loadOBJ(OBJ.class.getResourceAsStream("cube.obj"), true);
        } catch (IOException ex) {
            throw new InternalError(ex);
        }
    }

    public TexturedVAO texturedCube2D(String texPath, int texSlot) {
        try {
            return loader.loadOBJ(OBJ.class.getResourceAsStream("cube.obj"),
                    new Texture2D(texPath), texSlot, true);
        } catch (IOException ex) {
            throw new InternalError(ex);
        }
    }

    public TexturedVAO texturedCube3D(TextureCube texture3d, int texSlot) {
        try {
            return loader.loadOBJ(OBJ.class.getResourceAsStream("cube.obj"),
                    texture3d, texSlot, true);
        } catch (IOException ex) {
            throw new InternalError(ex);
        }
    }

    public VAO rect(float x, float y, float w, float h) {
        VAOBuilder builder = new VAOBuilder(1, 0);
        builder.createFloatBuffer(8, 0, 2);
        builder.createIndexBuffer(6);
        builder.put(loader.positionAttrib, x, y);
        builder.put(loader.positionAttrib, x + w, y);
        builder.put(loader.positionAttrib, x + w, y + h);
        builder.put(loader.positionAttrib, x, y + h);
        builder.putIndex(0, 1, 2, 0, 2, 3);
        return builder.createVAO();
    }

    public VAO meshXYZ(float[][] heights, Vector3f offset, Vector3f scale,
            int width, int height) {
        //Must Enable GL_PRIMITIVE_RESTART_FIXED_INDEX to render properly
        if (width < 0) {
            width = heights.length - 1;
        }
        if (height < 0) {
            height = heights[0].length - 1;
        }
        return meshXYZ((x, z) -> heights[x][z], offset, scale, width, height);
    }

    public VAO meshXYZ(BiFunction<Integer, Integer, Float> heightFunction,
            Vector3f offset, Vector3f scale, int width, int height) {
        int horizontalVertexCount = width + 1;
        int verticalVertexCount = height + 1;
        VAOBuilder builder = new VAOBuilder(1, 0);
        builder.createFloatBuffer(horizontalVertexCount * verticalVertexCount * 3, 0, 3);
        builder.createIndexBuffer(height * (2 * horizontalVertexCount + 1));
        for (int z = 0; z < verticalVertexCount; z++) {
            for (int x = 0; x < horizontalVertexCount; x++) {
                builder.put(0, new Vector3f(offset).add(scale.mul(x, heightFunction.apply(x, z), z, new Vector3f())));
            }
        }
        for (int z = 0; z < height; z++) {
            for (int x = 0; x < horizontalVertexCount; x++) {
                builder.putIndex(z * horizontalVertexCount + x, (z + 1) * horizontalVertexCount + x);
            }
            builder.putIndex(-1);
        }
        return builder.createVAO();
    }
}
