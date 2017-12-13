package com.rawit.crosstheroad.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

public class CarRenderer {

    private SpriteBatch batch;
    private List<BaseLane> baseLaneList;
    private Texture carImg;

    public CarRenderer(SpriteBatch batch, List<BaseLane> baseLaneList) {
        this.baseLaneList = baseLaneList;
        this.batch = batch;
        carImg = new Texture("car.png");
    }

    public void render(float delta) {
        batch.begin();
        for(BaseLane curLane: baseLaneList) {
            if(curLane.type == LaneType.Car) {
                CarLane lane = (CarLane) curLane;
                for(Car curCar: lane.carList) {
                    batch.draw(carImg, curCar.x - curCar.WIDTH/2, curCar.y - curCar.HEIGHT/2, curCar.WIDTH, curCar.HEIGHT);
                }
            }
        }
        batch.end();
    }

}
