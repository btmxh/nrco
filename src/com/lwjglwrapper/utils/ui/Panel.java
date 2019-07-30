/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.ui;

import com.lwjglwrapper.nanovg.NVGGraphics;
import static com.lwjglwrapper.utils.ui.Button.NORMAL;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector2f;

/**
 *
 * @author Welcome
 */
public class Panel extends Component{
    
    public static final int NORMAL = 0;
    
    private List<Component> children = new ArrayList<>();
    
    public Panel(Stage stage, boolean autoAdd) {
        super(stage, autoAdd, 1);
    }

    @Override
    public void tick() {
        super.tick();
        for (Component comp : children) {
            comp.tick();
        }
    }

    @Override
    public void render(NVGGraphics g) {
        super.render(g);
        for (Component comp : children) {
            comp.render(g);
        }
    }
    
    
    
    public void addChild(Component comp) {
        comp.setOffset(offset);
        children.add(comp);
    }
    
    public void removeChild(Component comp) {
        comp.setOffset(new Vector2f());
        children.remove(comp);
    }

    public Vector2f getOffset() {
        return offset;
    }
    
    @Override
    public void resetState() {
        super.resetState();
        setMode(NORMAL);
    }
}
