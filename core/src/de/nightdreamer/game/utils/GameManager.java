package de.nightdreamer.game.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 26.04.2014
 * Time: 13:32
 */
public class GameManager {

    public Game game;
    public Input input;
    public AssetManager assetManager;

    public GameManager(Game game) {
        this.game = game;

        input = new Input();
        assetManager = new AssetManager();
        load();

        // OpenGL Settings
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
    }

    private void load() {
        // textures
        assetManager.load("data/img/background.png", Texture.class);
        assetManager.load("data/img/middle.png", Texture.class);
        assetManager.load("data/img/player.png", Texture.class);
        assetManager.load("data/img/start.png", Texture.class);
        assetManager.load("data/img/exit.png", Texture.class);
        assetManager.load("data/img/move.png", Texture.class);
        assetManager.load("data/img/select.png", Texture.class);
        assetManager.load("data/img/shot.png", Texture.class);
        assetManager.load("data/img/enemy.png", Texture.class);
        assetManager.load("data/img/loosing.png", Texture.class);

        //bgm
        assetManager.load("data/sound/ingame_bgm.mp3", Music.class);

        //sfx
        assetManager.load("data/sound/selecting.wav", Sound.class);
    }

    public void clearScreen() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        input.checkForController();
    }

    public void dispose() {
        assetManager.dispose();
    }

}
