package de.nightdreamer.game.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.nightdreamer.game.utils.GameManager;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 27.04.2014
 * Time: 16:13
 */
public class ScreenLoose implements Screen {

    private GameManager gameManager;
    private SpriteBatch spriteBatch;
    private Sprite bg;

    public ScreenLoose(GameManager gameManager) {
        this.gameManager = gameManager;

        spriteBatch = new SpriteBatch();
        bg = new Sprite(gameManager.assetManager.get("data/img/loosing.png", Texture.class));
    }

    @Override
    public void render(float delta) {
        gameManager.clearScreen();

        if (gameManager.input.isKeyPressed(Input.Keys.SPACE) || gameManager.input.isButtonPressed(gameManager.input.xbox360Pad.BUTTON_B)) {
            gameManager.game.setScreen(new ScreenIngame(gameManager));
            return;
        } else if (gameManager.input.isKeyPressed(Input.Keys.ESCAPE) || gameManager.input.isButtonPressed(gameManager.input.xbox360Pad.BUTTON_START)) {
            gameManager.game.setScreen(new ScreenMenu(gameManager));
            return;
        }

        spriteBatch.begin();
        bg.draw(spriteBatch);
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
