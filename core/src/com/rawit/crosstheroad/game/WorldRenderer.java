package com.rawit.crosstheroad.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {

    private static final float FONT_SCORE_SCALE = 2;

    private CrossTheRoadGame crossTheRoadGame;
    private World world;
    private SpriteBatch batch;
    private PlayerRenderer playerRenderer;
    private MapRenderer mapRenderer;
    private CarRenderer carRenderer;
    private BitmapFont font;

    public WorldRenderer(CrossTheRoadGame crossTheRoadGame, World world) {
        this.crossTheRoadGame = crossTheRoadGame;
        batch = crossTheRoadGame.batch;
        this.world = world;
        this.playerRenderer = new PlayerRenderer(batch, world.getPlayer());
        this.mapRenderer = new MapRenderer(batch, world.getMap());
        carRenderer = new CarRenderer(batch, world.getMap().baseLaneList);
        font = new BitmapFont();
        font.setColor(0,0,1,1);
        font.getData().setScale(FONT_SCORE_SCALE);
    }

    public void render(float delta) {
        mapRenderer.render(delta);
        playerRenderer.render(delta);
        carRenderer.render(delta);

        /* Drawing text */
        Camera camera = world.getCam();
        batch.begin();
        font.draw(batch, "Score: " + world.score, 0, camera.y + camera.viewportHeight / 2);
        batch.end();
    }

}
