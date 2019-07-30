/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.geom.PaintedShape;
import com.lwjglwrapper.utils.geom.shapes.RoundRect;
import com.lwjglwrapper.utils.ui.Stage;
import nrco.MainGame;
import nrco.ui.IButton;
import nrco.ui.RectUtils;
import nrco.utils.Screenshot;
import org.joml.Vector3f;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Welcome
 */
public class CompleteState extends IState{
    
    public Screenshot levelScreenshot;
    private Stage stage;

    public CompleteState(MainGame game) {
        super(game);
        stage = new Stage(LWJGL.window, LWJGL.graphics);
        IButton close = new IButton(stage, RectUtils.center(LWJGL.window.getWidth() / 2, LWJGL.window.getHeight() / 2 + 150, 300, 100), game.format("%close%"), IColor.RED);
        close.setOnClickListener((s, b, m) -> game.setState(game.levelState));
    }

    @Override
    public void show() {
    }

    @Override
    public void update(float delta) {
        stage.tick();
    }

    @Override
    public void render(float xoff, float yoff) {
        super.render(xoff, yoff);
        
        if(levelScreenshot == null) {
            levelScreenshot = new Screenshot();
            levelScreenshot.begin();
            GL11.glClearColor(1, 1, 1, 1);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            game.levelState.render();
            levelScreenshot.end();
        }
        
        Screenshot.beginShader();
        Screenshot.setTint(new Vector3f(0.5f));
        levelScreenshot.render();
        Screenshot.endShader();
        
        int w = LWJGL.window.getWidth(), h = LWJGL.window.getHeight();
        LWJGL.graphics.begin();
        
        new PaintedShape(new RoundRect(RectUtils.center(w / 2, h / 2, w / 2, h / 2), 16), 
            IColor.GOLDENROD.darker(), null).render(LWJGL.graphics);
        new PaintedShape(new RoundRect(RectUtils.center(w / 2, h / 2, w / 2 - 30, h / 2 - 30), 16), 
            IColor.GOLDENROD, null).render(LWJGL.graphics);
            
        game.assets.textFont.use();
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_CENTER | NanoVG.NVG_ALIGN_MIDDLE);
        LWJGL.graphics.textPaint(IColor.WHITE);
        LWJGL.graphics.textSize(64);
        LWJGL.graphics.text(game.format("%congrats_header%"), w / 2, h / 2 - 150);
        
        LWJGL.graphics.textSize(36);
        LWJGL.graphics.textBox(game.format("%congrats_content%"), w / 4 + 40, h / 4 + 150, w / 2 - 70);
        
        stage.render();
        
        LWJGL.graphics.end();
    }
    
    

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
    
}
