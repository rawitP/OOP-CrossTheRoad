package com.rawit.crosstheroad.game;


import java.util.ArrayList;
import java.util.List;

public class Map {

    private int height;
    private int width;
    private List<BaseLane> baseLaneList = new ArrayList<>();

    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        initMap();
    }

    private void initMap() {
        /* TODO: Create block function */
        for (int i = 0; i < 1; i++) {
            baseLaneList.add(getBaseLane());
        }
    }

    private BaseLane getBaseLane() {
        return new BaseLane(width);
    }


}
