/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders;

import com.lwjglwrapper.exceptions.LWJGLException;
import com.lwjglwrapper.exceptions.OpenGLException;
import com.lwjglwrapper.utils.Utils;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GL40;

/**
 *
 * @author Welcome
 */
public class ShaderFile {

    

    private final int id;
    private final int type;

    private ShaderFile(String sourceCode, int shaderType) {
        this.type = shaderType;
        id = GL20.glCreateShader(shaderType);
        GL20.glShaderSource(id, sourceCode);
        GL20.glCompileShader(id);
        
        if(GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.out.println("Error compiling shader (type: " + typeString(type)+ "):");
            System.out.println(GL20.glGetShaderInfoLog(id));
        }
    }
    
    public static ShaderFile fromFile(String filePath, int shaderType) throws IOException {
        return new ShaderFile(Utils.loadFileAsString(filePath), shaderType);
    }
    
    public static ShaderFile fromSource(String sourceCode, int shaderType) {
        return new ShaderFile(sourceCode, shaderType);
    }
    
    public static ShaderFile fromResource(String resPath, int shaderType) {
        try {
            return new ShaderFile(Utils.loadResourceAsString(resPath), shaderType);
        } catch (IOException ex) {
            Logger.getLogger(ShaderFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void attach(Shader shader) {
        GL20.glAttachShader(shader.id, id);
    }
    
    public void detach(Shader shader) {
        GL20.glDetachShader(shader.id, id);
    }
    
    public void delete() {
        GL20.glDeleteShader(id);
    }

    private static String typeString(int type) {
        switch(type) {
            case GL20.GL_VERTEX_SHADER: return "vertex shader";
            case GL32.GL_GEOMETRY_SHADER: return "geometry shader";
            case GL20.GL_FRAGMENT_SHADER: return "fragment shader";
            case GL40.GL_TESS_EVALUATION_SHADER: return "tessellation evaluation shader";
            case GL40.GL_TESS_CONTROL_SHADER: return "tessellation control shader";
            
            default: return "Invalid shader type";
        }
    }
    
    static void verifyType(ShaderFile shaderFile, int type) {
        Objects.requireNonNull(shaderFile);
        if(shaderFile.type != type) {
            throw new OpenGLException("Wrong shader type: " + typeString(shaderFile.type) + "!=" + typeString(type));
        }
    }

    
}
