/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Welcome
 */
public class Shader {
    int id;
    private List<ShaderFile> shaders;
    
    private Shader() {
        id = GL20.glCreateProgram();
        shaders = new ArrayList<>();
    }
    
    public Shader(ShaderFile vertexShader, ShaderFile fragmentShader) {
        this();
        
        ShaderFile.verifyType(vertexShader, GL20.GL_VERTEX_SHADER);
        ShaderFile.verifyType(fragmentShader, GL20.GL_FRAGMENT_SHADER);
        
        shaders.add(vertexShader);
        shaders.add(fragmentShader);
        
        vertexShader.attach(this);
        fragmentShader.attach(this);
        
        GL20.glLinkProgram(id);
        GL20.glValidateProgram(id);
    }
    
    public void bind() {
        GL20.glUseProgram(id);
    }
    
    public void unbind() {
        GL20.glUseProgram(0);
    }
    
    public int getUniformLocation(String variableName) {
        return GL20.glGetUniformLocation(id, variableName);
    }
    
    public void dispose() {
        unbind();
        
        for (ShaderFile shaderFile : shaders) {
            shaderFile.detach(this);
            shaderFile.delete();
        }
        
        GL20.glDeleteProgram(id);
    }
    
}
