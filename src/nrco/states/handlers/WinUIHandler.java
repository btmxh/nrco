/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states.handlers;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.geom.PaintedShape;
import com.lwjglwrapper.utils.geom.shapes.RoundRect;
import com.lwjglwrapper.utils.ui.Button;
import com.lwjglwrapper.utils.ui.Stage;
import nrco.MainGame;
import nrco.ui.IButton;
import nrco.ui.RectUtils;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class WinUIHandler {
    private Stage stage;
    private Button close;
    private boolean show = false;

    public WinUIHandler(MainGame game, Stage stage) {
        this.stage = stage;
        int w = LWJGL.window.getWidth(), h = LWJGL.window.getHeight();
        close = new IButton(stage, RectUtils.center(w / 2, h / 2 + 150, 300, 100), game.format("%close%"), IColor.RED){
            @Override
            public void render(NVGGraphics g) {}

            @Override
            public void renderAndTick(NVGGraphics g) {
                super.render(g);
            }
        };
        close.setVisible(true);
        close.getShapeStates().setAllBeforePaints((g) -> {
            new PaintedShape(new RoundRect(RectUtils.center(w / 2, h / 2, w / 2, h / 2), 16), 
                IColor.GOLDENROD.darker(), null).render(g);
            new PaintedShape(new RoundRect(RectUtils.center(w / 2, h / 2, w / 2 - 30, h / 2 - 30), 16), 
                    IColor.GOLDENROD, null).render(g);
            
            game.assets.textFont.use();
            g.textAlign(NanoVG.NVG_ALIGN_CENTER | NanoVG.NVG_ALIGN_MIDDLE);
            g.textPaint(IColor.WHITE);
            g.textSize(36);
            g.text(game.format("%congrats%"), w / 2, h / 2 - 100);
        
        }).construct(false);
    }
    
    public void update(float delta) {
    }
    
    public void render(NVGGraphics g) {
        close.renderAndTick(g);
    }
    
    public void show() {
        show = true;
        close.setVisible(true);
    }
}
