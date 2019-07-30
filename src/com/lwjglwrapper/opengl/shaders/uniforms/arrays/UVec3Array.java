/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders.uniforms.arrays;

import com.lwjglwrapper.opengl.shaders.Shader;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Welcome
 */
public class UVec3Array extends UniformArray<Vector3f>{

    public UVec3Array(Shader shader, String variableName) {
        super(shader, variableName);
    }

    @Override
    protected void loadSingle(int location, Vector3f value) {
        GL20.glUniform3f(location, value.x, value.y, value.z);
    }
    
}
