/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg.paint;

import com.lwjglwrapper.nanovg.NVGGraphics;

/**
 *
 * @author Welcome
 */
public interface AdditionalPaint {
    public static final AdditionalPaint DO_NOTHING = (nvgID) -> {};
    public void paint(NVGGraphics g);
}
