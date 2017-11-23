package com.rawit.crosstheroad.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerRenderer {

    private SpriteBatch batch;
    private Chicken chicken;
    private Texture chickenImg;

    public PlayerRenderer(SpriteBatch batch, Chicken chicken) {
        this.chicken = chicken;
        this.batch = batch;
        chickenImg = new Texture("babyChicken.png");
    }

    public void render() {
        batch.begin();
        int[] chickenPos = chicken.getPosition();
        batch.draw(chickenImg, chickenPos[0], chickenPos[1]);
        batch.end();
    }


}
