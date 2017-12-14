package com.rawit.crosstheroad.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapRenderer {

    private SpriteBatch batch;
    private Map map;
    private Texture grassImg;
    private Texture grassWallImg;
    private Texture[] summerTile;
    private Texture winterImg;
    private Texture winterWallImg;
    private Texture[] winterTile;
    private Texture desertImg;
    private Texture desertWallImg;
    private Texture[] desertTile;
    private Texture roadTile;
    private World world;

    public MapRenderer(SpriteBatch batch, Map map) {
        this.batch = batch;
        this.map = map;
        world = map.world;

        grassImg = new Texture("grass.jpg");
        grassWallImg = new Texture("grass_wall.jpg");
        summerTile = new Texture[]{grassImg, grassWallImg};

        winterImg = new Texture("winter.jpg");
        winterWallImg = new Texture("winter_wall.jpg");
        winterTile = new Texture[]{winterImg, winterWallImg};

        desertImg = new Texture("desert.jpg");
        desertWallImg = new Texture("desert_wall.jpg");
        desertTile = new Texture[]{desertImg, desertWallImg};

        roadTile = new Texture("road.png");
    }

    public void render(float delta) {
        batch.begin();
        float blockWidth = map.getBlockWidthSize();
        float blockHeight = map.getBlockHeightSize();
        for (BaseLane curLane: map.baseLaneList) {
            if(curLane.type == LaneType.Car) {
                int lastBlock = curLane.columnLength - 1;
                for (int curBlock = 0; curBlock <= lastBlock; curBlock++) {
                    float curBlockX = curBlock * map.getBlockWidthSize();
                    batch.draw(roadTile, curBlockX, curLane.y - blockHeight / 2, blockWidth, blockHeight);
                }
            } else {
                Texture[] tileArray;
                switch (curLane.theme) {
                    case winter:
                        tileArray = winterTile;
                        break;
                    case desert:
                        tileArray = desertTile;
                        break;
                    default:
                        tileArray = summerTile;
                }
                int lastBlock = curLane.columnLength - 1;
                for (int curBlock = 0; curBlock <= lastBlock; curBlock++) {
                    int textureIndex = 0;
                    if (curLane.columnWall[curBlock] == true) textureIndex = 1; // If current block is wall.
                    float curBlockX = curBlock * map.getBlockWidthSize();
                    batch.draw(tileArray[textureIndex], curBlockX, curLane.y - blockHeight / 2, blockWidth, blockHeight);
                }
            }
        }
        batch.end();
    }

}
