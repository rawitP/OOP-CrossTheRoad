package com.rawit.crosstheroad.game;

public class Car {

    private static final float WIDTH_RATIO = 1.9f;
    private static final float HEIGHT_RATIO = 0.9f;
    public static float width;
    public static float height;

    public float x;
    public float y;
    private float speed;

    private World world;

    enum Color {
        Orange, Green, Blue, Purple, Grey;
    }
    private static final Color[] COLOR_VALUE = Color.values();

    public Color color;


    public Car (float x, float y, World world) {
        this.x = x;
        this.y = y;
        speed = 0;
        this.world = world;
        Map map = world.getMap();
        width = map.getBlockWidthSize() * WIDTH_RATIO;
        height = map.getBlockHeightSize() * HEIGHT_RATIO;
        color = COLOR_VALUE[world.random.nextInt(COLOR_VALUE.length)];
    }

    public void setSpeed(float speed, CarLane.CarDirection direction) {
        if (direction == CarLane.CarDirection.Left) {
            this.speed = speed * -1;
        } else {
            this.speed = speed;
        }
    }

    public void update(float delta) {
        x += speed;
    }
}
