package de.nightdreamer.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.nightdreamer.game.utils.GameManager;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 27.04.2014
 * Time: 13:44
 */
public class ShotManager {

    public ArrayList<Shot> shots = new ArrayList<Shot>();
    public Sprite shot;

    public ShotManager(GameManager gameManager) {
        shot = new Sprite(gameManager.assetManager.get("data/img/shot.png", Texture.class));
    }

    public void render(float delta, SpriteBatch spriteBatch) {
        for (int i = 0; i < shots.size(); i++) {
            Shot s = shots.get(i);
            s.update(delta);
            if (s.id == 0) {
                if (shot.isFlipX()) {
                    shot.flip(true, false);
                }
                shot.setPosition(s.x, s.y);
                shot.draw(spriteBatch);

                if (s.x > Gdx.graphics.getWidth()) {
                    shots.remove(shots.indexOf(s));
                    i--;
                }
            }
            if (s.id == 1) {
                if (!shot.isFlipX()) {
                    shot.flip(true, false);
                }
                shot.setPosition(s.x, s.y);
                shot.draw(spriteBatch);

                if (s.x > Gdx.graphics.getWidth()) {
                    shots.remove(shots.indexOf(s));
                    i--;
                }
            }
        }
    }
}
