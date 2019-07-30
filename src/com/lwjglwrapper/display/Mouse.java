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
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorEnterCallbackI;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;
import org.lwjgl.glfw.GLFWScrollCallbackI;

/**
 *
 * @author Welcome
 */
public class Mouse {

    GLFWCursorEnterCallbackI enterCallback;
    GLFWCursorPosCallbackI mousePositionCallback;
    GLFWMouseButtonCallbackI buttonCallback;
    GLFWScrollCallbackI scrollCallback;

    public Mouse() {
        this.scrollCallback = (winID, xOff, yOff) -> scroll(LWJGL.allWindows.get(winID), xOff, yOff);
        this.buttonCallback = (winID, button, action, mods) -> mouseButton(LWJGL.allWindows.get(winID), button, action, mods);
        this.mousePositionCallback = (winID, xPos, yPos) -> mousePosition(LWJGL.allWindows.get(winID), xPos, yPos);
        this.enterCallback = (winID, entered) -> cursorEnter(LWJGL.allWindows.get(winID), entered);
        
        mousePos = new Vector2i();
        lastFrameMousePos = new Vector2i();
        
        buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
        lastFrameButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    }

    public void cursorEnter(Window window, boolean entered) {}
    public void mousePosition(Window window, double x, double y) {
        mousePos = new Vector2i((int) x, (int) y);
    }
    public void mouseButton(Window window, int button, int action, int mods) {
        buttons[button] = action != GLFW.GLFW_RELEASE;
    }
    public void scroll(Window window, double xOff, double yOff) {}
    
    private Vector2i mousePos, lastFrameMousePos;
    private boolean[] buttons, lastFrameButtons;
    
    public void storeLastFrame() {
        lastFrameMousePos = new Vector2i(mousePos);
        lastFrameButtons = Arrays.copyOf(buttons, buttons.length);
    }
    
    public boolean mouseDown(int button)     {  return buttons[button]; }
    public boolean mousePressed(int button)  {  return buttons[button] && !lastFrameButtons[button]; }
    public boolean mouseReleased(int button) {  return !buttons[button] && lastFrameButtons[button]; }
    
    public boolean anyMouseDown(int... buttons)     {  return IntStream.of(buttons).anyMatch(this::mouseDown); }
    public boolean anyMousePressed(int... buttons)  {  return IntStream.of(buttons).anyMatch(this::mousePressed); }
    public boolean anyMouseReleased(int... buttons) {  return IntStream.of(buttons).anyMatch(this::mouseReleased); }
    
    public boolean allMouseDown(int... buttons)     {  return IntStream.of(buttons).allMatch(this::mouseDown); }
    public boolean allMousePressed(int... buttons)  {  return IntStream.of(buttons).allMatch(this::mousePressed); }
    public boolean allMouseReleased(int... buttons) {  return IntStream.of(buttons).allMatch(this::mouseReleased); }
    
    public boolean anyMouseDown(Collection<Integer> buttons)     {  return buttons.stream().anyMatch(this::mouseDown); }
    public boolean anyMousePressed(Collection<Integer> buttons)  {  return buttons.stream().anyMatch(this::mousePressed); }
    public boolean anyMouseReleased(Collection<Integer> buttons) {  return buttons.stream().anyMatch(this::mouseReleased); }
    
    public boolean allMouseDown(Collection<Integer> buttons)     {  return buttons.stream().allMatch(this::mouseDown); }
    public boolean allMousePressed(Collection<Integer> buttons)  {  return buttons.stream().allMatch(this::mousePressed); }
    public boolean allMouseReleased(Collection<Integer> buttons) {  return buttons.stream().allMatch(this::mouseReleased); }
    
    public int getCursorX() { return mousePos.x;  }
    public int getCursorY() { return mousePos.y;  }
    public float getDeltaX() {  return (float) ((mousePos.x - lastFrameMousePos.x) * LWJGL.currentLoop.getDeltaTime());   }
    public float getDeltaY() {  return (float) ((mousePos.y - lastFrameMousePos.y) * LWJGL.currentLoop.getDeltaTime());   }

    public Vector2f getCursorPosition() {
        return new Vector2f(mousePos);
    }
    
}
