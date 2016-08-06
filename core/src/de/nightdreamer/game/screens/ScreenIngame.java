package de.nightdreamer.game.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.nightdreamer.game.utils.GameManager;
import de.nightdreamer.game.world.*;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 26.04.2014
 * Time: 14:27
 */
public class ScreenIngame implements Screen {

    private GameManager gameManager;
    private SpriteBatch spriteBatch;
    private Music music;

    private Player player;
    private WorldManager worldManager;
    private ShotManager shotManager;
    private EnemyManager enemyManager;


    public ScreenIngame(GameManager gameManager) {
        this.gameManager = gameManager;

        shotManager = new ShotManager(gameManager);
        spriteBatch = new SpriteBatch();
        player = new Player(gameManager, shotManager);
        worldManager = new WorldManager(gameManager);
        enemyManager = new EnemyManager(gameManager, shotManager);

        music = gameManager.assetManager.get("data/sound/ingame_bgm.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(0.75f);
        music.play();
    }

    @Override
    public void render(float delta) {
        gameManager.clearScreen();

        if (gameManager.input.isKeyPressed(Input.Keys.ESCAPE) || gameManager.input.isButtonPressed(gameManager.input.xbox360Pad.BUTTON_START)) {
            gameManager.game.setScreen(new ScreenMenu(gameManager));
            return;
        }

        worldManager.update(delta);
        player.update(delta);

        for (Shot s : shotManager.shots) {
            if (s.id == 1) {
                if (player.bounds.overlaps(s.bounds)) {
                    gameManager.game.setScreen(new ScreenLoose(gameManager));
                    return;
                }
            }
        }


        spriteBatch.begin();
        worldManager.render(spriteBatch);
        shotManager.render(delta, spriteBatch);
        player.render(spriteBatch);
        enemyManager.render(delta, spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        if (music.isPlaying()) {
            music.stop();
        }
        music.dispose();
        worldManager.dispose();
        spriteBatch.dispose();
    }
}
