/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.states;

import java.util.ArrayList;

/**
 *
 * @author Welcome
 */
public class StateManager extends ArrayList<State>{
    private int currentStateIndex;

    public StateManager() {
    }

    public int getCurrentStateIndex() {
        return currentStateIndex;
    }
    
    public State getCurrentState() {
        return this.get(currentStateIndex);
    }

    public void setCurrentStage(int index) {
        if(index < 0 | index >= size()) throw new ArrayIndexOutOfBoundsException(index);
        else {
            State last = getCurrentState();
            currentStateIndex = index;
            State newStage = getCurrentState();
            
            last.hide();
            newStage.show();
        }
    }
    
    public void setCurrentStage(State state) {
        int index = indexOf(state);
        if(index < 0)   throw new IllegalArgumentException("State " + state + " not found");
        else {
            setCurrentStage(index);
        }
        
    }

    public void dispose() {
        forEach(State::dispose);
    }
}
