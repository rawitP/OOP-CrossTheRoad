package com.rawit.crosstheroad.game;


import java.util.ArrayList;
import java.util.List;

public class Map {

    public final static int COLUMN = 5;
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
            addTailLane();
        }
    }

    private BaseLane getBaseLane() {
        return new BaseLane(width, COLUMN);
    }

    public BaseLane getLane(int laneIndex) {
        return baseLaneList.get(laneIndex);
    }

    public BaseLane getNextLane(BaseLane curLane) {
        return baseLaneList.get(baseLaneList.indexOf(curLane) + 1);
    }

    public BaseLane getPrevLane(BaseLane curLane) {
        return baseLaneList.get(baseLaneList.indexOf(curLane) - 1);
    }

    public void addTailLane() {
        int addRowIndex = baseLaneList.size();
        baseLaneList.add(getBaseLane());
        if (addRowIndex > 0) {
            baseLaneList.get(addRowIndex).y = baseLaneList.get(addRowIndex - 1).y + blockHeightSize;
        } else {
            baseLaneList.get(addRowIndex).y = blockHeightSize / 2;
        }
    }

    public void removeHeadLane() {
        baseLaneList.remove(0);
    }

    public void update(float delta) {

    }
}
