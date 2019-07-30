/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders.uniforms.variables;

import com.lwjglwrapper.opengl.shaders.Shader;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Welcome
 */
public class UVec4 extends UniformVariable<Vector4f>{

    public UVec4(Shader shader, String variableName) {
        super(shader, variableName);
    }

    @Override
    public void load(Vector4f value) {
        GL20.glUniform4f(location, value.x, value.y, value.z, value.w);
    }
    
    public void load(float x, float y, float z, float w) {
        GL20.glUniform4f(location, x, y, z, w);
    }
    
}
