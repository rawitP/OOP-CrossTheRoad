package com.rawit.crosstheroad.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

public class CarRenderer {

    private SpriteBatch batch;
    private List<BaseLane> baseLaneList;

    private Texture carImgOrange;
    private Texture carImgGreen;
    private Texture carImgBlue;
    private Texture carImgPurple;
    private Texture carImgGrey;
    private Texture[] carImg;

    public CarRenderer(SpriteBatch batch, List<BaseLane> baseLaneList) {
        this.baseLaneList = baseLaneList;
        this.batch = batch;
        carImgOrange = new Texture("cars/car_orange.png");
        carImgGreen = new Texture("cars/car_green.png");
        carImgBlue = new Texture("cars/car_blue.png");
        carImgPurple = new Texture("cars/car_purple.png");
        carImgGrey = new Texture("cars/car_grey.png");
        carImg = new Texture[]{carImgOrange, carImgGreen, carImgBlue, carImgPurple, carImgGrey};
    }

    public void render(float delta) {
        batch.begin();
        for(BaseLane curLane: baseLaneList) {
            if(curLane.type == LaneType.Car) {
                CarLane lane = (CarLane) curLane;
                for(Car curCar: lane.carList) {
                    int carImgIndex;
                    switch (curCar.color) {
                        case Orange:
                            carImgIndex = 0;
                            break;
                        case Green:
                            carImgIndex = 1;
                            break;
                        case Blue:
                            carImgIndex = 2;
                            break;
                        case Purple:
                            carImgIndex = 3;
                            break;
                        case Grey:
                            carImgIndex = 4;
                            break;
                        default:
                            carImgIndex = 0;
                    }
                    batch.draw(carImg[carImgIndex], curCar.x - curCar.width /2, curCar.y - curCar.height /2, curCar.width, curCar.height);

                }
            }
        }
        batch.end();
    }

}
