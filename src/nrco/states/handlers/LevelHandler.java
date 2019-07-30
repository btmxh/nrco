/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states.handlers;

import nrco.Level;
import nrco.MainGame;
import nrco.states.LevelState;

/**
 *
 * @author Welcome
 */
public class LevelHandler {
    
    private MainGame game;
    
    public int[] levels;

    public LevelHandler(MainGame game) {
        this.game = game;
        levels = new int[Level.values().length];
        levels[0] = 0;
        for (int i = 1; i < levels.length; i++) {
            levels[i] = -1;
        }
    }
    
    public void finishLevel(int level, int stars) {
        levels[level - 1] = Math.max(levels[level - 1], stars);
        if(level != levels.length? false : levels[level] < 0) {
            levels[level] = 0;
        }
        game.levelState.updateLevels();
    }
    
}
