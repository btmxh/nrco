/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states;

import aurelienribon.tweenengine.Tween;
import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.utils.math.MathUtils;
import com.lwjglwrapper.utils.states.State;
import com.lwjglwrapper.utils.ui.Stage;
import nrco.MainGame;
import nrco.utils.RenderableTexture;
import nrco.utils.Screenshot;
import org.joml.Rectanglef;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Welcome
 */
public class TransitionState extends State<MainGame>{
    private IState from, to;
    private float time, maxTime;
    private Transition transition;
    private RenderableTexture background;
    private TintTransition tintTransition = TintTransition.DARKER;

    public TransitionState(MainGame game) {
        super(game);
    }
    
    public void start(IState from, IState to, float maxTime) {
        this.from = from;
        this.to = to;
        this.maxTime = maxTime;
    }

    @Override
    public void show() {
        from.show();
        to.show();
    }

    @Override
    public void update(float delta) {
        time += delta;
        if(time > maxTime) {
            from.setOffset(0, 0);
            to.setOffset(0, 0);
            from.setClear(true);
            to.setClear(true);
            game.setState(to);
            time = 0;
        }
    }

    @Override
    public void render() {
        GL11.glClearColor(1, 1, 1, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        
        RenderableTexture.begin();
        RenderableTexture.setAlpha(1);
        RenderableTexture.setOffset(0, 0);
        if(tintTransition == TintTransition.NO_CHANGE) {
            RenderableTexture.setTint(new Vector3f(1));
        } else {
            float frac = time / maxTime;
            if(tintTransition == TintTransition.DARKER)   frac = 1 - frac;
            frac = MathUtils.map(frac, 0, 1, 0.3f, 1);
            RenderableTexture.setTint(new Vector3f(frac));
        }
        background.render(new Rectanglef(0, 0, LWJGL.window.getWidth(), LWJGL.window.getHeight()));
        RenderableTexture.end();
        
        Vector4f offsets = transition.calcOffsets(time, maxTime);
        from.setOffset(offsets.x, offsets.y);
        to.setOffset(offsets.z, offsets.w);
        from.setClear(false);
        to.setClear(false);
        
        from.render();
        to.render();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }
    
    public void setBackground(RenderableTexture background) {
        this.background = background;
    }
    
    public void setTintTransition(TintTransition tt) {
        this.tintTransition = tt;
    }
    
    public static interface Transition {
        public static final Transition RIGHT = (time, maxTime) -> {
            float frac = time / maxTime;
            float deltaX = LWJGL.window.getWidth() * frac;
            return new Vector4f(-deltaX, 0, LWJGL.window.getWidth() - deltaX, 0);
        };
        public static final Transition LEFT = (time, maxTime) -> {
            float frac = time / maxTime;
            float deltaX = LWJGL.window.getWidth() * frac;
            return new Vector4f(deltaX, 0, - LWJGL.window.getWidth() + deltaX, 0);
        };
        public static final Transition UP = (time, maxTime) -> {
            float frac = time / maxTime;
            float deltaY = LWJGL.window.getHeight() * frac;
            return new Vector4f(0, -deltaY, 0, LWJGL.window.getHeight() - deltaY);
        };
        public static final Transition DOWN = (time, maxTime) -> {
            float frac = time / maxTime;
            float deltaY = LWJGL.window.getHeight()* frac;
            return new Vector4f(0, deltaY, 0, - LWJGL.window.getHeight() + deltaY);
        };
        public Vector4f calcOffsets(float time, float maxTime);
    }
    
    public enum TintTransition {
        DARKER, BRIGHTER, NO_CHANGE;
    }
}
