/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.geom.shapes.Rect;
import java.util.ArrayList;
import java.util.List;
import nrco.Level;
import nrco.MainGame;
import nrco.objs.Garbage;
import nrco.states.handlers.GarbageHandler;
import nrco.objs.Points;
import nrco.states.handlers.ScoringHandler;
import nrco.objs.TrashCan;
import nrco.states.handlers.AudioHandler;
import nrco.states.handlers.InGameUIHandler;
import nrco.utils.KeyMouseBindings;
import nrco.utils.RenderableTexture;
import nrco.utils.JARLoader;
import nrco.utils.Screenshot;
import org.joml.Rectanglef;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Welcome
 */
public class GameState extends IState{
    
    public Level level;
    
    public List<TrashCan> trashCans;
    private List<Points> points;    
    
    private ScoringHandler scoringHandler;
    public GarbageHandler garbageHandler;
    private InGameUIHandler gameUIHandler;
    
    private Screenshot ss;
    
    public GameState(MainGame game, int level) {
        super(game);
        setLevel(level);
        reset();
    }

    @Override
    public void show() {
        
        ss = new Screenshot();
    }

    private long lastSpawn = 0;
    @Override
    public void update(float delta) {
        garbageHandler.update(delta);
        
        trashCans.forEach(c -> c.update(delta, garbageHandler));
        points.forEach(p -> p.update(delta));
        
        scoringHandler.calculateScores(delta);
        
        if(scoringHandler.hp <= level.deathHP) {
            scoringHandler.end();
            render();
            game.setState(game.deathState);
        }
        
        if(scoringHandler.garbages() >= level.garbageRequirements) {
            scoringHandler.end();
            render();
            game.winState.setData(scoringHandler, level, game.levelHandler.levels[level.ordinal()]);
            game.levelHandler.finishLevel(level.ordinal() + 1, scoringHandler.stars());
            game.setState(game.splashState);
        }
        
        if(LWJGL.keyboard.anyKeyReleased(GLFW.GLFW_KEY_ESCAPE)) {
            game.setState(game.optionState);
        }
    }

    @Override
    public void render(float xoff, float yoff) {
        super.render(xoff, yoff);
        
        ss.begin();
        
        GL11.glClearColor(1, 1, 1, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        
        //RenderableTexture
        RenderableTexture.begin();
        RenderableTexture.setAlpha(1);
        RenderableTexture.setTint(new Vector3f(1));
        RenderableTexture.setOffset(xoff, yoff);
        
        game.assets.getGameBackground(level).render(new Rectanglef(0, 0, LWJGL.window.getWidth(), LWJGL.window.getHeight()));
        trashCans.forEach(TrashCan::renderCans);
        
        RenderableTexture.end();
        
        //NanoVG
        LWJGL.graphics.begin();
        LWJGL.graphics.translate(xoff, yoff);
        
        trashCans.forEach(c -> c.render());
        garbageHandler.renderShapes();
        
        LWJGL.graphics.end();
        
        //RenderableTexture
        RenderableTexture.begin();
        RenderableTexture.setAlpha(1);
        RenderableTexture.setTint(new Vector3f(1));
        garbageHandler.renderTextures();
        RenderableTexture.end();
        
        //NanoVG
        LWJGL.graphics.begin();
        LWJGL.graphics.translate(xoff, yoff);
        
        points.forEach(p -> p.render());
        gameUIHandler.render();
        
        LWJGL.graphics.end();
        
        ss.end();
        
        //Screenshot
        Screenshot.beginShader();
        Screenshot.setOffset(xoff, yoff);
        Screenshot.setTint(new Vector3f(1.0f));
        
        ss.render();
        Screenshot.endShader();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    
    
    

    private List<TrashCan> trashCans() {
        ArrayList<TrashCan> cans = new ArrayList<>();
        final float w = LWJGL.window.getWidth(), h = LWJGL.window.getHeight();
        cans.add(new TrashCan(this, new Rect(0, 0, 200, 200), 1 + 3 % level.types));
        cans.add(new TrashCan(this, new Rect(w - 200, 0, 200, 200), 1 + 2 % level.types));
        cans.add(new TrashCan(this, new Rect(w - 200, h - 200, 200, 200), 1 + 1 % level.types));
        cans.add(new TrashCan(this, new Rect(0, h - 200, 200, 200), 1));
        return cans;
    }

    public void addPoint(int pt, Vector2f position) {
        String rep;
        IColor color;
        if(pt != 0) rep = String.valueOf(pt);
        else rep = "X";
        switch (pt) {
            case 0  : color = IColor.RED;   break;
            case 50 : color = IColor.GOLD;   break;
            case 100: color = IColor.LIME;   break;
            case 200: color = IColor.BLUE;   break;
            case 300: color = IColor.AQUA;   break;
            default: color = IColor.BLACK;   break;
        }
        Points pts = new Points(rep, game.assets.gameFont, new IColor(color), 108, position, 2);
        points.add(pts);
        scoringHandler.addScore(pt);
    }


    /**
     * @return the ss
     */
    public Screenshot getScreenshot() {
        return ss;
    }

    public final void setLevel(int level) {
        this.level = Level.values()[level - 1];
    }
    
    public final GameState reset() {
        trashCans = trashCans();
        points = new ArrayList<>();
        
        scoringHandler = new ScoringHandler();
        garbageHandler = new GarbageHandler(game, level);
        gameUIHandler = new InGameUIHandler(game, scoringHandler);
        
        return this;
    }

    @Override
    public MainGame getGame() {
        return super.getGame(); //To change body of generated methods, choose Tools | Templates.
    }

    public int getLevel() {
        return level.ordinal() + 1;
    }

    enum PendingState {
        WIN, LOST;
    }
    
}
