package com.rawit.crosstheroad.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {

    private CrossTheRoadGame crossTheRoadGame;
    private World world;
    private SpriteBatch batch;
    private PlayerRenderer playerRenderer;
    private MapRenderer mapRenderer;

    public WorldRenderer(CrossTheRoadGame  crossTheRoadGame, World world) {
        this.crossTheRoadGame = crossTheRoadGame;
        batch = crossTheRoadGame.batch;
        this.world = world;
        this.playerRenderer = new PlayerRenderer(batch, world.getChicken());
        this.mapRenderer = new MapRenderer(batch, world.getMap());
    }

    public void render(float delta) {
        mapRenderer.render(delta);
        playerRenderer.render(delta);
    }

}
