package com.rawit.crosstheroad.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerRenderer {

    private SpriteBatch batch;
    private Player player;
    private Texture chickenImg;

    public PlayerRenderer(SpriteBatch batch, Player player) {
        this.player = player;
        this.batch = batch;
        chickenImg = new Texture("babyChicken.png");
    }

    public void render(float delta) {
        batch.begin();
        batch.draw(chickenImg, player.x - player.width/2, player.y - player.height/2, player.width, player.height);
        batch.end();
    }

}
