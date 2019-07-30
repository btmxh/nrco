/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders.uniforms.variables;

import com.lwjglwrapper.opengl.shaders.Shader;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Welcome
 */
public class UVec2 extends UniformVariable<Vector2f>{

    public UVec2(Shader shader, String variableName) {
        super(shader, variableName);
    }

    @Override
    public void load(Vector2f value) {
        GL20.glUniform2f(location, value.x, value.y);
    }
    
    public void load(float x, float y) {
        GL20.glUniform2f(location, x, y);
    }
    
}
