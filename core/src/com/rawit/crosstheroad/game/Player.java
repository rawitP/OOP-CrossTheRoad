package com.rawit.crosstheroad.game;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Player {

    public static final float WIDTH_RATIO = 0.75f;
    public static final float HEIGHT_RATIO = 0.75f;
    public static final float MOVE_SPEED = 15;
    public static final float COLLISION_RATIO = 0.75f;

    private boolean lifeStatus;

    private World world;
    public float width;
    public float height;
    public float x;
    public float y;
    public float blockWidth;
    public float blockHeight;
    private int column;

    private float nextX, nextY;
    public boolean isMoving;
    private List<MoveLaneListener> listeners;

    public BaseLane lane;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public Player(int playerRow, int playerColumn, World world) {
        this.world = world;
        this.column = playerColumn;
        lifeStatus = true;

        /* Get move distance */
        Map map = world.getMap();
        blockWidth = map.getBlockWidthSize();
        blockHeight = map.getBlockHeightSize();
        width = blockWidth * WIDTH_RATIO;
        height = blockHeight * HEIGHT_RATIO;

        /* Set player position */
        lane = map.getLane(playerRow);
        x = playerColumn * blockWidth + blockWidth / 2.0f;
        y = lane.y;

        /* Set next position */
        isMoving = false;
        nextX = x;
        nextY = y;

        listeners = new ArrayList<MoveLaneListener>();
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(Direction dir) {
        if(!isAlive()) return; // Do not move if it dead.

        switch(dir) {
            case RIGHT:
                if (canMove(dir)) {
                    column += 1;
                    setNextPos(nextX + blockWidth, nextY);
                }
                break;
            case LEFT:
                if (canMove(dir)) {
                    column -= 1;
                    setNextPos(nextX - blockWidth, nextY);
                }
                break;
            case UP:
                if (canMove(dir)) {
                    Map map = world.getMap();
                    setLane(map.getNextLane(lane));
                    setNextPos(x,lane.y);
                    notifyMoveLaneListeners(dir);
                }
                break;
            case DOWN:
                if (canMove(dir)) {
                    Map map = world.getMap();
                    setLane(map.getPrevLane(lane));
                    setNextPos(x,lane.y);
                    notifyMoveLaneListeners(dir);
                }
        }
    }

    private boolean canMove(Direction dir) {
        switch (dir) {
            case UP: {
                Map map = world.getMap();
                int nextLaneIndex = map.baseLaneList.indexOf(lane) + 1;
                if (nextLaneIndex > world.getMap().baseLaneList.size() - 1) {
                    return false;
                }
                if(world.getMap().baseLaneList.get(nextLaneIndex).columnWall[column] == true) {
                    return false;
                }
            } break;
            case DOWN: {
                Map map = world.getMap();
                int nextLaneIndex = map.baseLaneList.indexOf(lane) - 1;
                if (nextLaneIndex < 0) {
                    return false;
                }
                if(world.getMap().baseLaneList.get(nextLaneIndex).columnWall[column] == true) {
                    return false;
                }
            } break;
            case LEFT: {
                int nextColumn = column - 1;
                if (nextColumn < 0) {
                    return false;
                }
                if(lane.columnWall[nextColumn] == true) {
                    return false;
                }
            } break;
            case RIGHT: {
                int nextColumn = column + 1;
                if (nextColumn > world.getMap().COLUMN - 1) {
                    return false;
                }
                if(lane.columnWall[nextColumn] == true) {
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

    public void setLifeStatus(boolean value) {
        lifeStatus = value;
    }

    public boolean isAlive() {
        return lifeStatus;
    }

    public interface MoveLaneListener {
        void notifyMoveLane(Direction dir);
    }

    public void registerMoveLaneListener(MoveLaneListener l) {
        listeners.add(l);
    }

    private void notifyMoveLaneListeners(Direction dir) {
        for(MoveLaneListener l : listeners) {
            l.notifyMoveLane(dir);
        }
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
