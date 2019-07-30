/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states.handlers;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.nanovg.NVGFont;
import com.lwjglwrapper.opengl.objects.Texture;
import com.lwjglwrapper.opengl.objects.Texture2D;
import com.lwjglwrapper.utils.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import nrco.Level;
import static nrco.MainGame.MUSIC_OFF;
import static nrco.MainGame.MUSIC_ON;
import static nrco.MainGame.SOUND_OFF;
import static nrco.MainGame.SOUND_ON;
import nrco.ui.IButton;
import nrco.utils.JARLoader;
import nrco.utils.RenderableTexture;
import nrco.utils.Screenshot;
import nrco.utils.i18n.I18NBundle;

/**
 *
 * @author Welcome
 */
public class AssetHandler {

    private static int bgCount = 3;

    

    public AssetHandler() throws IOException, URISyntaxException {
        init();
    }
    
    private Random randomizer;
    
    public NVGFont gameFont, textFont, boldFont;
    private Texture[] trashCanTextures;
    private Texture2D[] uiTextures;
    private List<List<Texture2D>> trashTextures;
    private List<RenderableTexture> gameBackground;
    private RenderableTexture finalBackground;
    public RenderableTexture starTexture;
    public RenderableTexture background;
    public RenderableTexture lockTexture;
    public RenderableTexture bee;    
    public RenderableTexture title;
    
    private List<String> splashes;
    public String credits;
    
    public final void init() throws IOException, URISyntaxException {
        randomizer = new Random();
        
        gameFont = LWJGL.graphics.createFont("res/GALS.ttf", "galiver-sans");
        textFont = LWJGL.graphics.createFont("res/segoeui.ttf", "segoe-ui");
        boldFont = LWJGL.graphics.createFont("res/calibrib.ttf", "segoe-ui-semibold");
        IButton.font = textFont;
        trashCanTextures = new Texture[4];
        for (int i = 0; i < 4; i++) {
            trashCanTextures[i] = JARLoader.loadTexture("/cans/" + (i + 1) + ".png");
        }
        uiTextures = new Texture2D[4];
        uiTextures[MUSIC_OFF] = JARLoader.loadTexture("/uis/music_off.png");
        uiTextures[MUSIC_ON] = JARLoader.loadTexture("/uis/music_on.png");
        uiTextures[SOUND_OFF] = JARLoader.loadTexture("/uis/sound_off.png");
        uiTextures[SOUND_ON] = JARLoader.loadTexture("/uis/sound_on.png");
        trashTextures = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            File dir = new File("res/trash/" + (i + 1));
            List<Texture2D> textures = new ArrayList<>();
            trashTextures.add(textures);
            
            for (String texPath : dir.list()) {
                textures.add(new Texture2D(dir.getAbsolutePath() + "\\" + texPath));
            }
        }
        starTexture = new RenderableTexture(JARLoader.loadTexture("/star.png"));
        lockTexture = new RenderableTexture(JARLoader.loadTexture("/lock.png"));
        background = new RenderableTexture(JARLoader.loadTexture("/menu.png"));
        gameBackground = new ArrayList<>();
        for (int i = 1; i < bgCount + 1; i++) {
            gameBackground.add(new RenderableTexture(JARLoader.loadTexture("/gamebg" + i + ".png")));
        }
        finalBackground = new RenderableTexture(JARLoader.loadTexture("/gamebg10.jpg"));
        bee = new RenderableTexture(JARLoader.loadTexture("/bee128.png"));
        title = new RenderableTexture(JARLoader.loadTexture("/title.png"));
        splashes = new ArrayList<>();
        credits = Utils.loadResourceAsString(AssetHandler.class, "/lang/credits.txt", StandardCharsets.UTF_8);
        System.out.println(I18NBundle.loadI18N("/lang/english.properties").format(credits));
        BufferedReader splashReader = new BufferedReader(new InputStreamReader(AssetHandler.class.getResourceAsStream("/splashTexts.txt"), StandardCharsets.UTF_8));
        String line;
        while((line = splashReader.readLine()) != null) {
            splashes.add(line);
        }
        splashReader.close();
    }
    
    public void dispose() {
        Stream.of(uiTextures).forEach(Texture::dispose);
        Stream.of(trashCanTextures).forEach(Texture::dispose);
        trashTextures.stream().flatMap(List::stream).forEach(Texture::dispose);
        starTexture.dispose();
        lockTexture.dispose();
        background.dispose();
        gameBackground.forEach(RenderableTexture::dispose);
        bee.dispose();
        title.dispose();
    }
    
    public Texture trashCan(int type) {
        return trashCanTextures[type - 1];
    }
    
    public Texture2D buttonTexture(int type) {
        return uiTextures[type];
    }
    
    public Texture2D randomTrash(int type) {
        List<Texture2D> textures = trashTextures.get(type - 1);
        int size = textures.size();
        int index = randomizer.nextInt(size);
        return textures.get(index);
    }
    
    public String randomSplash() {
        return splashes.get(randomizer.nextInt(splashes.size()));
    }

    public RenderableTexture getGameBackground(Level level) {
        if(level.ordinal() == Level.values().length - 1) {
            return finalBackground;
        } 
        return gameBackground.get(level.ordinal() % bgCount);
    }
}
