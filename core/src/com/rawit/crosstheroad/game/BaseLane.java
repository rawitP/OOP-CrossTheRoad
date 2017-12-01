package com.rawit.crosstheroad.game;

public class BaseLane {

    public int lengthColumn;
    private float length;
    public static final int LENGTH_COLUMN_OFFSET = 3;
    public float y;
    public LaneType type;

    public BaseLane(float length, int lengthDisplayColumn) {
        this.length = length;
        this.lengthColumn = lengthDisplayColumn;
    }

    public void setType(LaneType type) {
        this.type = type;
    }

    public void setY(float y) {
        this.y = y;
    }

}
