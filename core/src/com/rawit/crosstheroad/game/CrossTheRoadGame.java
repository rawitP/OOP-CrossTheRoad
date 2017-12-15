package com.rawit.crosstheroad.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CrossTheRoadGame extends Game {

	public static final int HEIGHT = 720;
	public static final int WIDTH = 1280;

	SpriteBatch batch;
	GameScreen gameScreen;
	WelcomeScreen welcomeScreen;

	enum Screen {
		Game, Welcome
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		welcomeScreen = new WelcomeScreen(this);
		setScreen(welcomeScreen);
	}

	public void changeScreen(Screen screen) {
		switch (screen) {
			case Game:
				gameScreen = new GameScreen(this);
				setScreen(gameScreen);
				break;
			case Welcome:
				setScreen(welcomeScreen);
		}
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
