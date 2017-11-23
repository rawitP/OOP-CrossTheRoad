package com.rawit.crosstheroad.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.rawit.crosstheroad.game.CrossTheRoadGame;

public class GameScreen extends ScreenAdapter {

    private CrossTheRoadGame crossTheRoadGame;
    World world;
    WorldRenderer worldRenderer;

    public GameScreen(CrossTheRoadGame crossTheRoadGame) {
        this.crossTheRoadGame = crossTheRoadGame;
        this.world = new World(crossTheRoadGame);
        this.worldRenderer = new WorldRenderer(crossTheRoadGame, world);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render(delta);
    }

    private void updatePlayer() {
        Chicken chicken = world.getChicken();
    }

    private void update(float delta) {
        updatePlayer();
        world.update(delta);
    }
}
