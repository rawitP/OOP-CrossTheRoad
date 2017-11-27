package com.rawit.crosstheroad.game;

public class BaseLane {

    public int lengthColumn;
    private float length;
    public static final int LENGTH_COLUMN_OFFSET = 2 * 3;
    public float y;
    public LaneType type;

    public BaseLane(float length, int lengthDisplayColumn) {
        this.length = length + (length / (float)lengthDisplayColumn * LENGTH_COLUMN_OFFSET);
        this.lengthColumn = lengthDisplayColumn + LENGTH_COLUMN_OFFSET;
    }

    public void setType(LaneType type) {
        this.type = type;
    }

    public void setY(float y) {
        this.y = y;
    }

}
