/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.ui;

import com.lwjglwrapper.utils.math.MathUtils;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Welcome
 */
public class Button extends Component{

    public static final int NORMAL = 0, HOVER = 1, CLICKED = 2;
    private boolean inBounds;
    private OnClickListener onClickListener;
    
    public Button(Stage stage, boolean add) {
        super(stage, add, 3);
        setOnClickListener((s, b, m) -> {});
    }

    @Override
    public void tick() {
        super.tick();
        if(!isVisible())    return;
        if (stage.window.getMouse().mousePressed(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
            inBounds = MathUtils.contains(getCurrentShape().getShape().boundBox(),
                    stage.window.getMouse().getCursorPosition());
        }
        if(stage.hovering == this) {
            setMode(HOVER);
            if(stage.window.getMouse().mouseDown(GLFW.GLFW_MOUSE_BUTTON_LEFT) && inBounds) {
                setMode(CLICKED);
                if(stage.window.getMouse().mousePressed(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
                    onClickListener.action(stage, this, currentMode);
                }
            }
        } else setMode(NORMAL);
        
        if(inBounds && stage.window.getMouse().mouseDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
            setMode(CLICKED);
        }
    }
    
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void resetState() {
        super.resetState();
        setMode(NORMAL);
        inBounds = false;
    }
    
    public static interface OnClickListener extends ComponentListener<Button> {

        //This method is never used
        @Override
        public default boolean test(Stage stage, Button comp, int mode) {
            return true;    
        }
        
    }
}
