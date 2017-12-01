package com.rawit.crosstheroad.game;

import static java.lang.Math.abs;

public class Player {

    public static final float INIT_WIDTH = 75;
    public static final float INIT_HEIGHT = 75;
    public static final float MOVE_SPEED = 15;

    private World world;
    public float width;
    public float height;
    private float x,y;
    public float moveDistanceX;
    public float moveDistanceY;
    private int column;

    private float nextX, nextY;
    public boolean isMoving;

    private BaseLane lane;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public Player(int playerRow, int playerColumn, World world) {
        width = INIT_WIDTH;
        height = INIT_HEIGHT;
        this.world = world;
        this.column = playerColumn;

        /* Get move distance */
        Map map = world.getMap();
        moveDistanceX = map.getBlockWidthSize();
        moveDistanceY = map.getBlockWidthSize();

        /* Set player position */
        lane = map.getLane(playerRow);
        x = playerColumn * moveDistanceX + moveDistanceX / 2.0f;
        y = lane.y;

        /* Set next position */
        isMoving = false;
        nextX = x;
        nextY = y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void move(Direction dir) {
        switch(dir) {
            case RIGHT:
                if (canMove(dir)) {
                    column += 1;
                    setNextPos(nextX + moveDistanceX, nextY);
                }
                break;
            case LEFT:
                if (canMove(dir)) {
                    column -= 1;
                    setNextPos(nextX - moveDistanceX, nextY);
                }
                break;
            case UP:
                if (canMove(dir)) {
                    Map map = world.getMap();
                    setLane(map.getNextLane(lane));
                    setNextPos(x,lane.y);
                }
                break;
            case DOWN:
                if (canMove(dir)) {
                    Map map = world.getMap();
                    setLane(map.getPrevLane(lane));
                    setNextPos(x,lane.y);
                }
        }
    }

    private boolean canMove(Direction dir) {
        switch (dir) {
            case UP: {
                Map map = world.getMap();
                int nextLaneIndex = map.baseLaneList.indexOf(lane) + 1;
                if (nextLaneIndex > world.getMap().ROW - 1) {
                    return false;
                }
            } break;
            case DOWN: {
                Map map = world.getMap();
                int nextLaneIndex = map.baseLaneList.indexOf(lane) - 1;
                if (nextLaneIndex < 0) {
                    return false;
                }
            } break;
            case LEFT: {
                int nextColumn = column - 1;
                if (nextColumn < 0) {
                    return false;
                }
            } break;
            case RIGHT: {
                int nextColumn = column + 1;
                if (nextColumn > world.getMap().COLUMN - 1) {
                    return false;
                }
            } break;
        }
        return true;
    }

    private void setLane(BaseLane lane) {
        this.lane = lane;
    }

    private void setNextPos(float nextX, float nextY) {
        isMoving = true;
        this.nextX = nextX;
        this.nextY = nextY;
    }

    public void update(float delta) {
        Map map = world.getMap();
        if(isMoving) {

            /* Step to next Y */
            float deltaY = nextY - y;
            if(abs(deltaY) < MOVE_SPEED) {
                y = nextY;
            } else if(deltaY > 0) {
                y += MOVE_SPEED;
            } else {
                y -= MOVE_SPEED;
            }

            /* Step to next X */
            float deltaX = nextX - x;
            if(abs(deltaX) < MOVE_SPEED) {
                x = nextX;
            } else if(deltaX > 0) {
                x += MOVE_SPEED;
            } else {
                x -= MOVE_SPEED;
            }

            /* Check if it already at next Position */
            if((y == nextY) && (x == nextX)) {
                isMoving = false;
            }
        }
    }
}
