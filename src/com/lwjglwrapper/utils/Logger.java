/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils;

/**
 *
 * @author Welcome
 */
public class Logger {
    public static boolean enable;
    
    public static void logln(Object obj) {
        if(enable)  System.out.println(obj);
    }
    
    public static void log(Object obj) {
        if(enable)  System.out.print(obj);
    } 
    
    public static void logln(Object tag, Object message) {
        logln("[" + tag + "] " + message);
    }
    
    public static void log(Object tag, Object message) {
        log("[" + tag + "] " + message);
    }
}
