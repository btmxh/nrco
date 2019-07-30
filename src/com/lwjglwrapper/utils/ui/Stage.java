/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.ui;

import com.lwjglwrapper.display.Window;
import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.utils.geom.PaintedShape;
import com.lwjglwrapper.utils.geom.shapes.Rect;
import java.util.ArrayList;
import java.util.List;
import org.joml.Circlef;
import org.joml.Rectanglef;
import org.joml.Vector2f;

/**
 *
 * @author Welcome
 */
public class Stage {
    private NVGGraphics g;
    
    public List<Component> components;
    public Window window;
    
    public Component hovering;
    public float alpha;

    public Stage(Window window, NVGGraphics g) {
        components = new ArrayList<>();
        this.window = window;
        this.g = g;
    }

    public void tick() {
        hovering = null;
        
        for (int i = components.size() - 1; i >= 0; i--) {
            Component comp = components.get(i);
            if(!comp.isVisible())   continue;
            if(hovering == null) {
            
                PaintedShape currentShape = comp.getShapeStates().get(comp.currentMode);
                Rectanglef boundBox = currentShape.getShape().boundBox();
                boolean contains = Rect.awtRect(boundBox).contains(window.getMouse().getCursorX(), window.getMouse().getCursorY());
                if(contains)    hovering = comp;
        
            }
            
            comp.tick();
        }
    }

    public void render() {
        for (Component comp : components) {
            comp.render(g);
        }
    }

    public void renderAndTick() {
        for (Component comp : components) {
            comp.renderAndTick(g);
        }
    }

    public void charEntered(int codePoint) {
        for (Component comp : components) {
            if(comp instanceof CharComponent) {
                ((CharComponent) comp).charEntered(codePoint);
            }
        }
    }

    public void resetState() {
        components.forEach(Component::resetState);
    }
}
