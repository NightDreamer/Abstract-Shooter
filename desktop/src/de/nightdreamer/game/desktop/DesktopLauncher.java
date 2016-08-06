package de.nightdreamer.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.nightdreamer.game.GameApplication;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.samples = 8;
        config.width = 800;
        config.height = 600;
        config.resizable = false;
        config.fullscreen = false;
        config.vSyncEnabled = true;

        new LwjglApplication(new GameApplication(), config);
    }
}
