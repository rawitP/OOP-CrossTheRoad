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

    private Texture getRandomTexture() {
        Random random = new Random();
        int randomIndex = random.nextInt(tileArray.length);
        return tileArray[randomIndex];
    }

    public void render(float delta) {
        batch.begin();
        int allRow = Map.ROW;
        for (BaseLane curLane: map.baseLaneList) {
            Texture texture = tileArray[1];
            int curBlock = curLane.LENGTH_COLUMN_OFFSET/2;
            int lastBlock = curLane.lengthColumn - curLane.LENGTH_COLUMN_OFFSET/2 - 1;
            for ( ; curBlock <= lastBlock; curBlock++) {
                int displayColumn = curBlock - curLane.LENGTH_COLUMN_OFFSET/2;
                batch.draw(texture, displayColumn * map.getBlockWidthSize(), curLane.y - map.getBlockHeightSize() / 2, map.getBlockWidthSize(), map.getBlockHeightSize());
            }
            batch.draw(texture, 0, curLane.y - map.getBlockHeightSize() / 2, map.getBlockWidthSize(), map.getBlockHeightSize());
        }
        batch.end();
    }

}
