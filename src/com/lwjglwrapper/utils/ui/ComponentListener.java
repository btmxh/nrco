/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.ui;

import com.lwjglwrapper.display.Window;

/**
 *
 * @author Welcome
 */
public interface ComponentListener<C extends Component> {
    public boolean test(Stage stage, C comp, int mode);
    public void action(Stage stage, C comp, int mode);
}
