/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.display;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.exceptions.GLFWException;
import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.opengl.objects.TextureData;
import com.lwjglwrapper.utils.ui.Stage;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

/**
 * A single wrapper class for GLFW Window
 * @author Welcome
 */
public class Window {

    /**
     * the GLFW window id
     */
    private long winID;

    /**
     * Width of the GLFW window
     */
    private int width;
    
    /**
     * Height of the GLFW window
     */
    private int height;
    
    /**
     * Title of the GLFW window
     */
    private String title;
    
    /**
     * FPS Cap of the GLFW window
     */
    private int fpsCap;

    /**
     * Keyboard input component of the window
     */
    private Keyboard keyboard;
    
    /**
     * Mouse input component of the window
     */
    private Mouse mouse;
    
    /**
     * Joystick input component of the window
     */
    private Joystick joystick;
    
    /**
     * Window callbacks of the window
     */
    private WindowCallbacks windowCallbacks;

    /**
     * NanoVG Graphics of the window, must be created after {@link create() create()} by {@link createNVGGraphics() createNVGGraphics()} method
     */
    private NVGGraphics nvgGraphics;
    
    /**
     * Stage of the window, must be created after {@link create() create()} by {@link createStage() createStage()} method
     */
    private Stage stage;
    
    /**
     * Standard Cursor Map of the window
     */
    private Map<Integer, Long> standardCursors = new HashMap<>();
    
    /**
     * Context Scale of the window
     */
    private float xscale = 1, yscale = 1;
    private GLCapabilities glCapabilities;
    private boolean fullScreen;
    
    /**
     * Constructor for a window with size 1280x720, title "Game", FPS cap is 60. After calling the constructor, you must call the {@link create() create()} method in order to create the GLFW Window
     */
    public Window() {
        this(1280, 720, "Game", 60);
    }

    /**
     * Constructor for a window. After calling the constructor, you must call the {@link create() create()} method in order to create the GLFW Window
     * @param width the width of the window
     * @param height the height of the window
     * @param title the title of the window
     * @param fpsCap the FPS cap of the window
     */
    public Window(int width, int height, String title, int fpsCap) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.fpsCap = fpsCap;
        
