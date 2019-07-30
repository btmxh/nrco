/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.states.State;
import nrco.MainGame;
import nrco.utils.Screenshot;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class SplashState extends IState{

    private static final long TIME = 60000;
    
    private long showTime;
    private String splash;

    public SplashState(MainGame game) {
        super(game);
    }

    @Override
    public void show() {
        showTime = System.currentTimeMillis();
        splash = game.assets.randomSplash();
    }

    @Override
    public void update(float delta) {
        if(System.currentTimeMillis() - showTime > TIME || LWJGL.mouse.allMouseReleased(GLFW.GLFW_MOUSE_BUTTON_LEFT) || LWJGL.keyboard.anyKeyReleased(GLFW.GLFW_KEY_ENTER)) {
            game.setState(game.winState);
        }
    }

    @Override
    public void render(float xoff, float yoff) {
        Screenshot.beginShader();
        Screenshot.setOffset(xoff, yoff);
        Screenshot.setTint(new Vector3f(0.5f, 0.5f, 0.5f));
        game.getGameScreenshot().render();
        Screenshot.endShader();
        
        LWJGL.graphics.begin();
        game.assets.textFont.use();
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_CENTER | NanoVG.NVG_ALIGN_MIDDLE);
        LWJGL.graphics.textPaint(IColor.WHITE);
        LWJGL.graphics.textSize(60);
        int lines = LWJGL.graphics.textBoxLines(splash, LWJGL.window.getWidth() / 2, 5);
        LWJGL.graphics.textBox(splash, LWJGL.window.getWidth() / 4, LWJGL.window.getHeight() / 2 - lines * 60 / 2, LWJGL.window.getWidth() / 2);
        LWJGL.graphics.end();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
    
}
