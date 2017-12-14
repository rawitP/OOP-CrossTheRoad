package com.rawit.crosstheroad.game;


import java.util.ArrayList;
import java.util.List;

public class Map {

    public final static int COLUMN = 15;
    public  final static int ROW = 16;

    public int rowCamera;

    public World world;
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

    enum LaneSet {
        Flat, RandomWall, Road, Highway
    }
    private static final LaneSet[] LANE_SET_VALUES = LaneSet.values();

    private void addLaneSet(LaneSet set) {

        switch(set) {
            case Flat:
                for (int i = 0; i < 2; i++) {
                    addTailLane(LaneType.Flat);
                }
                System.out.println("Lane set: Flat");
                break;
            case RandomWall:
                for (int i = 0; i < 2; i++) {
                    addTailLane(LaneType.RandomWall);
                }
            case Road:
                int randomRoadLane = world.random.nextInt(3) + 1;
                for (int i = 0; i < randomRoadLane; i++) {
                    addTailLane(LaneType.Car);
                }
                addTailLane(LaneType.RandomWall);
                System.out.println("Lane set: Road");
                break;
            case Highway:
                for (int i = 0; i < 5; i++) {
                    addTailLane(LaneType.Car);
                }
                addTailLane(LaneType.RandomWall);
                System.out.println("Lane set: Highway");
                break;
        }
    }

    public float getBlockWidthSize() {
        return blockWidthSize;
    }

    public float getBlockHeightSize() {
        return blockHeightSize;
    }

    private void initMap() {
        addLaneSet(LaneSet.Flat);

        while(baseLaneList.size() < ROW) {
            addRandomLaneSet();
        }
    }

    private BaseLane getBaseLane() {
        return new BaseLane(width, COLUMN, world);
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
        switch (type) {
            case RandomWall:
                newLane = new BaseLane(width, COLUMN, world);
                newLane.randomWall();
                break;
            case Car:
                newLane = new CarLane(width, COLUMN, world);
                break;
            default:
                newLane = new BaseLane(width, COLUMN, world);
        }

        /* Set lane's position */
        if (laneSize > 0) {
            newLane.y = baseLaneList.get(laneSize - 1).y + blockHeightSize;
        } else {
            newLane.y = blockHeightSize / 2;
        }

        baseLaneList.add(newLane);
    }

    public void addRandomLaneSet() {
        addLaneSet(LANE_SET_VALUES[world.random.nextInt(LANE_SET_VALUES.length - 1) + 1]);
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
