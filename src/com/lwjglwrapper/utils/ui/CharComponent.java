/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.ui;

import static com.lwjglwrapper.utils.ui.Button.NORMAL;

/**
 *
 * @author Welcome
 */
public abstract class CharComponent extends Component{
    
    public CharComponent(Stage stage, boolean autoAdd, int maxModes) {
        super(stage, autoAdd, maxModes);
    }
    
    public abstract void charEntered(int codePoint);
    
    @Override
    public void resetState() {
        super.resetState();
        setMode(NORMAL);
    }
}
