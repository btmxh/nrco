/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.display;

import com.lwjglwrapper.LWJGL;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCharCallbackI;
import org.lwjgl.glfw.GLFWCharModsCallbackI;
import org.lwjgl.glfw.GLFWKeyCallbackI;

/**
 *
 * @author Welcome
 */
public class Keyboard {

    GLFWCharCallbackI charCallback;
    GLFWCharModsCallbackI charModsCallback;
    GLFWKeyCallbackI keyCallback;

    public Keyboard() {
        keyStates = new boolean[GLFW.GLFW_KEY_LAST + 1];
        lastKeyStates = new boolean[GLFW.GLFW_KEY_LAST + 1];
        
        this.charCallback = (winID, codePoint) -> charAction(LWJGL.allWindows.get(winID), codePoint);
        this.charModsCallback = (winID, codePoint, mods) -> charModsAction(LWJGL.allWindows.get(winID), codePoint, mods);
        this.keyCallback = (winID, key, scancode, action, mods) -> keyAction(LWJGL.allWindows.get(winID), key, scancode, action, mods);
    }
    
    public void charAction(Window window, int codePoint) {
        if(window.getStage() != null) {
            window.getStage().charEntered(codePoint);
        }
    }
    public void charModsAction(Window window, int codePoint, int mods) {}
    public void keyAction(Window window, int key, int scancode, int action, int mods) {
        if(key < 0) return;
        keyStates[key] = action != GLFW.GLFW_RELEASE;
    }
    
    private boolean[] keyStates, lastKeyStates;
    
    public void storeKeyStates() {
        lastKeyStates = Arrays.copyOf(keyStates, keyStates.length);
    }
    
    public boolean keyDown(int key)     {   return keyStates[key];  }
    public boolean keyPressed(int key)  {   return !lastKeyStates[key] && keyStates[key];   }
    public boolean keyReleased(int key) {   return lastKeyStates[key] && !keyStates[key];   }
    
    public boolean anyKeyDown(int... keys)     {  return IntStream.of(keys).anyMatch(this::keyDown); }
    public boolean anyKeyPressed(int... keys)  {  return IntStream.of(keys).anyMatch(this::keyPressed); }
    public boolean anyKeyReleased(int... keys) {  return IntStream.of(keys).anyMatch(this::keyReleased); }
    
    public boolean allKeyDown(int... keys)     {  return IntStream.of(keys).allMatch(this::keyDown); }
    public boolean allKeyPressed(int... keys)  {  return IntStream.of(keys).allMatch(this::keyPressed); }
    public boolean allKeyReleased(int... keys) {  return IntStream.of(keys).allMatch(this::keyReleased); }
    
    public boolean anyKeyDown(Collection<Integer> keys)     {  return keys.stream().anyMatch(this::keyDown); }
    public boolean anyKeyPressed(Collection<Integer> keys)  {  return keys.stream().anyMatch(this::keyPressed); }
    public boolean anyKeyReleased(Collection<Integer> keys) {  return keys.stream().anyMatch(this::keyReleased); }
    
    public boolean allKeyDown(Collection<Integer> keys)     {  return keys.stream().allMatch(this::keyDown); }
    public boolean allKeyPressed(Collection<Integer> keys)  {  return keys.stream().allMatch(this::keyPressed); }
    public boolean allKeyReleased(Collection<Integer> keys) {  return keys.stream().allMatch(this::keyReleased); }
}
