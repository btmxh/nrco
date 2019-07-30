/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders.uniforms.variables;

import com.lwjglwrapper.opengl.shaders.Shader;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Welcome
 */
public class UInt extends UniformVariable<Integer>{

    public UInt(Shader shader, String variableName) {
        super(shader, variableName);
    }

    @Override
    public void load(Integer value) {
        GL20.glUniform1i(location, value);
    }
    
}
