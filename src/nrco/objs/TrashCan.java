/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.objs;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.utils.GameObject;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.geom.PaintedShape;
import com.lwjglwrapper.utils.geom.shapes.Rect;
import java.util.Iterator;
import java.util.List;
import nrco.states.GameState;
import nrco.states.handlers.AudioHandler;
import nrco.states.handlers.GarbageHandler;
import nrco.utils.RenderableTexture;
import org.joml.Vector2f;

/**
 *
 * @author Welcome
 */
public class TrashCan implements GameObject{
    private GameState gameState;
    private PaintedShape<Rect> rect;
    private RenderableTexture texture;
    private int type;

    public TrashCan(GameState gameState, Rect rect, int type) {
        this.gameState = gameState;
        this.rect = new PaintedShape<>(rect, IColor.TRANSPARENT, IColor.BLACK);
        this.texture = new RenderableTexture(gameState.getGame().assets.trashCan(type));
        this.type = type;
    }

    @Override
    public void update(float delta) {}
    
    public void update(float delta, GarbageHandler garbageHandler) {
        for (Iterator<Garbage> it = garbageHandler.garbages.iterator(); it.hasNext();) {
            Garbage g = it.next();
            if(garbageHandler.holdings.contains(g)) continue;
            if(contains(g.getCenter())) {
                gameState.addPoint(g.type == type? 300:100, g.getCenter());
                gameState.getGame().audioHandler.play(g.type == type? AudioHandler.DUMP:AudioHandler.WRONG, false);
                it.remove();
            }
        }
    }

    @Override
    public void render() {
        rect.render(LWJGL.graphics);
    }
    
    public void renderCans() {
        texture.render(rect.getShape().getJOMLRect());
    }

    public boolean contains(Vector2f point) {
        Vector2f flipped = new Vector2f(point.x, LWJGL.window.getHeight() - point.y);
        return rect.getShape().contains(flipped);
    }

    public int getType() {
        return type;
    }
    
    
    
    
}
