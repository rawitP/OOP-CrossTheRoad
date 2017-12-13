package com.rawit.crosstheroad.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {

    private CrossTheRoadGame crossTheRoadGame;
    private World world;
    private SpriteBatch batch;
    private PlayerRenderer playerRenderer;
    private MapRenderer mapRenderer;
    private CarRenderer carRenderer;

    public WorldRenderer(CrossTheRoadGame crossTheRoadGame, World world) {
        this.crossTheRoadGame = crossTheRoadGame;
        batch = crossTheRoadGame.batch;
        this.world = world;
        this.playerRenderer = new PlayerRenderer(batch, world.getPlayer());
        this.mapRenderer = new MapRenderer(batch, world.getMap());
        carRenderer = new CarRenderer(batch, world.getMap().baseLaneList);
    }

    public void render(float delta) {
        mapRenderer.render(delta);
        playerRenderer.render(delta);
        carRenderer.render(delta);
    }

}
