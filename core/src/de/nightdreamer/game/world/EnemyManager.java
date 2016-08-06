package de.nightdreamer.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.nightdreamer.game.utils.GameManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 27.04.2014
 * Time: 15:26
 */
public class EnemyManager {

    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ShotManager shotManager;
    private Sprite sprite;
    private Random rndm;
    private Sound shoot;

    public EnemyManager(GameManager gameManager, ShotManager shotManager) {
        this.shotManager = shotManager;
        sprite = new Sprite(gameManager.assetManager.get("data/img/enemy.png", Texture.class));
        sprite.setSize(64f, 64f);

        rndm = new Random();
        shoot = gameManager.assetManager.get("data/sound/selecting.wav", Sound.class);
    }

    public void render(float delta, SpriteBatch spriteBatch) {
        if (enemies.size() < 8) {
            int size = rndm.nextInt(7) + 1;

            for (int i = 0; i < size; i++) {
                enemies.add(new Enemy(Gdx.graphics.getWidth() + rndm.nextInt(250), rndm.nextInt(Gdx.graphics.getHeight() - 128) + 64, 125f));
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update(shoot, rndm, shotManager, delta);

            sprite.setPosition(e.x, e.y);
            sprite.draw(spriteBatch);

            if (e.x + 64f < 0.0f || e.killed) {
                enemies.remove(enemies.indexOf(e));
                i--;
            }
        }
    }

}
