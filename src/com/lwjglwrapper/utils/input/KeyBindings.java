/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.input;

import com.lwjglwrapper.display.Keyboard;
import com.lwjglwrapper.LWJGL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Welcome
 */
public class KeyBindings {

    public final Map<String, Set<Integer>> keyBindings;
    private final Keyboard keyboard;

    public KeyBindings() {
        this(LWJGL.keyboard);
    }
    
    public KeyBindings(Keyboard keyboard) {
        keyBindings = new HashMap<>();
        this.keyboard = keyboard;
    }

    public void add(String name, int... keys) {
        Set<Integer> keySet = keyBindings.get(name);
        if (keySet == null) {
            keySet = new HashSet<>();
            keyBindings.put(name, keySet);
        }
        for (int key : keys) {
            keySet.add(key);
        }
    }

    public boolean clear(String name) {
        return keyBindings.remove(name) != null;
    }

    public void clearAll() {
        keyBindings.clear();
    }

    public boolean anyDown(String name) {
        Set<Integer> set = keyBindings.get(name);
        if (set == null) {
            return false;
        }
        return keyboard.anyKeyDown(set);
    }

    public boolean anyPressed(String name) {
        Set<Integer> set = keyBindings.get(name);
        if (set == null) {
            return false;
        }
        return keyboard.anyKeyPressed(keyBindings.get(name));
    }

    public boolean anyReleased(String name) {
        Set<Integer> set = keyBindings.get(name);
        if (set == null) {
            return false;
        }
        return keyboard.anyKeyReleased(keyBindings.get(name));
    }

    public boolean allDown(String name) {
        Set<Integer> set = keyBindings.get(name);
        if (set == null) {
            return false;
        }
        return keyboard.allKeyDown(keyBindings.get(name));
    }

    public boolean allPressed(String name) {
        Set<Integer> set = keyBindings.get(name);
        if (set == null) {
            return false;
        }
        return keyboard.allKeyPressed(keyBindings.get(name));
    }

    public boolean allReleased(String name) {
        Set<Integer> set = keyBindings.get(name);
        if (set == null) {
            return false;
        }
        return keyboard.allKeyReleased(keyBindings.get(name));
    }

    public KeyBindings defaultKeyBindings() {
        this.add("up", GLFW.GLFW_KEY_ENTER, GLFW.GLFW_KEY_SPACE);
        this.add("down", GLFW.GLFW_KEY_RIGHT_SHIFT, GLFW.GLFW_KEY_LEFT_CONTROL);
        this.add("left", GLFW.GLFW_KEY_LEFT, GLFW.GLFW_KEY_A);
        this.add("right", GLFW.GLFW_KEY_RIGHT, GLFW.GLFW_KEY_D);
        this.add("forward", GLFW.GLFW_KEY_UP, GLFW.GLFW_KEY_W);
        this.add("backward", GLFW.GLFW_KEY_DOWN, GLFW.GLFW_KEY_S);
        this.add("exit", GLFW.GLFW_KEY_ESCAPE);

        return this;
    }
    
    public KeyBindings debugKeyBindings() {
        this.add("polygon", GLFW.GLFW_KEY_LEFT_ALT);
        return this;
    }
}
