/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.geom.shapes.RoundRect;
import static com.lwjglwrapper.utils.ui.Button.CLICKED;
import static com.lwjglwrapper.utils.ui.Button.HOVER;
import static com.lwjglwrapper.utils.ui.Button.NORMAL;
import com.lwjglwrapper.utils.ui.Stage;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import nrco.Level;
import nrco.MainGame;
import nrco.states.TransitionState.Transition;
import nrco.states.handlers.AudioHandler;
import nrco.states.handlers.AudioUIHandler;
import nrco.states.handlers.LevelHandler;
import nrco.states.handlers.WinUIHandler;
import nrco.ui.IButton;
import static nrco.ui.IButton.font;
import nrco.ui.RectUtils;
import nrco.utils.RenderableTexture;
import org.joml.Rectanglef;
import org.joml.Vector3f;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

/**
 *
 * @author Welcome
 */
public class LevelState extends IState{
    
    private Stage stage;
    private AudioUIHandler audioUIHandler;
    private final IButton exit;
    private List<IButton> levelButtons = new ArrayList<>();
    
    private final int spacing;
    
    
    public LevelState(MainGame game) {
        super(game);
        stage = new Stage(LWJGL.window, LWJGL.graphics);
        
        
        spacing = (LWJGL.window.getWidth() - 1200) / 4;
        for (int i = 0; i < Level.values().length; i++) {
            int level = i + 1;
            
            float x = 100 + (spacing + 200) * (i % 5);
            float y = 200 + 300 * (i / 5);
            float width = 200;
            float height = 200;
            Rectanglef rect = RectUtils.xywh(x, y, width, height);
            IColor color;
            switch (level % 3) {
                case 0 : color = IColor.GREEN.scale(1.7f);  break;
                case 1 : color = IColor.GOLDENROD.scale(1.5f);  break;
                default: color = IColor.RED.scale(1.6f);  break;
            }
            IButton button = new IButton(stage, rect, game.levelHandler.levels[i] < 0? "":level + "", color);
            if(game.levelHandler.levels[i] < 0) {
                button.getShapeStates().reset().setAll(new RoundRect(rect, (rect.maxX - rect.minX) * 0.05f))
                      .setAllFills(new IColor(0.8f)).construct(false);
            } else {
                button.setOnClickListener((s, b, m) -> game.startLevel(level));
            }
            levelButtons.add(button);
        }
        exit = new IButton(stage, RectUtils.xywh(LWJGL.window.getWidth() - 350,
                LWJGL.window.getHeight() - 150, 300, 100), game.format("%return%"), IColor.RED.scale(1.6f));
        exit.setOnClickListener((s, b, m) -> game.transition(game.levelState, game.menuState, Transition.UP, TransitionState.TintTransition.BRIGHTER));
        
        audioUIHandler = new AudioUIHandler(game);
        
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
        LWJGL.graphics.textPaint(IColor.AZURE);
        LWJGL.graphics.text(game.format("%select_level%"), LWJGL.window.getWidth() / 2, 100);
        stage.render();
        LWJGL.graphics.end();
        
        RenderableTexture.begin();
        RenderableTexture.setTint(new Vector3f(1));
        RenderableTexture.setAlpha(1);
        RenderableTexture.setOffset(xoff, yoff);
        audioUIHandler.render();
        for (int i = 0; i < Level.values().length; i++) {
            int stars = game.levelHandler.levels[i];
            float x = 100 + (spacing + 200) * (i % 5);
            float y = LWJGL.window.getHeight() - 400 - 300 * (i / 5);
            float width = 200;
            float height = 200;
            if(stars < 0) {
                Rectanglef rect = RectUtils.xywh(x, y, width, height);
                game.assets.lockTexture.render(rect);
            } else {
                float centerX = x + 100;
                float centerY = y;
                float size = 72;
                
                if(stars > 0) game.assets.starTexture.render(RectUtils.center(centerX - size, centerY, size, size));
                if(stars > 1) game.assets.starTexture.render(RectUtils.center(centerX, centerY, size, size));
                if(stars > 2) game.assets.starTexture.render(RectUtils.center(centerX + size, centerY, size, size));
            }
        }
        RenderableTexture.end();
    }

    @Override
    public void show() {
        stage.resetState();
        audioUIHandler.show();
    }

    @Override
    public void update(float delta) {
        stage.tick();
        audioUIHandler.update(delta);
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    public void resetLanguage() {
        exit.setText(game.format("%return%"));
    }

    public void updateLevels() {
        for (int i = 0; i < levelButtons.size(); i++) {
            IButton button = levelButtons.get(i);
            int level = i + 1;
            if(game.levelHandler.levels[i] < 0) {
                button.setText("");
                Rectanglef rect = button.getRect();
                button.getShapeStates().reset().setAll(new RoundRect(rect, (rect.maxX - rect.minX) * 0.05f))
                      .setAllFills(new IColor(0.8f)).construct(false);
                        
            } else {
                button.setText(level + "");
                button.resetShapeStates();
                button.setOnClickListener((s, b, m) -> game.startLevel(level));
            }
        }
    }
    
}
