package com.rawit.crosstheroad.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WelcomeScreen extends ScreenAdapter {

    CrossTheRoadGame crossTheRoadGame;
    SpriteBatch batch;

    Texture welcomeImg;
    public boolean canLeave;

    public WelcomeScreen(CrossTheRoadGame crossTheRoadGame) {
        this.crossTheRoadGame = crossTheRoadGame;
        batch = crossTheRoadGame.batch;
        welcomeImg = new Texture("welcome.jpg");
        canLeave = true;
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(welcomeImg, 0, 0, crossTheRoadGame.WIDTH, crossTheRoadGame.HEIGHT);
        batch.end();
    }

    private void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            crossTheRoadGame.changeScreen(CrossTheRoadGame.Screen.Game);
        }
    }

}
