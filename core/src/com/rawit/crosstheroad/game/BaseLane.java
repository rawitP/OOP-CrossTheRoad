package com.rawit.crosstheroad.game;

public class BaseLane {

    public static final int LENGTH_COLUMN_OFFSET = 3;
    private static final float WALL_MAX_RATIO = 0.5f;

    public boolean columnWall[];
    private int wallMax;
    public int columnLength;
    public float length;

    public float y;
    public LaneType type;

    public World world;
    public World.Theme theme;

    public BaseLane(float length, int columnLength, World world) {
        this.length = length;
        this.columnLength = columnLength;
        columnWall = new boolean[columnLength];
        this.world = world;
        wallMax = (int)(columnLength * WALL_MAX_RATIO);
        theme = world.theme;
    }

    public void randomWall() {
        int wallAmount = 0;
        for(int wallIndex = 0; wallIndex < columnLength; wallIndex++) {
            if(wallAmount < wallMax) {
                if(world.random.nextInt(2) == 1) {
                    wallAmount++;
                    columnWall[wallIndex] = true;
                }
            } else {
                break;
            }
        }
    }

    public void update(float delta) {

    }

}