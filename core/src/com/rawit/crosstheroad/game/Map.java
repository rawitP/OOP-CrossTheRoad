package com.rawit.crosstheroad.game;


import java.util.ArrayList;
import java.util.List;

public class Map {

    public final static int COLUMN = 5;
    public  final static int ROW = 16;

    private int rowCamera;

    private World world;
    private float height;
    private float width;
    private float blockWidthSize;
    private float blockHeightSize;

    public List<BaseLane> baseLaneList = new ArrayList<BaseLane>();


    public Map(int width, int height, World world) {
        this.height = height;
        this.width = width;
        this.world = world;
        blockWidthSize = width / (float) COLUMN;
        blockHeightSize = height / (float) ROW;
        rowCamera = (int)(world.getCam().viewportHeight / blockHeightSize);
        initMap();
    }

    public float getBlockWidthSize() {
        return blockWidthSize;
    }

    public float getBlockHeightSize() {
        return blockHeightSize;
    }

    private void initMap() {
        for (int i = 0; i < rowCamera; i++) {
            addTailLane(LaneType.Flat);
        }
        for (int i = rowCamera; i < ROW; i++) {
            addTailLane(LaneType.Car);
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

    public void addTailLane(LaneType type) {
        int laneSize = baseLaneList.size();

        /* Create new lane */
        BaseLane newLane;
        if (type == LaneType.Car) {
            newLane = new CarLane(width, COLUMN);
        } else {
            newLane = new BaseLane(width, COLUMN);
        }

        /* Set lane's position */
        if (laneSize > 0) {
            newLane.y = baseLaneList.get(laneSize - 1).y + blockHeightSize;
        } else {
            newLane.y = blockHeightSize / 2;
        }

        baseLaneList.add(newLane);
    }

    public void removeHeadLane() {
        baseLaneList.remove(0);
    }

    public void update(float delta) {
        for(BaseLane curLane: baseLaneList) {
            curLane.update(delta);
        }
    }
}
