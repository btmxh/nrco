/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states.handlers;

import com.lwjglwrapper.LWJGL;
import nrco.MainGame;
import nrco.ui.ToggleImageButton;
import nrco.ui.RectUtils;
import nrco.utils.RenderableTexture;

/**
 *
 * @author Welcome
 */
public class AudioUIHandler {

    private MainGame game;
    private ToggleImageButton musicButton, soundButton;

    public AudioUIHandler(MainGame game) {
        this.game = game;
        final float size = 92.5f;
        musicButton = new ToggleImageButton(game.assets.buttonTexture(MainGame.MUSIC_OFF),
                game.assets.buttonTexture(MainGame.MUSIC_ON), RectUtils.xywh(LWJGL.window.getWidth() - size * 2 - 20, LWJGL.window.getHeight() - size - 10, size, size));
        soundButton = new ToggleImageButton(game.assets.buttonTexture(MainGame.SOUND_OFF),
                game.assets.buttonTexture(MainGame.SOUND_ON), RectUtils.xywh(LWJGL.window.getWidth() - size, LWJGL.window.getHeight() - size - 10, size, size));

        musicButton.setToggled(game.audioHandler.musicEnabled);
        soundButton.setToggled(game.audioHandler.soundEnabled);

        musicButton.setOnToggledListener(toggled -> {
            game.audioHandler.musicEnabled = toggled;
            if (toggled) {
                game.audioHandler.play(AudioHandler.MUSIC, true);
            } else {
                game.audioHandler.stopMusic();
            }
        });
        soundButton.setOnToggledListener(toggled -> game.audioHandler.soundEnabled = toggled);
    }

    public void update(float delta) {
        soundButton.update(delta);
        musicButton.update(delta);
    }

    public void show() {
        musicButton.setToggled(game.audioHandler.musicEnabled);
        soundButton.setToggled(game.audioHandler.soundEnabled);
    }

    public void render() {
        musicButton.render();
        soundButton.render();
    }

}
