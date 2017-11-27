package com.rawit.crosstheroad.game;


import java.util.ArrayList;
import java.util.List;

public class Map {

    private final static int COLUMN = 5;
    private final static int ROW = 10;
    public  final static int ALL_ROW = ROW + 2;

    public List<BaseLane> baseLaneList = new ArrayList<>();
    private float height;
    public float width;
    private float blockWidthSize;
    private float blockHeightSize;
    private float speed = 0;
    private World world;

    public Map(int width, int height, World world) {
        this.height = height;
        this.width = width;
        this.world = world;
        blockWidthSize = width / COLUMN;
        blockHeightSize = height / (float)ROW;
        initMap();
    }

    public float getBlockWidthSize() {
        return blockWidthSize;
    }

    public float getBlockHeightSize() {
        return blockHeightSize;
    }

    private void initMap() {
        for (int i = 0; i < ALL_ROW; i++) {
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

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void update(float delta) {
        int curRow = 0;
        int lastRow = baseLaneList.size() - 1;
        for (; curRow <= lastRow; curRow++) {
            float nextY = baseLaneList.get(curRow).y - speed;
            if (nextY < 0 - blockWidthSize/2) {
                baseLaneList.remove(curRow);
                curRow -= 1;
                addLane();
                Chicken chicken = world.getChicken();
                chicken.setRow(chicken.row - 1);
            } else {
                baseLaneList.get(curRow).y = nextY;
            }
        }
    }
}
