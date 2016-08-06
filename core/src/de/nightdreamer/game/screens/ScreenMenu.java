package de.nightdreamer.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.nightdreamer.game.utils.GameManager;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 26.04.2014
 * Time: 19:06
 */
public class ScreenMenu implements Screen {

    private GameManager gameManager;
    private SpriteBatch spriteBatch;

    private Sound sfx_select;
    private Sprite start, exit, move, select;
    private boolean selected[];

    public ScreenMenu(GameManager gameManager) {
        this.gameManager = gameManager;

        spriteBatch = new SpriteBatch();

        sfx_select = gameManager.assetManager.get("data/sound/selecting.wav", Sound.class);

        start = new Sprite(gameManager.assetManager.get("data/img/start.png", Texture.class));
        start.setSize(192.0f, 128.0f);
        start.setPosition(Gdx.graphics.getWidth() / 2f - start.getWidth() / 2f, Gdx.graphics.getHeight() - start.getHeight() - 100.0f);

        exit = new Sprite(gameManager.assetManager.get("data/img/exit.png", Texture.class));
        exit.setSize(192.0f, 128.0f);
        exit.setPosition(Gdx.graphics.getWidth() / 2f - start.getWidth() / 2f, start.getY() - 150.0f);

        move = new Sprite(gameManager.assetManager.get("data/img/move.png", Texture.class));
        move.setSize(192.0f, 128.0f);
        move.setPosition(0.0f, 0.0f);

        select = new Sprite(gameManager.assetManager.get("data/img/select.png", Texture.class));
        select.setSize(192.0f, 128.0f);
        select.setPosition(Gdx.graphics.getWidth() - select.getWidth(), 0.0f);

        selected = new boolean[2];
        selected[0] = true;
        selected[1] = false;
    }

    @Override
    public void render(float delta) {
        gameManager.clearScreen();

        if (gameManager.input.isKeyPressed(Input.Keys.ESCAPE) || gameManager.input.isButtonPressed(gameManager.input.xbox360Pad.BUTTON_START)) {
            Gdx.app.exit();
            return;
        }

        if (gameManager.input.isKeyPressed(Input.Keys.ENTER) || gameManager.input.isButtonPressed(gameManager.input.xbox360Pad.BUTTON_A)) {
            if (selected[0]) {
                gameManager.game.setScreen(new ScreenIngame(gameManager));
                return;
            } else if (selected[1]) {
                Gdx.app.exit();
                return;
            }
        }

        if (gameManager.input.isKeyPressed(Input.Keys.W) || gameManager.input.isKeyPressed(Input.Keys.S) || gameManager.input.getAxisOnce(gameManager.input.xbox360Pad.AXIS_LEFT_Y)) {
            if (selected[0]) {
                selected[0] = false;
                selected[1] = true;
            } else {
                selected[1] = false;
                selected[0] = true;
            }
            sfx_select.play();
        }

        if (selected[0]) {
            start.setScale(1.25f);
            exit.setScale(1.0f);
        } else if (selected[1]) {
            start.setScale(1.0f);
            exit.setScale(1.25f);
        }

        spriteBatch.begin();
        start.draw(spriteBatch);
        exit.draw(spriteBatch);
        move.draw(spriteBatch);
        select.draw(spriteBatch);
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
        spriteBatch.dispose();
    }
}
