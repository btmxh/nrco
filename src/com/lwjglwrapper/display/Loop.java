/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.display;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.utils.GameUtils;
import com.lwjglwrapper.utils.Logger;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Welcome
 */
public class Loop implements Runnable {

    protected Window window;
    
    protected int frameNo;
    protected double delta;
    
    private boolean log;

    public Loop() {
    }

    @Override
    public void run() {
        try {
            init();
            window.setToMainWindow();
            LWJGL.currentLoop = this;
            
            int fpsCap = window.getFPSCap();
            frameNo = 0;
            
            double lastLoopTime = GLFW.glfwGetTime();
            double optimalTime = 1.0 / fpsCap;
            double lastFPSTime = 0;
            
            while(!window.closeRequested()) {
                double now = GLFW.glfwGetTime();
                double updateLength = now - lastLoopTime;
                
                lastLoopTime = now;
                
                delta = updateLength;
                lastFPSTime += updateLength;
                frameNo++;
                
                if(lastFPSTime >= 1) {
                    lastFPSTime = 0;
                    Logger.logln("FPS: " + frameNo + "\tMemory: " + GameUtils.formatMem(GameUtils.getJavaHeap()) + "/" + GameUtils.formatMem(GameUtils.getMaxMemory()));
                    
                    frameNo = 0;
                }
                
                update((float) delta);
                render();
                
                try{
                    long time = (long) ((lastLoopTime - GLFW.glfwGetTime() + optimalTime) * 1000);
                    if(time > 0) {
                        Thread.sleep(time);
                        
                    }
                } catch (InterruptedException ex){}
            }
            
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Thread runNewThread() {
        Thread t = new Thread(this);
        t.start();
        return t;
    }

    protected void render() throws Exception{
        window.updateDisplay();
    }

    protected void update(float delta) throws Exception{
        window.updateInput();
    }
    
    protected void init() throws Exception{
        createWindow();
    }
    
    protected void createWindow() {
        window = new Window();
        window.create();
        
        window.setToCenterScreen();
        window.setToMainWindow();
        
        window.setVisible(true);
    }
    
    public void exit() {
        window.close();
    }
    
    protected void dispose() {
        window.destroy();
    }

    public double getDeltaTime() {
        return delta;
    }

    /**
     * @param log the log to set
     */
    public void logGameInfo(boolean log) {
        this.log = log;
    }

    
}
