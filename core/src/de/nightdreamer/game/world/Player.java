package de.nightdreamer.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import de.nightdreamer.game.utils.GameManager;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 26.04.2014
 * Time: 14:36
 */
public class Player {

    public Rectangle bounds;
    private GameManager gameManager;
    private ShotManager shotManager;
    private Sprite sprite;
    private Sound shoot;

    public Player(GameManager gameManager, ShotManager shotManager) {
        this.gameManager = gameManager;
        this.shotManager = shotManager;

        sprite = new Sprite(gameManager.assetManager.get("data/img/player.png", Texture.class));
        sprite.setSize(64.0f, 64.0f);
        sprite.setPosition(1.25f * sprite.getWidth(), Gdx.graphics.getHeight() / 2f - sprite.getHeight() / 2f);

        bounds = new Rectangle(sprite.getBoundingRectangle());
        shoot = gameManager.assetManager.get("data/sound/selecting.wav", Sound.class);
    }

    public void update(float delta) {
        float velocity = delta * 250.0f;

        if (Gdx.input.isKeyPressed(Input.Keys.W) || gameManager.input.getAxis(gameManager.input.xbox360Pad.AXIS_LEFT_Y) < -0.25f) {
            sprite.setPosition(sprite.getX(), sprite.getY() + velocity);

            // upper game field bounds
            if (sprite.getY() + sprite.getHeight() > Gdx.graphics.getHeight()) {
                sprite.setY(Gdx.graphics.getHeight() - sprite.getHeight());
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || gameManager.input.getAxis(gameManager.input.xbox360Pad.AXIS_LEFT_X) < -0.25f) {
            sprite.setPosition(sprite.getX() - velocity, sprite.getY());

            // left game field bounds
            if (sprite.getX() < 0.0f) {
                sprite.setX(0.0f);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || gameManager.input.getAxis(gameManager.input.xbox360Pad.AXIS_LEFT_Y) > 0.25f) {
            sprite.setPosition(sprite.getX(), sprite.getY() - velocity);

            // lower game field bounds
            if (sprite.getY() < 0.0f) {
                sprite.setY(0.0f);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || gameManager.input.getAxis(gameManager.input.xbox360Pad.AXIS_LEFT_X) > 0.25f) {
            sprite.setPosition(sprite.getX() + velocity, sprite.getY());

            // right game field bounds
            if (sprite.getX() + sprite.getWidth() > Gdx.graphics.getWidth()) {
                sprite.setX(Gdx.graphics.getWidth() - sprite.getWidth());
            }
        }

        bounds.set(sprite.getBoundingRectangle());


        if (gameManager.input.isKeyPressed(Input.Keys.ENTER) || gameManager.input.isButtonPressed(gameManager.input.xbox360Pad.BUTTON_A)) {
            shotManager.shots.add(new Shot(0, sprite.getX() + sprite.getWidth() - shotManager.shot.getWidth(), sprite.getY() + sprite.getHeight() / 2f - shotManager.shot.getHeight() / 2f, 375f));
            shoot.play(1.0f);
        }
    }

    public void render(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }

}
