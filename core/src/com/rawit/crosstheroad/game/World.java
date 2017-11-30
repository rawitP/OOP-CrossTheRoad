package com.rawit.crosstheroad.game;

public class World {

    private static final int CAM_VIEWPORT_WIDTH = 540;
    private static final int CAM_VIEWPORT_HEIGHT = 960;

    private static final int WORLD_WIDTH = CAM_VIEWPORT_WIDTH;
    private static final int WORLD_HEIGHT = 2 * CAM_VIEWPORT_HEIGHT;

    private static final float CAM_INIT_SPEED = 1;

    private CrossTheRoadGame crossTheRoadGame;
    private Map map;
    private Chicken chicken;
    public Camera cam;

    World(CrossTheRoadGame crossTheRoadGame) {
        this.crossTheRoadGame = crossTheRoadGame;
        initWorld();
    }

    private void initWorld() {
        cam = new Camera(CAM_VIEWPORT_WIDTH, CAM_VIEWPORT_HEIGHT);
        cam.setSpeed(CAM_INIT_SPEED);
        cam.x = WORLD_WIDTH / 2.0f;
        cam.y = CAM_VIEWPORT_HEIGHT / 2.0f + 0;

        map = new Map(WORLD_WIDTH, WORLD_HEIGHT, this);
        chicken = new Chicken(0, 0, this);
    }

    public Chicken getChicken() {
        return chicken;
    }

    public Map getMap() {
        return map;
    }

    public void update(float delta) {
        cam.update(delta);
        map.update(delta);
        chicken.update(delta);
    }

}
