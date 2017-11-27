package com.rawit.crosstheroad.game;

public class Chicken {

    private float x,y;
    private float width = 75, height = 75;
    private World world;
    public float moveDistanceX;
    public float moveDistanceY;
    public int row;
    private int column;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public Chicken(int chickenRow, int chickenColumn, World world) {
        this.world = world;
        this.row = chickenRow;
        this.column = chickenColumn;
        Map map = world.getMap();
        moveDistanceX = map.getBlockWidthSize();
        moveDistanceY = map.getBlockWidthSize();
        this.x = chickenColumn * moveDistanceX + moveDistanceX / 2.0f;
        this.y = map.baseLaneList.get(row).y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setRow(int row) {
        this.row = row;
        y = world.getMap().baseLaneList.get(row).y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void move(Direction dir) {
        switch(dir) {
            case RIGHT:
                if (canMove(dir)) {
                    column += 1;
                    x += moveDistanceX;
                }
                break;
            case LEFT:
                if (canMove(dir)) {
                    column -= 1;
                    x -= moveDistanceX;
                }
                break;
            case UP:
                if (canMove(dir)) {
                    row += 1;
                    y = world.getMap().baseLaneList.get(row).y;
                }
                break;
            case DOWN:
                if (canMove(dir)) {
                    row -= 1;
                    y = world.getMap().baseLaneList.get(row).y;
                }
        }
    }

    private boolean canMove(Direction dir) {
        switch (dir) {
            case UP: {
                int nextRow = row + 1;
                if (nextRow > world.getMap().baseLaneList.size() - 1) {
                    return false;
                }
            } break;
            case DOWN: {
                int nextRow = row - 1;
                if (nextRow < 0) {
                    return false;
                }
            } break;
        }
        return true;
    }

    public void setMoveDistance(float blockWidthSize, float blockHeightSize) {
        moveDistanceX = blockWidthSize;
        moveDistanceY = blockHeightSize;
    }

    public void update(float delta) {
        Map map = world.getMap();
        y = map.baseLaneList.get(row).y;
    }
}
