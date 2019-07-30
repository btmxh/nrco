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
import static nrco.states.TransitionState.TintTransition.BRIGHTER;
import static nrco.states.TransitionState.Transition.LEFT;
import nrco.ui.IButton;
import nrco.ui.RectUtils;
import nrco.utils.RenderableTexture;
import org.joml.Rectanglef;
import org.joml.Vector3f;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class CreditsState extends IState{

    private Stage stage;
    private String credits;
    private final IButton exit;
    
    public CreditsState(MainGame game) {
        super(game);
        stage = new Stage(LWJGL.window, LWJGL.graphics);
        exit = new IButton(stage, RectUtils.xywh(LWJGL.window.getWidth() - 350,
                LWJGL.window.getHeight() - 150, 300, 100), game.format("%return%"), IColor.RED.scale(1.6f));
        exit.setOnClickListener((s, b, m) -> game.transition(game.creditsState, game.menuState, LEFT, BRIGHTER));
        credits = game.format(game.assets.credits);
    }

    @Override
    public void render(float xoff, float yoff) {
        super.render(xoff, yoff);
        if(clear) {
            RenderableTexture.begin();
            RenderableTexture.setTint(new Vector3f(0.3f));
            RenderableTexture.setAlpha(1);
            RenderableTexture.setOffset(0, 0);
            game.assets.background.render(new Rectanglef(0, 0, LWJGL.window.getWidth(), LWJGL.window.getHeight()));
            RenderableTexture.end();
        }
        LWJGL.graphics.begin();
        LWJGL.graphics.translate(xoff, yoff);
        game.assets.textFont.use();
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_CENTER | NanoVG.NVG_ALIGN_MIDDLE);
        LWJGL.graphics.textSize(108);
        LWJGL.graphics.textPaint(IColor.WHITE);
        LWJGL.graphics.text(game.format("%credits%"), LWJGL.window.getWidth() / 2, 100);
        stage.render();
        game.assets.textFont.use();
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_LEFT | NanoVG.NVG_ALIGN_BASELINE);
        LWJGL.graphics.textSize(56);
        LWJGL.graphics.textBox(credits, 50, 220, LWJGL.window.getWidth() - 50 * 2);
        LWJGL.graphics.end();
        
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
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    public void resetLanguage() {
        exit.setText(game.format("%return%"));
        credits = game.format(game.assets.credits);
    }
    
}
