/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders.uniforms.arrays;

import com.lwjglwrapper.opengl.shaders.Shader;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UVec4;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Welcome
 */
public class UVec4Array extends UniformArray<Vector4f>{

    public UVec4Array(Shader shader, String variableName) {
        super(shader, variableName);
    }

    @Override
    protected void loadSingle(int location, Vector4f value) {
        GL20.glUniform4f(location, value.x, value.y, value.z, value.w);
    }
    
}
