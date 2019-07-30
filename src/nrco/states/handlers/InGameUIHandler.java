/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states.handlers;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.math.MathUtils;
import nrco.MainGame;
import nrco.states.GameState;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class InGameUIHandler {
    private ScoringHandler scoringHandler;
    private MainGame game;

    public InGameUIHandler(MainGame game, ScoringHandler scoringHandler) {
        this.scoringHandler = scoringHandler;
        this.game = game;
    }
    
    public void render() {
        IColor color = IColor.hsv(MathUtils.map(scoringHandler.fakeHP, 20, 100, 0f, 0.4f), 1, 1);

        //Combo
        LWJGL.graphics.beginPath();
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_LEFT | NanoVG.NVG_ALIGN_BASELINE);
        LWJGL.graphics.textPaint(color);
        LWJGL.graphics.textSize(64f);
        LWJGL.graphics.text("x" + scoringHandler.combo, 0, LWJGL.window.getHeight());
        
        //HP
        final float rectWidth = 500;
        LWJGL.graphics.rect((LWJGL.window.getWidth() - rectWidth) / 2, 10, rectWidth, 80);
        LWJGL.graphics.fill(new IColor(1, 0.5f));
        
        LWJGL.graphics.rect((LWJGL.window.getWidth() - rectWidth) / 2, 10, rectWidth * scoringHandler.fakeHP / 100f, 80);
        LWJGL.graphics.fill(color);
        
        LWJGL.graphics.rect((LWJGL.window.getWidth() - rectWidth) / 2, 10, rectWidth, 80);
        LWJGL.graphics.stroke(IColor.BLACK);
        
        
        //Garbages
        
        float frac = scoringHandler.fakeGarbages / game.gameState.level.garbageRequirements;
        
        LWJGL.graphics.rect((LWJGL.window.getWidth() - rectWidth) / 2, LWJGL.window.getHeight() - 60, rectWidth, 50);
        LWJGL.graphics.fill(new IColor(1, 0.5f));
        
        LWJGL.graphics.rect((LWJGL.window.getWidth() - rectWidth) / 2, LWJGL.window.getHeight() - 60, rectWidth * frac, 50);
        LWJGL.graphics.fill(color);
        
        LWJGL.graphics.rect((LWJGL.window.getWidth() - rectWidth) / 2, LWJGL.window.getHeight() - 60, rectWidth, 50);
        LWJGL.graphics.stroke(IColor.BLACK);
        
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_CENTER | NanoVG.NVG_ALIGN_BASELINE);
        LWJGL.graphics.textPaint(IColor.BLUE);
        LWJGL.graphics.textSize(25f);
        String progress = String.format("%.1f", frac * 100) + "% %completed%";
        LWJGL.graphics.text(game.format(progress), LWJGL.window.getWidth() / 2, LWJGL.window.getHeight() - 30);
        
        //Score and Accuracy
        String text = String.format("%09d", scoringHandler.fakeScore) + "   " +
                      String.format("%.1f", scoringHandler.fakeAccuracy) + "%";
        
        LWJGL.graphics.beginPath();
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_CENTER | NanoVG.NVG_ALIGN_BASELINE);
        LWJGL.graphics.textPaint(color);
        LWJGL.graphics.textSize(100f);
        LWJGL.graphics.text(text, LWJGL.window.getWidth() / 2, 150);
        
    }
    
    public void end() {
        
    }
}
