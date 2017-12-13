package com.rawit.crosstheroad.game;

public class BaseLane {

    public static final int LENGTH_COLUMN_OFFSET = 3;

    public boolean columnWall[];
    public int columnLength;
    public float length;

    public float y;
    public LaneType type;

    public BaseLane(float length, int columnLength) {
        this.length = length;
        this.columnLength = columnLength;
        columnWall = new boolean[columnLength];
    }

    public void update(float delta) {

    }

}