package de.nightdreamer.game;

import com.badlogic.gdx.Game;
import de.nightdreamer.game.screens.ScreenInit;
import de.nightdreamer.game.utils.GameManager;

public class GameApplication extends Game {

    @Override
    public void create() {
        GameManager gameManager = new GameManager(this);
        setScreen(new ScreenInit(gameManager));
    }
}
