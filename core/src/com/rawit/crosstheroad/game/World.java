package com.rawit.crosstheroad.game;

public class World {

    private static final int BLOCK_SIZE = 100;

    private CrossTheRoadGame crossTheRoadGame;
    private Map map;
    private Chicken chicken;

    World(CrossTheRoadGame crossTheRoadGame) {
        this.crossTheRoadGame = crossTheRoadGame;
        map = new Map(CrossTheRoadGame.WIDTH, CrossTheRoadGame.HEIGHT);
        chicken = new Chicken(360, 640, this);
        initWorld();
    }

    private void initWorld() {
        // TODO
    }

    public Chicken getChicken() {
        return chicken;
    }

    public void update(float delta) {

    }

}
