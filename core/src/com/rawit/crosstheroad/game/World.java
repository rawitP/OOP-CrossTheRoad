package com.rawit.crosstheroad.game;

public class World {

    private static final int CAM_VIEWPORT_WIDTH = 540;
    private static final int CAM_VIEWPORT_HEIGHT = 960;

    private static final int WORLD_WIDTH = CAM_VIEWPORT_WIDTH;
    private static final int WORLD_HEIGHT = 2 * CAM_VIEWPORT_HEIGHT;

    private static final float CAM_INIT_SPEED = 1;

    private CrossTheRoadGame crossTheRoadGame;
    private Map map;
    private Player player;
    private Camera cam;

    World(CrossTheRoadGame crossTheRoadGame) {
        this.crossTheRoadGame = crossTheRoadGame;
        initWorld();
    }

    private void initWorld() {

        /* Setup Camera */
        cam = new Camera(CAM_VIEWPORT_WIDTH, CAM_VIEWPORT_HEIGHT);
        cam.setSpeed(CAM_INIT_SPEED);
        cam.x = WORLD_WIDTH / 2.0f;
        cam.y = CAM_VIEWPORT_HEIGHT / 2.0f + 0;

        /* Setup Game Object */
        map = new Map(WORLD_WIDTH, WORLD_HEIGHT, this);
        player = new Player(2, 2, this);
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }

    public Camera getCam() {
        return cam;
    }

    private void updateMap() {

        /* Remove bottom lane if it outside of camera view */
        float camBottom = cam.y - cam.viewportHeight / 2.0f;
        float headLaneTop = map.baseLaneList.get(0).y + map.getBlockHeightSize() / 2;
        if(camBottom > headLaneTop) {
            map.removeHeadLane();
            map.addTailLane(LaneType.Car);
        }
    }

    public void update(float delta) {
        cam.update(delta);
        updateMap();
        player.update(delta);
        map.update(delta);
    }

}
