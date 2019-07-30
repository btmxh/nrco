/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author Welcome
 */
public class GLCalls {

    public static void enableDepthTest() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
    
}
