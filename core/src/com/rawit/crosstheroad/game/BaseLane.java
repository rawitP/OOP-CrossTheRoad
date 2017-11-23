package com.rawit.crosstheroad.game;

public class BaseLane {

    private int length;
    private static final int LENGTH_OFFSET = 2 * 3;

    public BaseLane(int length) {
        this.length = length + LENGTH_OFFSET; //
    }

}
