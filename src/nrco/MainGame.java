/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco;

import nrco.states.handlers.AudioHandler;
import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.display.Window;
import com.lwjglwrapper.display.WindowCallbacks;
import com.lwjglwrapper.utils.Logger;
import com.lwjglwrapper.utils.Utils;
import com.lwjglwrapper.utils.states.GameStateLoop;
import java.io.IOException;
import nrco.states.CompleteState;
import nrco.states.CreditsState;
import nrco.states.DeathState;
import nrco.states.GameState;
import nrco.states.IState;
import nrco.states.InstructionState;
import nrco.states.LevelState;
import nrco.states.MenuState;
import nrco.states.OptionState;
import nrco.states.SplashState;
import nrco.states.TransitionState;
import nrco.states.TransitionState.TintTransition;
import nrco.states.TransitionState.Transition;
import nrco.states.WinState;
import nrco.states.handlers.AssetHandler;
import nrco.states.handlers.LanguageHandler;
import nrco.states.handlers.LevelHandler;
import nrco.states.handlers.ParticleHandler;
import nrco.ui.RectUtils;
import nrco.utils.JARLoader;
import nrco.utils.RenderableTexture;
import nrco.utils.Screenshot;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Welcome
 */
public class MainGame extends GameStateLoop{

    public static final int MUSIC_OFF = 0, MUSIC_ON = 1, SOUND_OFF = 2, SOUND_ON = 3;
    
    
    public GameState gameState;
    public OptionState optionState;
    public MenuState menuState;
    public DeathState deathState;
    public WinState winState;
    public LevelState levelState;
    public TransitionState transitionState;
    public InstructionState instructionState;
    public SplashState splashState;
    public CompleteState completeState;
    public CreditsState creditsState;
    
    public AssetHandler assets;
    public AudioHandler audioHandler;
    public LanguageHandler languageHandler;
    public LevelHandler levelHandler;
    public ParticleHandler particleHandler;
    
    
    public MainGame() {
        super();
        Logger.enable = true;
    }
    
    public static void main(String[] args) {
        new MainGame().run();
    }

    @Override
    protected void init() throws Exception {
        super.init();
        
        
        window.setIcon(Utils.ioResourceToByteBuffer(MainGame.class.getResourceAsStream("/bee128.png"), 16 * 1024));
        
        window.createNVGGraphics();
        RenderableTexture.createShader();
        Screenshot.createShader();
        window.setCursor(window.addCursor(JARLoader.loadTextureData("/bee128.png"), 64, 64));

        
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        assets = new AssetHandler();
        languageHandler = new LanguageHandler();
        levelHandler = new LevelHandler(this);
        audioHandler = new AudioHandler();
        particleHandler = new ParticleHandler();
        audioHandler.loadAudio();
        
        SaveFile.load("res/save.json").update(this);
        
        gameState = new GameState(this, 1);
        optionState = new OptionState(this);
        menuState = new MenuState(this);
        deathState = new DeathState(this);
        winState = new WinState(this);
        levelState = new LevelState(this);
        transitionState = new TransitionState(this);
        transitionState.setBackground(assets.background);
        instructionState = new InstructionState(this);
        splashState = new SplashState(this);
        completeState = new CompleteState(this);
        creditsState = new CreditsState(this);
        
        super.setState(menuState);
        
        
        
        audioHandler.play(AudioHandler.MUSIC, true);
    }

    @Override
    protected void update(float delta) throws Exception {
        super.update(delta);
        particleHandler.update(delta);
    }

    @Override
    protected void render() throws Exception {
        super.render();
        particleHandler.render();
        if(window.fullScreen()) {
            RenderableTexture.begin();
            RenderableTexture.setTint(new Vector3f(1));
            RenderableTexture.setAlpha(1);
            RenderableTexture.setOffset(0, 0);
            assets.bee.render(RectUtils.center(LWJGL.mouse.getCursorX(), LWJGL.window.getHeight() - LWJGL.mouse.getCursorY(), 128, 128));
            RenderableTexture.end();
        }
    }

    @Override
    protected void dispose() {
        super.dispose();
        audioHandler.dispose();
        assets.dispose();
        RenderableTexture.disposeShader();
        Screenshot.disposeShader();
    }

    @Override
    protected void createWindow() {
        GLFW.glfwInit();
        GLFWVidMode mode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        window = new Window(mode.width(), mode.height(), "Nhặt rác cùng ong", 60);
        window.fullScreen(true);
        window.setWindowCallbacks(new WindowCallbacks(){
            @Override
            public void windowOnClose(Window window) {
                super.windowOnClose(window);
                try {
                    SaveFile.create(MainGame.this).save("res/save.json");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        window.create();
        
        window.setToMainWindow();
        
        window.setVisible(true);
        
    }
    
    public Screenshot getGameScreenshot() {
        return gameState.getScreenshot();
    }
    
    public void transition(IState from, IState to, Transition transition, TintTransition tintTransition) {
        transitionState.start(from, to, 1f);
        transitionState.setTransition(transition);
        transitionState.setTintTransition(tintTransition);
        setState(transitionState);
    }

    public void startLevel(int level) {
        gameState.setLevel(level);
        gameState.reset();
        setState(gameState);
    }
    
    public String format(String string) {
        return languageHandler.format(string);
    }
    
    public void setLanguage(int langIndex) {
        languageHandler.set(langIndex);
        instructionState.resetLanguage();
        levelState.resetLanguage();
        menuState.resetLanguage();
        optionState.resetLanguage();
        winState.resetLanguage();
        creditsState.resetLanguage();
    }
}
