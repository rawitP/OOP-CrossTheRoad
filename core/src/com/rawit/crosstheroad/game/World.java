package com.rawit.crosstheroad.game;

import java.util.Random;

public class World {

    private static final int CAM_VIEWPORT_WIDTH = CrossTheRoadGame.WIDTH;
    private static final int CAM_VIEWPORT_HEIGHT = CrossTheRoadGame.HEIGHT;

    private static final int WORLD_WIDTH = CAM_VIEWPORT_WIDTH;
    private static final int WORLD_HEIGHT = 2 * CAM_VIEWPORT_HEIGHT;

    private static final float CAM_INIT_SPEED = 2;

    private CrossTheRoadGame crossTheRoadGame;
    private Map map;
    private Player player;
    private Camera cam;

    public int score;
    private int curRow;

    public Random random;

    public Theme theme;

    enum Theme {
        summer, winter, desert
    }
    private static final Theme[] THEME_VALUES = Theme.values();

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
        theme = Theme.summer;
        random = new Random();
        map = new Map(WORLD_WIDTH, WORLD_HEIGHT, this);
        player = new Player(1, map.COLUMN / 2, this);
        registerMoveLaneListener();

        score = 0;
        curRow = 0;

    }

    public Player getPlayer() {
        return player;
    }

    private void registerMoveLaneListener() {
        player.registerMoveLaneListener(new Player.MoveLaneListener() {
            @Override
            public void notifyMoveLane(Player.Direction dir) {
                if(dir == Player.Direction.UP) {
                    curRow += 1;
                } else {
                    curRow -= 1;
                }
                if (curRow > score) {
                    score = curRow;
                    themeControl();
                }
            }
        });
    }

    private void themeControl() {
        if(score % 25 == 0) {
            int nextThemeIndex = random.nextInt(THEME_VALUES.length);
            if(THEME_VALUES[nextThemeIndex] == theme) {
                nextThemeIndex = (nextThemeIndex + 1) % THEME_VALUES.length;
            }
            theme = THEME_VALUES[nextThemeIndex];
        }
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
            if (map.baseLaneList.size() <= map.rowCamera + 1) {
                map.addRandomLaneSet();
            }
        }
    }

    private void checkPlayer() {
        if (map.baseLaneList.indexOf(player.lane) == -1) {
            player.setLifeStatus(false);
        }
    }

    public void update(float delta) {
        cam.update(delta);
        updateMap();


        /* Check player life status */
        if (player.isAlive()) {
            checkPlayer();
        } else {
            cam.speed = 0;
        }

        player.update(delta);
        map.update(delta);
    }

}
