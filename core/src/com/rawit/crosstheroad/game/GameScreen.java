package com.rawit.crosstheroad.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {

    private CrossTheRoadGame crossTheRoadGame;
    World world;
    WorldRenderer worldRenderer;
    SpriteBatch batch;

    OrthographicCamera cam;
    Camera worldCam;

    public GameScreen(CrossTheRoadGame crossTheRoadGame) {
        this.crossTheRoadGame = crossTheRoadGame;
        batch = crossTheRoadGame.batch;
        this.world = new World(crossTheRoadGame);
        this.worldRenderer = new WorldRenderer(crossTheRoadGame, world);

        /* Create camera and sync it */
        worldCam = world.getCam();
        cam = new OrthographicCamera(worldCam.viewportWidth, worldCam.viewportHeight);
        cam.position.set(worldCam.x, worldCam.y, 0);
        cam.update();
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render(delta);
    }

    private void updatePlayer() {
        Player player = world.getPlayer();
        if(!player.isMoving) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                player.move(Player.Direction.LEFT);
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                player.move(Player.Direction.RIGHT);
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                player.move(Player.Direction.UP);
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                player.move(Player.Direction.DOWN);
            }
        }
    }

    private void updateCam(float delta) {
        cam.position.y = worldCam.y;
        cam.update();
        batch.setProjectionMatrix(cam.combined);
    }

    private void update(float delta) {
        updatePlayer();
        world.update(delta);
        updateCam(delta);
    }
}
