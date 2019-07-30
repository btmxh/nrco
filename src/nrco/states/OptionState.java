/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.math.MathUtils;
import nrco.MainGame;
import nrco.states.handlers.AudioUIHandler;
import nrco.ui.ButtonGroup;
import nrco.ui.IButton;
import nrco.ui.AlphaStage;
import nrco.ui.RectUtils;
import nrco.utils.RenderableTexture;
import nrco.utils.Screenshot;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Welcome
 */
public class OptionState extends IState{

    private static final long TRANSITION_TIME = 200;
    
    private AlphaStage stage;
    private AudioUIHandler audioButtons;
    private final IButton resume;
    private final IButton retry;
    private final IButton exit;
    private long showTime;
    

    public OptionState(MainGame game) {
        super(game);
        stage = new AlphaStage(LWJGL.window, LWJGL.graphics);
        
        IButton.font = game.assets.textFont;
        final float spacing = 50, height = 100;
        float[] cys = ButtonGroup.centerYs(3, height, spacing);
        resume = new IButton(stage, RectUtils.center(LWJGL.window.getWidth() / 2, cys[0], 400, height), game.format("%resume%"), 
                                IColor.GREEN.scale(1.7f));
        retry = new IButton(stage, RectUtils.center(LWJGL.window.getWidth() / 2, cys[1], 400, height), game.format("%retry%"),
                                IColor.GOLDENROD.scale(1.5f));
        exit = new IButton(stage, RectUtils.center(LWJGL.window.getWidth() / 2, cys[2], 400, height), game.format("%return%"),
                                IColor.DARKRED.scale(1.6f));
        resume.setOnClickListener((s, b, m) -> game.setState(game.gameState));
        retry.setOnClickListener((s, b, m) -> game.setState(game.gameState.reset()));
        exit.setOnClickListener((s, b, m) -> game.setState(game.levelState));
        
        audioButtons = new AudioUIHandler(game);
    }

    @Override
    public void show() {
        stage.resetState();
        stage.setAlpha(0);
        audioButtons.show();
        showTime = System.currentTimeMillis();
    }

    @Override
    public void update(float delta) {
        stage.tick();
        float frac = (float) (System.currentTimeMillis() - showTime) / TRANSITION_TIME;
        stage.setAlpha(Math.min(frac, 1.0f));
        audioButtons.update(delta);
        if(LWJGL.keyboard.anyKeyReleased(GLFW.GLFW_KEY_ESCAPE)) {
            game.setState(game.gameState);
        }
    }

    @Override
    public void render(float xoff, float yoff) {
        super.render(xoff, yoff);
        
        Screenshot.beginShader();
        Screenshot.setOffset(xoff, yoff);
        float frac = (float) (System.currentTimeMillis() - showTime) / TRANSITION_TIME;
        float tint = MathUtils.clamp(0.5f, 1 - frac / 2, 1f);
        Screenshot.setTint(new Vector3f(tint));
        game.getGameScreenshot().render();
        Screenshot.endShader();
        LWJGL.graphics.begin();
        LWJGL.graphics.translate(xoff, yoff);
        stage.render();
        LWJGL.graphics.end();
        RenderableTexture.begin();
        RenderableTexture.setTint(new Vector3f(1));
        RenderableTexture.setOffset(xoff, yoff);
        RenderableTexture.setAlpha(Math.min(frac, 1f));
        audioButtons.render();
        RenderableTexture.end();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        
    }

    public void resetLanguage() {
        resume.setText(game.format("%resume%"));
        retry.setText(game.format("%retry%"));
        exit.setText(game.format("%return%"));
    }
    
}
