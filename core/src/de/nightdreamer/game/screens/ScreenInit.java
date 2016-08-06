package de.nightdreamer.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.nightdreamer.game.utils.GameManager;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 26.04.2014
 * Time: 13:32
 */
public class ScreenInit implements Screen {

    private GameManager gameManager;
    private SpriteBatch spriteBatch;
    private Sprite font;

    public ScreenInit(GameManager gameManager) {
        this.gameManager = gameManager;

        spriteBatch = new SpriteBatch();
        font = new Sprite(new Texture("data/img/loading_font.png"));

        font.setSize(600.0f, 128.0f);
        font.setPosition(Gdx.graphics.getWidth() / 2f - font.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - font.getHeight() / 2f);
    }

    @Override
    public void render(float delta) {
        gameManager.clearScreen();

        spriteBatch.begin();
        font.draw(spriteBatch);
        spriteBatch.end();

        if (gameManager.assetManager.update()) {
            gameManager.game.setScreen(new ScreenMenu(gameManager));
            return;
        }
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
        font.getTexture().dispose();
    }
}
