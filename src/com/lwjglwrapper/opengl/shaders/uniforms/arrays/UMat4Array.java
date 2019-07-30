/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders.uniforms.arrays;

import com.lwjglwrapper.opengl.shaders.Shader;
import java.nio.FloatBuffer;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

/**
 *
 * @author Welcome
 */
public class UMat4Array extends UniformArray<Matrix4f>{

    public UMat4Array(Shader shader, String variableName) {
        super(shader, variableName);
    }

    @Override
    protected void loadSingle(int location, Matrix4f value) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = value.get(stack.mallocFloat(16));
            GL20.glUniformMatrix4fv(location, false, buffer);
        }
    }
    
}
