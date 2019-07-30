/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders.uniforms.arrays;

import com.lwjglwrapper.opengl.shaders.Shader;
import java.util.List;

/**
 *
 * @author Welcome
 */
public abstract class UniformArray<T> {
    protected int location;

    public UniformArray(Shader shader, String variableName) {
        location = shader.getUniformLocation(variableName);
    }
    
    protected abstract void loadSingle(int location, T value);
    
    public void load(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T value = list.get(i);
            loadSingle(location + i, value);
        }
    }
    
    public void load(T[] array) {
        for (int i = 0; i < array.length; i++) {
            T value = array[i];
            loadSingle(location + i, value);
        }
    }
    
    
}
