package com.rawit.crosstheroad.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {

    private CrossTheRoadGame crossTheRoadGame;
    private World world;
    private SpriteBatch batch;
    private PlayerRenderer playerRenderer;

    public WorldRenderer(CrossTheRoadGame  crossTheRoadGame, World world) {
        this.crossTheRoadGame = crossTheRoadGame;
        batch = crossTheRoadGame.batch;
        this.world = world;
        this.playerRenderer = new PlayerRenderer(batch, world.getChicken());
    }

    public void render(float delta) {
        playerRenderer.render();
    }

}
