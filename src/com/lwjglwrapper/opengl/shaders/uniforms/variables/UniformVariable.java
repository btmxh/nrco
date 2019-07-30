/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders.uniforms.variables;

import com.lwjglwrapper.opengl.shaders.Shader;

/**
 *
 * @author Welcome
 */
public abstract class UniformVariable<T> {
    
    protected int location;

    public UniformVariable(Shader shader, String variableName) {
        location = shader.getUniformLocation(variableName);
    }
    
    public abstract void load(T value);
    
}
