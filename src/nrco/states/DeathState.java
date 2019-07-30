/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.ui.Stage;
import nrco.MainGame;
import nrco.ui.ButtonGroup;
import nrco.ui.IButton;
import nrco.ui.RectUtils;
import nrco.utils.Screenshot;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class DeathState extends IState{
    
    private Stage stage;

    public DeathState(MainGame game) {
        super(game);
        stage = new Stage(LWJGL.window, LWJGL.graphics);
        
        final float spacing = 50, height = 100;
        float[] cys = ButtonGroup.centerYs(4, height, spacing);
        IButton retry = new IButton(stage, RectUtils.center(LWJGL.window.getWidth() / 2, cys[2], 400, height),
                                game.format("%retry%"),
                                IColor.GOLDENROD.scale(1.5f));
        IButton exit = new IButton(stage, RectUtils.center(LWJGL.window.getWidth() / 2, cys[3], 400, height),
                                game.format("%exit%"),
                                IColor.DARKRED.scale(1.6f));
        retry.setOnClickListener((s, b, m) -> game.setState(game.gameState.reset()));
        exit.setOnClickListener((s, b, m) -> game.setState(game.levelState));
    }

    @Override
    public void show() {
        stage.resetState();
    }

    @Override
    public void update(float delta) {
        stage.tick();
        if(LWJGL.keyboard.anyKeyReleased(GLFW.GLFW_KEY_ESCAPE)) {
            game.setState(game.gameState);
        }
    }

    @Override
    public void render(float xoff, float yoff) {
        super.render(xoff, yoff);
        
        Screenshot.beginShader();
        Screenshot.setOffset(xoff, yoff);
        Screenshot.setTint(new Vector3f(0.5f, 0.5f, 0.5f));
        game.getGameScreenshot().render();
        Screenshot.endShader();
        LWJGL.graphics.begin();
        LWJGL.graphics.translate(xoff, yoff);
        stage.render();
        game.assets.textFont.use();
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_CENTER | NanoVG.NVG_ALIGN_MIDDLE);
        LWJGL.graphics.textSize(100);
        LWJGL.graphics.textPaint(IColor.WHITE);
        LWJGL.graphics.text(game.format("%failed%"), LWJGL.window.getWidth() / 2, LWJGL.window.getHeight() * 0.33f);
        LWJGL.graphics.end();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        
    }
    
}
