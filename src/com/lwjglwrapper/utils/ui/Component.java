/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.ui;

import com.lwjglwrapper.display.Window;
import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.utils.geom.PaintedShape;
import com.lwjglwrapper.utils.geom.Shape;
import com.lwjglwrapper.utils.geom.ShapeStates;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector2f;

/**
 *
 * @author Welcome
 */
public abstract class Component {
    protected Stage stage;
    protected ShapeStates shapes;
    protected int currentMode = 0;
    private boolean visible = true;
    protected Vector2f offset = new Vector2f();
    protected List<ComponentListener> listeners = new ArrayList<>();
    
    public Component(Stage stage, boolean autoAdd, int maxModes) {
        this.stage = stage;
        shapes = new ShapeStates(maxModes);
        if(autoAdd) stage.components.add(this);
    }
    
    public ShapeStates getShapeStates() {
        return shapes;
    }
    
    protected PaintedShape getCurrentShape() {
        return shapes.get(currentMode);
    }
    
    public void resetState() {
        
    }
    
    public void tick() {
        if(!visible)    return;
        for (ComponentListener listener : listeners) {
            if(listener.test(stage, this, currentMode)) {
                listener.action(stage, this, currentMode);
            }
        }
    }
    
    public void render(NVGGraphics g) {
        if(!visible)    return;
        if(shapes == null)  return;
        PaintedShape shape = shapes.get(currentMode);
        if(shape == null)   return;
        g.push();
        g.translate(offset);
        shape.render(g);
        g.pop();
    }
    
    public void renderAndTick(NVGGraphics g) {
        tick();
        render(g);
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public void setMode(int mode) {
        this.currentMode = mode;
    }

    void setOffset(Vector2f offset) {
        this.offset = new Vector2f(offset);
    }
}
