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
public class GameUtils {
    public static long getJavaHeap() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
    
    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }
    
    public static String formatMem(long mem) {
        if(mem < 2 << 9) return mem + "bytes";
        else if(mem < 2 << 19)  return (mem >> 9) + "KB";
        else if(mem < 2 << 29)  return (mem >> 19) + "MB";
        else if(mem < 2 << 39)    return (mem >> 29) + "GB";
        else return "WTF";
    }
}
