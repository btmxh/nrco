/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.collections;

/**
 *
 * @author Welcome
 */
public class IntArrayStack {
    private int[] array;
    private int pointer;

    public IntArrayStack(int len) {
        array = new int[len];
        pointer = 0;
    }
    
    public int size() {
        return array.length;
    }
    
    public void put(int... ints) {
        for (int i : ints) {
            array[pointer++] = i;
        }
    }
    
    public int[] getArray() {
        return array;
    }
    
}
