package com.rawit.crosstheroad.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class MapRenderer {

    private SpriteBatch batch;
    private Map map;
    private Texture grassImg;
    private Texture stoneImg;
    private Texture[] tileArray;

    public MapRenderer(SpriteBatch batch, Map map) {
        this.batch = batch;
        this.map = map;
        grassImg = new Texture("grass.jpg");
        stoneImg = new Texture("stone.jpg");
        tileArray = new Texture[]{grassImg, stoneImg};
    }

    public void render(float delta) {
        batch.begin();
        float blockWidth = map.getBlockWidthSize();
        float blockHeight = map.getBlockHeightSize();
        for (BaseLane curLane: map.baseLaneList) {
            Texture texture = tileArray[1];
            int lastBlock = curLane.lengthColumn - 1;
            for (int curBlock = 0; curBlock <= lastBlock; curBlock++) {
                float curBlockX = curBlock * map.getBlockWidthSize();
                batch.draw(texture, curBlockX, curLane.y - blockHeight / 2, blockWidth, blockHeight);
            }
        }
        batch.end();
    }

}
