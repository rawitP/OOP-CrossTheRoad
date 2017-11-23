package com.rawit.crosstheroad.game;

public class Chicken {

    private int[] pos;
    private World world;

    public Chicken(int x, int y, World world) {
        this.world = world;
        pos = new int[]{x,y};
    }

    public void setPosition(int x, int y) {
        pos[0] = x;
        pos[1] = y;
    }

    public int[] getPosition() {
        return pos;
    }

}
