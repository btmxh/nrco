/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states.handlers;

import com.lwjglwrapper.LWJGL;
import java.util.ArrayList;
import java.util.List;
import nrco.Level;
import nrco.MainGame;
import nrco.objs.Garbage;
import nrco.objs.TrashCan;
import nrco.utils.KeyMouseBindings;
import org.joml.Vector2f;

/**
 *
 * @author Welcome
 */
public class GarbageHandler {
    
    private final MainGame game;
    private Level level;
    
    public int garbageCount = 0;
    
    public List<Garbage> garbages;
    public List<Garbage> holdings;
    public List<Garbage> blowUp;
    
    public long lastSpawn;
    

    public GarbageHandler(MainGame game, Level level) {
        this.level = level;
        this.game = game;
        garbages = new ArrayList<>();
        holdings = new ArrayList<>();
        blowUp = new ArrayList<>();
        
    }
    
    public void update(float delta) {
        if((Math.random() < delta * level.spawnRate ||
                System.currentTimeMillis() - lastSpawn > level.maxNoSpawnTime * 1000) && 
                level.garbageRequirements >= garbageCount + 1 &&
                level.garbageLimit > garbages.size() + 1) {
            garbages.add(randomGarbage());
            lastSpawn = System.currentTimeMillis();
            garbageCount++;
        }
        garbages.forEach(g -> g.update(delta));
        for (int i = 0; i < holdings.size(); i++) {
            holdings.get(i).onHold(delta, i);
        }
        if(KeyMouseBindings.drop()) {
            holdings.clear();
        }
        
        garbages.removeAll(blowUp);
        holdings.removeAll(blowUp);
        blowUp.clear();
    }

    public void renderTextures() {
        garbages.forEach(Garbage::renderTexture);
    }

    public void renderShapes() {
        garbages.forEach(Garbage::render);
    }
    
    public void hold(Garbage g){
        if(holdings.contains(g))    return;
        if(holdings.isEmpty()) {
            if(KeyMouseBindings.holdingNone())  holdings.add(g);
        } else {
            if(!holdings.get(0).sameType(g)) return;
            if(KeyMouseBindings.holding())   holdings.add(g);
        }
        
    }
    public void unhold(Garbage g){holdings.remove(g);}
    
    public void blowUp(Garbage g) {
        blowUp.add(g);
        game.audioHandler.play(AudioHandler.BOOM, false);
    }
    
    private Garbage randomGarbage() {
        final float offset = 100f;
        float x = (float) (Math.random() * (LWJGL.window.getWidth() - offset)) + offset,
              y = (float) (Math.random() * (LWJGL.window.getHeight() - offset)) + offset;
        
        Vector2f position = new Vector2f(x, y);
        for (TrashCan trashCan : game.gameState.trashCans) {
            if(trashCan.contains(position)) return randomGarbage();
        }
        
        return new Garbage(game.gameState, position, level.generateMaxRadius(), level.generateMaxAge(), level.generateType());
    }
}
