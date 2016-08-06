package de.nightdreamer.game.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.nightdreamer.game.utils.GameManager;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 26.04.2014
 * Time: 14:37
 */
public class WorldManager {

    private GameManager gameManager;
    private Sprite bg[], mid[];

    public WorldManager(GameManager gameManager) {
        this.gameManager = gameManager;

        bg = new Sprite[2];
        bg[0] = new Sprite(gameManager.assetManager.get("data/img/background.png", Texture.class));
        bg[0].setSize(1600f, 600f);
        bg[0].setPosition(0.0f, 0.0f);
        bg[1] = new Sprite(gameManager.assetManager.get("data/img/background.png", Texture.class));
        bg[1].setSize(1600f, 600f);
        bg[1].setPosition(1600.0f, 0.0f);

        mid = new Sprite[2];
        mid[0] = new Sprite(gameManager.assetManager.get("data/img/middle.png", Texture.class));
        mid[0].setSize(1000f, 600f);
        mid[0].setPosition(0.0f, 0.0f);
        mid[1] = new Sprite(gameManager.assetManager.get("data/img/middle.png", Texture.class));
        mid[1].setSize(1000f, 600f);
        mid[1].setPosition(1000.0f, 0.0f);
    }

    public void update(float delta) {
        // background
        float tmp_bg = -75.0f * delta;

        bg[0].setX(bg[0].getX() + tmp_bg);
        bg[1].setX(bg[1].getX() + tmp_bg);

        if (bg[0].getX() + bg[0].getWidth() < 0.0f) {
            bg[0].setX(bg[1].getX() + bg[1].getWidth());
        }

        if (bg[1].getX() + bg[1].getWidth() < 0.0f) {
            bg[1].setX(bg[0].getX() + bg[0].getWidth());
        }

        // middle
        float tmp_mid = -150.0f * delta;

        mid[0].setX(mid[0].getX() + tmp_mid);
        mid[1].setX(mid[1].getX() + tmp_mid);

        if (mid[0].getX() + mid[0].getWidth() < 0.0f) {
            mid[0].setX(mid[1].getX() + mid[1].getWidth());
        }

        if (mid[1].getX() + mid[1].getWidth() < 0.0f) {
            mid[1].setX(mid[0].getX() + mid[0].getWidth());
        }
    }

    public void render(SpriteBatch spriteBatch) {
        bg[0].draw(spriteBatch);
        bg[1].draw(spriteBatch);

        mid[0].draw(spriteBatch);
        mid[1].draw(spriteBatch);
    }

    public void dispose() {

    }

}
