package com.rawit.crosstheroad.game;


import java.util.ArrayList;
import java.util.List;

public class Map {

    private final static int COLUMN = 5;
    public  final static int ROW = 20;

    private World world;
    private float height;
    private float width;
    private float blockWidthSize;
    private float blockHeightSize;

    public List<BaseLane> baseLaneList = new ArrayList<>();


    public Map(int width, int height, World world) {
        this.height = height;
        this.width = width;
        this.world = world;
        blockWidthSize = width / (float) COLUMN;
        blockHeightSize = height / (float) ROW;
        initMap();
    }

    public float getBlockWidthSize() {
        return blockWidthSize;
    }

    public float getBlockHeightSize() {
        return blockHeightSize;
    }

    private void initMap() {
        for (int i = 0; i < ROW; i++) {
            addLane();
        }
    }

    private BaseLane getBaseLane() {
        return new BaseLane(width, COLUMN);
    }

    private void addLane() {
        int addRowIndex = baseLaneList.size();
        baseLaneList.add(getBaseLane());
        if (addRowIndex > 0) {
            baseLaneList.get(addRowIndex).y = baseLaneList.get(addRowIndex - 1).y + blockHeightSize;
        } else {
            baseLaneList.get(addRowIndex).y = blockHeightSize / 2;
        }
    }

    public void update(float delta) {
    }
}
