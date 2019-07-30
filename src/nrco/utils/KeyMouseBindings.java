/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.utils;

import com.lwjglwrapper.LWJGL;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Welcome
 */
public class KeyMouseBindings {
    public static boolean holding() {
        return LWJGL.keyboard.anyKeyDown(GLFW.GLFW_KEY_X) | LWJGL.mouse.anyMouseDown(GLFW.GLFW_MOUSE_BUTTON_LEFT);
    }
    
    public static boolean holdingNone() {
        return LWJGL.keyboard.anyKeyPressed(GLFW.GLFW_KEY_X) | LWJGL.mouse.anyMousePressed(GLFW.GLFW_MOUSE_BUTTON_LEFT);
    }
    
    public static boolean drop() {
        return LWJGL.keyboard.anyKeyReleased(GLFW.GLFW_KEY_X) | LWJGL.mouse.anyMouseReleased(GLFW.GLFW_MOUSE_BUTTON_LEFT);
    }
}
