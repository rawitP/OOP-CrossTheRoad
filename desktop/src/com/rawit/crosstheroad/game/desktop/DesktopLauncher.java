package com.rawit.crosstheroad.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rawit.crosstheroad.game.CrossTheRoadGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = CrossTheRoadGame.WIDTH;
		config.height = CrossTheRoadGame.HEIGHT;
		config.foregroundFPS = 60;
		new LwjglApplication(new CrossTheRoadGame(), config);
	}
}
