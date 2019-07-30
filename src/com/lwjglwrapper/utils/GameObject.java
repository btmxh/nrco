/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils;

import com.lwjglwrapper.utils.states.State;

/**
 *
 * @author Welcome
 */
public interface GameObject{
    public void update(float delta);
    public void render();
}