        keyboard = new Keyboard();
        mouse = new Mouse();
        joystick = new Joystick();
        windowCallbacks = new WindowCallbacks();
    }

    /**
     * Create a GLFW window by the parameters specified in the constructor
     */
    public void create() {
        if (!GLFW.glfwInit()) {
            throw new GLFWException("GLFW initialized unsucessfully");
        }

        
        
        configWindow();
        if(fullScreen) {
            long monitor = GLFW.glfwGetPrimaryMonitor();
            GLFWVidMode mode = GLFW.glfwGetVideoMode(monitor);
            winID = GLFW.glfwCreateWindow(mode.width(), mode.height(), title, monitor, 0);
        } else {
            winID = GLFW.glfwCreateWindow(width, height, title, 0, 0);
        }
        if (winID == 0) {
            throw new GLFWException("Window created unsucessfully");
        }

        LWJGL.allWindows.put(winID, this);
        

        GLFW.glfwSetCharCallback(winID, keyboard.charCallback);
        GLFW.glfwSetCharModsCallback(winID, keyboard.charModsCallback);
        GLFW.glfwSetCursorEnterCallback(winID, mouse.enterCallback);
        GLFW.glfwSetCursorPosCallback(winID, mouse.mousePositionCallback);
        GLFW.glfwSetDropCallback(winID, windowCallbacks.drop);
        GLFW.glfwSetErrorCallback(windowCallbacks.error);
        GLFW.glfwSetFramebufferSizeCallback(winID, windowCallbacks.framebuffer);
        GLFW.glfwSetMonitorCallback(windowCallbacks.monitor);
        GLFW.glfwSetJoystickCallback(joystick);
        GLFW.glfwSetKeyCallback(winID, keyboard.keyCallback);
        GLFW.glfwSetMouseButtonCallback(winID, mouse.buttonCallback);
        GLFW.glfwSetScrollCallback(winID, mouse.scrollCallback);
        GLFW.glfwSetWindowCloseCallback(winID, windowCallbacks.windowClose);
        GLFW.glfwSetWindowContentScaleCallback(winID, windowCallbacks.windowContentScale);
        GLFW.glfwSetWindowFocusCallback(winID, windowCallbacks.windowFocus);
        GLFW.glfwSetWindowIconifyCallback(winID, windowCallbacks.windowIconify);
        GLFW.glfwSetWindowMaximizeCallback(winID, windowCallbacks.windowMaximize);
        GLFW.glfwSetWindowPosCallback(winID, windowCallbacks.position);
        GLFW.glfwSetWindowRefreshCallback(winID, windowCallbacks.refresh);
        GLFW.glfwSetWindowSizeCallback(winID, windowCallbacks.size);
        
        GLFW.glfwMakeContextCurrent(winID);
        glCapabilities = GL.createCapabilities(true);
    }

    /**
     * Show or hide the window
     * @param visible new visibility component of the window
     */
    public void setVisible(boolean visible) {
        if (visible) {
            GLFW.glfwShowWindow(winID);
        } else {
            GLFW.glfwHideWindow(winID);
        }
    }

    /**
     * Updates all the input events
     */
    public void updateInput() {
        keyboard.storeKeyStates();
        mouse.storeLastFrame();
        GLFW.glfwPollEvents();
    }

    /**
     * Swap buffers to update the display
     */
    public void updateDisplay() {

        GLFW.glfwSwapBuffers(winID);
    }

    /**
     * Returns the value of the close flag of this window.
     * @return true if user close the window, false otherwise
     */
    public boolean closeRequested() {
        return GLFW.glfwWindowShouldClose(winID);
    }

    /**
     * Dispose all native resources of this window
     */
    public void destroy() {
        if(nvgGraphics != null) nvgGraphics.dispose();
        setVisible(false);
        GLFW.glfwDestroyWindow(winID);
        LWJGL.allWindows.remove(winID);
    }

    /**
     * Set hint to create window. This method should be used when overriding the {@link configWindow() configWindow()} method
     * @param hint the window hint in {@link org.lwjgl.glfw.GLFW GLFW} class
     * @param value the boolean value to change
     */
    protected void windowHint(int hint, boolean value) {
        GLFW.glfwWindowHint(hint, value ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
    }

    /**
     * Set hint to create window. This method should be used when overriding the {@link configWindow() configWindow()} method
     * @param hint the window hint in {@link org.lwjgl.glfw.GLFW GLFW} class
     * @param value the integer value to change
     */
    protected void windowHint(int hint, int value) {
        GLFW.glfwWindowHint(hint, value);
    }

    /**
     * Set hint to create window. This method should be used when overriding the {@link configWindow() configWindow()} method
     * @param hint the window hint in {@link org.lwjgl.glfw.GLFW GLFW} class
     * @param value the string value to change
     */
    protected void windowHint(int hint, String value) {
        GLFW.glfwWindowHintString(hint, value);
    }

    /**
     * Config window hints before creating the window. This method shouldn't be called by the developer, but it could be overriden to change the window hints
     * @param hint the window hint in {@link org.lwjgl.glfw.GLFW GLFW} class
     * @param value the string value to change
     */
    protected void configWindow() {
        //windowHint(GLFW.GLFW_RESIZABLE, false);
        windowHint(GLFW.GLFW_VISIBLE, false);
        windowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        windowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        windowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        windowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, true);
        
        registerCursor(GLFW.GLFW_IBEAM_CURSOR);
        registerCursor(GLFW.GLFW_ARROW_CURSOR);
    }

    /**
     * Set this window to be the main window stored in {@link com.lwjglwrapper.LWJGL LWJGL}
     */
    public void setToMainWindow() {
        LWJGL.window = this;
        LWJGL.keyboard = keyboard;
        LWJGL.mouse = mouse;
        LWJGL.joystick = joystick;
        LWJGL.windowCallbacks = windowCallbacks;
    }

    /**
     * Set the window position
     * @param x the x position of the window
     * @param y the y position of the window
     */
    public void setPosition(int x, int y) {
        GLFW.glfwSetWindowPos(winID, x, y);
    }

    /**
     * Set the window position to be in the center of the screen
     */
    public void setToCenterScreen() {
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(
                GLFW.glfwGetPrimaryMonitor());
        int screenWidth = videoMode.width();
        int screenHeight = videoMode.height();

        this.setPosition((screenWidth - width) / 2, (screenHeight - height) / 2);
    }

    

    public void setSize(int width, int height) {
        setSizeVariables(width, height);
        GLFW.glfwSetWindowSize(winID, width, height);
    }
    
    void setSizeVariables(int width, int height) {
        this.width = width;
        this.height = height;
        GL11.glViewport(0, 0, width, height);
        if(nvgGraphics != null) {
            nvgGraphics.updateSize(width, height);
        }
    }

    public void setTitle(String newTitle) {
        GLFW.glfwSetWindowTitle(winID, title);
    }

    public float getAspectRatio() {
        return (float) width / (float) height;
    }

    public NVGGraphics createNVGGraphics() {
        nvgGraphics = new NVGGraphics();
        if(LWJGL.window == this) {
            LWJGL.graphics = nvgGraphics;
        }
        return nvgGraphics;
    }

    public void setContentScale(float xscale, float yscale) {
        this.xscale = xscale;
        this.yscale = yscale;
    }

    /**
     * @return the keyboard
     */
    public Keyboard getKeyboard() {
        return keyboard;
    }

    /**
     * @return the mouse
     */
    public Mouse getMouse() {
        return mouse;
    }

    /**
     * @return the joystick
     */
    public Joystick getJoystick() {
        return joystick;
    }

    /**
     * @return the windowCallbacks
     */
    public WindowCallbacks getWindowCallbacks() {
        return windowCallbacks;
    }

    public void createStage() {
        if(nvgGraphics == null) createNVGGraphics();
        stage = new Stage(this, nvgGraphics);
    }
    
    public Stage getStage() {
        return stage;
    }

    /**
     * Set the cursor of this window to which the cursor you specified
     * @param cursor 
     */
    public void setStandardCursor(int cursor) {
        GLFW.glfwSetCursor(winID, standardCursors.get(cursor));
    }
    
    /**
     * Register a built-in cursor and put the data into {@link standardCursors standardCursors} map
     * @param builtinCursor 
     */
    protected void registerCursor(int builtinCursor) {
        long id = GLFW.glfwCreateStandardCursor(builtinCursor);
        standardCursors.put(builtinCursor, id);
    }

    /**
     * Set the cursor to be hidden or visible
     * @param hideCursor true if you want the cursor to be hidden, false otherwise
     * @return the hideCursor parameter that you passed in
     */
    public boolean hideCursor(boolean hideCursor) {
        GLFW.glfwSetInputMode(winID, GLFW.GLFW_CURSOR, hideCursor? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
        return hideCursor;
    }

    /**
     * @return the width of this window
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height of this window
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the title of this window
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the FPS cap of this window
     */
    public int getFPSCap() {
        return fpsCap;
    }
    
    public long addCursor(TextureData cursorData, int xhot, int yhot) {
        GLFWImage image = GLFWImage.malloc().set(cursorData.getWidth(), cursorData.getHeight(), cursorData.getPixelData());
        long cursor = GLFW.glfwCreateCursor(image, xhot, yhot);
        image.free();
        return cursor;
    }
    
    public void setCursor(long cursor) {
        GLFW.glfwSetCursor(winID, cursor);
    }

    public void close() {
        GLFW.glfwSetWindowShouldClose(winID, true);
        windowCallbacks.windowOnClose(this);
    }

    public void fitScreen() {
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        setSize(videoMode.width(), videoMode.height());
    }

    public void setWindowCallbacks(WindowCallbacks windowCallbacks) {
        this.windowCallbacks = windowCallbacks;
    }

    /**
     * @param keyboard the keyboard to set
     */
    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * @param mouse the mouse to set
     */
    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    /**
     * @param joystick the joystick to set
     */
    public void setJoystick(Joystick joystick) {
        this.joystick = joystick;
    }
    
    public GLCapabilities getCapabilities() {
        return glCapabilities;
    }

    public void fullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public boolean fullScreen() {
        return fullScreen;
    }

    public void setIcon(ByteBuffer iconData) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);
            
            ByteBuffer pixels = STBImage.stbi_load_from_memory(iconData, w, h, comp, 4);
            
            GLFWImage.Buffer icons = GLFWImage.create(1);
            GLFWImage image = GLFWImage.create();
            image.width(w.get()).height(h.get()).pixels(pixels);
            
            icons.put(image);
            icons.flip();
            
            GLFW.glfwSetWindowIcon(winID, icons);
        }        
    }
    
}
