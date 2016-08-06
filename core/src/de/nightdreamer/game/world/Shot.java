package de.nightdreamer.game.world;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 27.04.2014
 * Time: 14:40
 */
public class Shot {
    public int id;
    public float x, y;
    public Rectangle bounds;
    private float velocity;

    public Shot(int id, float x, float y, float velocity) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        bounds = new Rectangle(x, y, 69f, 29f);
    }

    public void update(float delta) {
        x += delta * velocity;
        bounds.setX(x);
    }
}
