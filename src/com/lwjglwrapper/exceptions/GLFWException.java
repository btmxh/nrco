/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.exceptions;


public class GLFWException extends LWJGLException {

    public GLFWException() {
    }

    public GLFWException(String message) {
        super(message);
    }

    public GLFWException(String message, Throwable cause) {
        super(message, cause);
    }

    public GLFWException(Throwable cause) {
        super(cause);
    }
    
}
