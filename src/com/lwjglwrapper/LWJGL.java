/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper;

import com.lwjglwrapper.display.Joystick;
import com.lwjglwrapper.display.Keyboard;
import com.lwjglwrapper.display.Loop;
import com.lwjglwrapper.display.Mouse;
import com.lwjglwrapper.display.Window;
import com.lwjglwrapper.display.WindowCallbacks;
import com.lwjglwrapper.nanovg.NVGGraphics;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Welcome
 */
public class LWJGL {
    public static Map<Long, Window> allWindows = new HashMap<>();
    
    public static Window window;
    public static Loop currentLoop;
    
    public static Keyboard keyboard;
    public static Mouse mouse;
    public static Joystick joystick;
    public static WindowCallbacks windowCallbacks;
    
    public static NVGGraphics graphics;
    
}
