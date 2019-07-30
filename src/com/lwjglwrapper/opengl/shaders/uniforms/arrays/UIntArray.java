/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders.uniforms.arrays;

import com.lwjglwrapper.opengl.shaders.Shader;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Welcome
 */
public class UIntArray extends UniformArray<Integer>{

    public UIntArray(Shader shader, String variableName) {
        super(shader, variableName);
    }

    @Override
    protected void loadSingle(int location, Integer value) {
        GL20.glUniform1i(location, value);
    }
    
}
