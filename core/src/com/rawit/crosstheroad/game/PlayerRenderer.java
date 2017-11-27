package com.rawit.crosstheroad.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerRenderer {

    private SpriteBatch batch;
    private Chicken player;
    private Texture chickenImg;

    public PlayerRenderer(SpriteBatch batch, Chicken chicken) {
        this.player = chicken;
        this.batch = batch;
        chickenImg = new Texture("babyChicken.png");
    }

    public void render(float delta) {
        batch.begin();
        batch.draw(chickenImg, player.getX() - player.getWidth()/2, player.getY() - player.getHeight()/2, player.getWidth(), player.getHeight());
        batch.end();
    }


}
