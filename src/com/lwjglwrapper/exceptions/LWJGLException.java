/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.exceptions;

/**
 *
 * @author Welcome
 */
public class LWJGLException extends RuntimeException {

    public LWJGLException() {
        super();
    }
    
    public LWJGLException(String message) {
        super(message);
    }
    
    public LWJGLException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public LWJGLException(Throwable cause) {
        super(cause);
    }

    
}
