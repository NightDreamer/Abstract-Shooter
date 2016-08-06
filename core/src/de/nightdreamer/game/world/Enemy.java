package de.nightdreamer.game.world;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 27.04.2014
 * Time: 15:26
 */
public class Enemy {

    public float x, y, velocity;
    public Rectangle bounds;
    public boolean killed = false;

    public Enemy(float x, float y, float velocity) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;

        bounds = new Rectangle(x, y, 64f, 64f);
    }

    public void update(Sound shoot, Random rndm, ShotManager shotManager, float delta) {
        for (int i = 0; i < shotManager.shots.size(); i++) {
            Shot s = shotManager.shots.get(i);
            if (s.id == 0) {
                if (bounds.overlaps(s.bounds)) {
                    killed = true;
                    shotManager.shots.remove(shotManager.shots.indexOf(s));
                    i--;
                }
            }
        }

        int shooting = rndm.nextInt(1000);
        if (shooting < 7) {
            shoot.play(1.0f);
            shotManager.shots.add(new Shot(1, x, y + 32f - shotManager.shot.getHeight() / 2f, -375f));
        }

        x -= delta * velocity;
        bounds.setX(x);
    }

}
