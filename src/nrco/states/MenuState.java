/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.math.MathUtils;
import com.lwjglwrapper.utils.ui.Stage;
import java.util.ArrayList;
import java.util.List;
import nrco.MainGame;
import nrco.states.TransitionState.Transition;
import nrco.states.handlers.AudioUIHandler;
import nrco.states.handlers.LevelHandler;
import nrco.ui.IButton;
import nrco.ui.IComboBox;
import nrco.ui.RectUtils;
import nrco.utils.RenderableTexture;
import nrco.utils.i18n.I18NBundle;
import org.joml.Rectanglef;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class MenuState extends IState{

    private Stage stage;
    private AudioUIHandler audioUIHandler;
    private IComboBox languageBox;
    private final IButton play;
    private final IButton instructions;   
    private final IButton credits;
    private final IButton exit;
    
    private long showTime;
    
    public MenuState(MainGame game) {
        super(game);
        audioUIHandler = new AudioUIHandler(game);
        stage = new Stage(LWJGL.window, LWJGL.graphics);
        
        play = new IButton(stage, RectUtils.xywh(LWJGL.window.getWidth() - 600, LWJGL.window.getHeight() - 650, 500, 100), game.format("%play%"), IColor.GREEN.scale(1.7f));
        instructions = new IButton(stage, RectUtils.xywh(LWJGL.window.getWidth() - 600, LWJGL.window.getHeight() - 525, 500, 100), game.format("%instructions%"), IColor.GOLDENROD.scale(1.5f));
        credits = new IButton(stage, RectUtils.xywh(LWJGL.window.getWidth() - 600, LWJGL.window.getHeight() - 400, 500, 100), game.format("%credits%"), IColor.CYAN);
        exit = new IButton(stage, RectUtils.xywh(LWJGL.window.getWidth() - 600, LWJGL.window.getHeight() - 275, 500, 100), game.format("%exit%"), IColor.RED.scale(1.6f));
        
        play.setOnClickListener((s, b, m) -> game.transition(game.menuState, game.levelState, Transition.DOWN, TransitionState.TintTransition.DARKER));
        instructions.setOnClickListener((s, b, m) -> game.transition(game.menuState, game.instructionState, Transition.RIGHT, TransitionState.TintTransition.DARKER));
        credits.setOnClickListener((s, b, m) -> game.transition(game.menuState, game.creditsState, Transition.RIGHT, TransitionState.TintTransition.DARKER));
        exit.setOnClickListener((s, b, m) -> game.exit());
        
        play.setTextAlignment(IButton.LEFT);
        instructions.setTextAlignment(IButton.LEFT);
        credits.setTextAlignment(IButton.LEFT);
        exit.setTextAlignment(IButton.LEFT);
        
        languageBox = new IComboBox(stage, 50, new Rectanglef(100, 50, 400, 300), game.assets.textFont);
        languageBox.setOnChangeListener((idx, cell) -> game.setLanguage(idx));
        languageBox.set(game.languageHandler.getCurrentIndex());
        for (I18NBundle lang : game.languageHandler.getAllLanguages()) {
            new IComboBox.IComboBoxCell(languageBox, lang.format("%lang%"));
        }
    }

    @Override
    public void show() {
        stage.resetState();
        audioUIHandler.show();
        showTime = System.currentTimeMillis();
    }

    @Override
    public void update(float delta) {
        audioUIHandler.update(delta);
        stage.tick();
    }

    @Override
    public void render(float xoff, float yoff) {
        super.render(xoff, yoff);
        
        RenderableTexture.begin();
        RenderableTexture.setTint(new Vector3f(1));
        RenderableTexture.setAlpha(1);
        if(clear) {
            RenderableTexture.setOffset(0, 0);
            game.assets.background.render(new Rectanglef(0, 0, LWJGL.window.getWidth(), LWJGL.window.getHeight()));
        }
        RenderableTexture.setOffset(xoff, yoff);
        audioUIHandler.render();
        RenderableTexture.end();
        
        LWJGL.graphics.begin();
        LWJGL.graphics.translate(xoff, yoff);
        stage.render();
        
        game.assets.textFont.use();
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_LEFT | NanoVG.NVG_ALIGN_TOP);
        LWJGL.graphics.textSize(48);
        LWJGL.graphics.textPaint(IColor.WHITE);
        String langText = game.format("%languages%");
        LWJGL.graphics.text(langText, LWJGL.window.getWidth() - 600, LWJGL.window.getHeight() - 150);
        float textLength = LWJGL.graphics.textLength(langText);
        
        languageBox.setBounds(new Rectanglef(LWJGL.window.getWidth() - 580 + textLength, LWJGL.window.getHeight() - 150, LWJGL.window.getWidth() - 100, LWJGL.window.getHeight() - 100));
        LWJGL.graphics.push();
        game.assets.boldFont.use();
        
        long ellapsed = System.currentTimeMillis() - showTime;
        
        float frac = (ellapsed % 2000) / 2000f;
        float scale;
        if(frac < 0.5f) {
            scale = MathUtils.map(frac, 0, 0.5f, 0.9f, 1.1f);
        } else {
            scale = MathUtils.map(frac, 0.5f, 1, 1.1f, 0.9f);
        }
        LWJGL.graphics.translate(LWJGL.window.getWidth() / 3.5f, 150);
        LWJGL.graphics.rotate(-15);
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_CENTER | NanoVG.NVG_ALIGN_MIDDLE);
        LWJGL.graphics.textSize(90);
        LWJGL.graphics.textPaint(IColor.PURPLE);
        //LWJGL.graphics.text(game.format("%name%").toUpperCase(), 0, 0);
        LWJGL.graphics.end();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    public void resetLanguage() {
        play.setText(game.format("%play%"));
        instructions.setText(game.format("%instructions%"));
        credits.setText(game.format("%credits%"));
        exit.setText(game.format("%exit%"));
    }

    public void updateLevels() {
    }
    
}
