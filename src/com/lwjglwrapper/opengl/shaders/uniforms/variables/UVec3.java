/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders.uniforms.variables;

import com.lwjglwrapper.opengl.shaders.Shader;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Welcome
 */
public class UVec3 extends UniformVariable<Vector3f>{

    public UVec3(Shader shader, String variableName) {
        super(shader, variableName);
    }

    @Override
    public void load(Vector3f value) {
        GL20.glUniform3f(location, value.x, value.y, value.z);
    }
    
    public void load(float x, float y, float z) {
        GL20.glUniform3f(location, x, y, z);
    }
    
}
