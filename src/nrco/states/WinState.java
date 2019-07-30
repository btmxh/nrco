/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.geom.PaintedShape;
import com.lwjglwrapper.utils.geom.shapes.RoundRect;
import com.lwjglwrapper.utils.ui.Stage;
import nrco.Level;
import nrco.MainGame;
import nrco.states.handlers.ScoringHandler;
import nrco.ui.IButton;
import nrco.ui.RectUtils;
import nrco.utils.RenderableTexture;
import nrco.utils.Screenshot;
import org.joml.Vector3f;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class WinState extends IState{

    private ScoringHandler scoringHandler;
    private Stage stage;
    private final IButton retry;
    private final IButton exit;
    private int pastStars;
    private int level;
    
    public WinState(MainGame game) {
        super(game);
        stage = new Stage(LWJGL.window, LWJGL.graphics);
        
        
        final float height = 100;
        retry = new IButton(stage, RectUtils.xywh(LWJGL.window.getWidth() - 350, LWJGL.window.getHeight() - height - 50, 300, height), game.format("%retry%"),
                                IColor.GOLDENROD.scale(1.5f));
        exit = new IButton(stage, RectUtils.xywh(LWJGL.window.getWidth() - 700, LWJGL.window.getHeight() - height - 50, 300, height), game.format("%exit%"),
                                IColor.DARKRED.scale(1.6f));
        retry.setOnClickListener((s, b, m) -> game.setState(game.gameState.reset()));
        exit.setOnClickListener((s, b, m) -> {
            if(level == Level.values().length && pastStars <= 0) {
                game.setState(game.completeState);
            } else {
                game.setState(game.levelState);
            }
        });
    }

    @Override
    public void show() {
        stage.resetState();
    }

    @Override
    public void update(float delta) {
        stage.tick();
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
        
        new PaintedShape(new RoundRect(RectUtils.center(LWJGL.window.getWidth() / 2, LWJGL.window.getHeight() / 2, 
                LWJGL.window.getWidth() - 200, LWJGL.window.getHeight() - 200), 16), 
                IColor.GOLDENROD.darker(), null).render(LWJGL.graphics);
        new PaintedShape(new RoundRect(RectUtils.center(LWJGL.window.getWidth() / 2, LWJGL.window.getHeight() / 2, 
                LWJGL.window.getWidth() - 230, LWJGL.window.getHeight() - 230), 16), 
                IColor.GOLDENROD, null).render(LWJGL.graphics);
        
        
        
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_BASELINE | NanoVG.NVG_ALIGN_LEFT);
        LWJGL.graphics.textPaint(IColor.WHITE);
        LWJGL.graphics.textSize(100);
        
        game.assets.textFont.use();
        LWJGL.graphics.text(game.format("%score%: ") + scoringHandler.score, 130, 300);
        LWJGL.graphics.text(game.format("%combo%: ") + scoringHandler.maxCombo, 130, 600);
        LWJGL.graphics.text(game.format("%accuracy%: ") + String.format("%.1f", scoringHandler.accuracy) + "%", 130, 700);
        
        game.assets.gameFont.use();
        int[] scoreTypes = {300, 200, 100, 50, 0};
        final int rows = 2;
        final float spacing = 300;
        for (int i = 0; i < scoreTypes.length; i++) {
            int score = scoreTypes[i];
            Integer count = scoringHandler.scoreCount.get(score);
            if(count == null)  count = 0;
            String rep = score + " x " + count;
            if(score == 0)  rep = "X x " + count;
            IColor color;
            switch (score) {
                case 0  : color = IColor.RED;   break;
                case 50 : color = IColor.GOLD;   break;
                case 100: color = IColor.LIME;   break;
                case 200: color = IColor.BLUE;   break;
                case 300: color = IColor.AQUA;   break;
                default: color = IColor.BLACK;   break;
            }
            LWJGL.graphics.textPaint(color);
            LWJGL.graphics.text(rep, (float) (130 + Math.ceil(i / rows) * spacing), 400 + (i % rows) * 100);
        }
        
        stage.render();
        LWJGL.graphics.end();
        
        final float size = 128, offset = 10;
        
        RenderableTexture.begin();
        RenderableTexture.setTint(new Vector3f(1));
        RenderableTexture.setAlpha(1);
        RenderableTexture.setOffset(xoff, yoff);
        int stars = scoringHandler.stars();
        if(stars > 0)   game.assets.starTexture.render(RectUtils.center(LWJGL.window.getWidth() / 2 - size - offset, LWJGL.window.getHeight() - 100, size, size));
        if(stars > 1)   game.assets.starTexture.render(RectUtils.center(LWJGL.window.getWidth() / 2, LWJGL.window.getHeight() - 100, size, size));
        if(stars > 2)   game.assets.starTexture.render(RectUtils.center(LWJGL.window.getWidth() / 2 + size + offset, LWJGL.window.getHeight() - 100, size, size));
        RenderableTexture.end();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    public void resetLanguage() {
        retry.setText(game.format("%retry%"));
        exit.setText(game.format("%return%"));
    }

    void setData(ScoringHandler scoringHandler, Level level, int stars) {
        
        this.scoringHandler = scoringHandler;
        this.level = level.ordinal() + 1;
        this.pastStars = stars;
    }
    
}
