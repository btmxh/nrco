/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.display;

import org.lwjgl.glfw.GLFWJoystickCallbackI;

/**
 *
 * @author Welcome
 */
public class Joystick implements GLFWJoystickCallbackI{

    @Override
    public void invoke(int jid, int event) {
        joystickEvent(jid, event);
    }
    
    public void joystickEvent(int jid, int event) {}
    
}
