package com.rawit.crosstheroad.game;

public class Car {

    public static final float WIDTH = 100;
    public static final float HEIGHT = 50;

    public float x;
    public float y;
    private float speed;

    public Car (float x, float y) {
        this.x = x;
        this.y = y;
        speed = 0;
    }

    public void setSpeed(float speed, CarLane.carDirection direction) {
        if (direction == CarLane.carDirection.Left) {
            this.speed = speed * -1;
        } else {
            this.speed = speed;
        }
    }

    public void update(float delta) {
        x += speed;
    }
}
