/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.states;

/**
 *
 * @author Welcome
 */
public abstract class State<G extends GameStateLoop>{
    
    protected G game;

    public State(G game) {
        this.game = game;
        game.addState(this);
    }
    
    public abstract void show();
    
    //using float because OpenGL like it
    public abstract void update(float delta);
    
    public abstract void render();
    
    public abstract void hide();
    
    public abstract void dispose();

    public G getGame() {
        return game;
    }
    
    
}
