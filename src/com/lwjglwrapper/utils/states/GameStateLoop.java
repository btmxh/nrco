/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.states;

import com.lwjglwrapper.display.Loop;

/**
 *
 * @author Welcome
 */
public class GameStateLoop extends Loop {

    private StateManager stateManager;

    public GameStateLoop() {
        stateManager = new StateManager();
    }

    public GameStateLoop(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void setState(State s) {
        stateManager.setCurrentStage(s);
    }

    public void setStateByIndex(int index) {
        stateManager.setCurrentStage(index);
    }

    @Override
    protected void init() throws Exception {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void update(float delta) throws Exception {
        super.update(delta);
        if (stateManager.isEmpty()) {
            return;
        }
        stateManager.getCurrentState().update(delta);
    }

    @Override
    protected void render() throws Exception {
        super.render();
        if (stateManager.isEmpty()) {
            return;
        }
        stateManager.getCurrentState().render();
    }

    @Override
    protected void dispose() {
        super.dispose();
        stateManager.dispose();
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    protected void addState(State s) {
        stateManager.add(s);
    }

}
