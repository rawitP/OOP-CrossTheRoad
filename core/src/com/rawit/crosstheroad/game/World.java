package com.rawit.crosstheroad.game;

public class World {

    private CrossTheRoadGame crossTheRoadGame;
    private Map map;
    private Chicken chicken;

    World(CrossTheRoadGame crossTheRoadGame) {
        this.crossTheRoadGame = crossTheRoadGame;
        initWorld();
    }

    private void initWorld() {
        map = new Map(CrossTheRoadGame.WIDTH, CrossTheRoadGame.HEIGHT, this);
        map.setSpeed(1);
        chicken = new Chicken(0, 0, this);
    }

    public Chicken getChicken() {
        return chicken;
    }

    public Map getMap() {
        return map;
    }

    public void update(float delta) {
        map.update(delta);
        chicken.update(delta);
    }

}
