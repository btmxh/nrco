/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.ui;

import com.lwjglwrapper.LWJGL;

/**
 *
 * @author Welcome
 */
public class ButtonGroup {

    public static float[] centerYs(int buttonCount, float buttonHeight,
            float buttonSpacing) {
        float offset = (LWJGL.window.getHeight() - buttonHeight * buttonCount
                - buttonSpacing * (buttonCount - 1)) / 2;
        float[] cys = new float[buttonCount];
        for (int i = 0; i < buttonCount; i++) {
            cys[i] = offset + (buttonHeight + buttonSpacing) * i + buttonHeight / 2;
        }
        return cys;
    }

}
