package com.rawit.crosstheroad.game;

public class Camera {

    public int viewportWidth;
    public int viewportHeight;

    public float x;
    public float y;
    public float speed;

    Camera (int viewportWidth, int viewportHeight) {
        speed = 0;
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        x = 0;
        y = 0;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void update(float delta) {
        y += delta/0.016f * speed;      // Update Camera moving speed.
    }

}
