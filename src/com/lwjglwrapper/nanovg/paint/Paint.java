/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg.paint;

/**
 *
 * @author Welcome
 */
public interface Paint {
    public void fill(long nvgID);
    public void stroke(long nvgID);
    public void text(long nvgID);
}
