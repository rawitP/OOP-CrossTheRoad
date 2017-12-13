package com.rawit.crosstheroad.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
            Texture[] tileArray = this.tileArray;
            int lastBlock = curLane.columnLength - 1;
            for (int curBlock = 0; curBlock <= lastBlock; curBlock++) {
                int textureIndex = 0;
                if(curLane.columnWall[curBlock] == true) textureIndex = 1; // If current block is wall.
                float curBlockX = curBlock * map.getBlockWidthSize();
                batch.draw(tileArray[textureIndex], curBlockX, curLane.y - blockHeight / 2, blockWidth, blockHeight);
            }
        }
        batch.end();
    }

}
