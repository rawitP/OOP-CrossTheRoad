package com.rawit.crosstheroad.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarLane extends BaseLane {

    private static final int DISTANCE_BLOCK_MIN = 3;
    private static final int DISTANCE_BLOCK_RANDOM = 4;

    enum carDirection {
        Left, Right
    }

    public List<Car> carList;
    private carDirection direction;
    private float carSpeed;
    private float blockWidth;

    private Random random;


    public CarLane(float length, int columnLength) {
        super(length, columnLength);
        type = LaneType.Car;
        carList = new ArrayList<Car>();
        random = new Random();
        carSpeed = random.nextInt(5);
        blockWidth = length / columnLength;

        direction = carDirection.values()[random.nextInt(2)];
    }

    private void spawnCar() {
        float spawnX;
        if(direction == carDirection.Left) {
            spawnX = length + blockWidth * (random.nextInt(DISTANCE_BLOCK_RANDOM) + DISTANCE_BLOCK_MIN) + Car.WIDTH/2;
        } else {
            spawnX = 0 - blockWidth * (random.nextInt(DISTANCE_BLOCK_RANDOM) + DISTANCE_BLOCK_MIN) - Car.WIDTH/2;
        }
        Car newCar = new Car(spawnX, y);
        newCar.setSpeed(carSpeed, direction);
        carList.add(newCar);
    }

    private boolean canSpawn() {
        if (carList.isEmpty()) return true;

        /* Check if last car is inside map */
        Car lastCar = carList.get(carList.size() - 1);
        if (direction == carDirection.Left) {
            if (lastCar.x + lastCar.WIDTH/2 <= length) {
                return true;
            }
        } else {
            if (lastCar.x - lastCar.WIDTH/2 >= 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void update(float delta) {
        for(Car curCar: carList) {
            curCar.update(delta);
        }

        /* Remove car if it outside */
        if(!carList.isEmpty()) {
            Car firstCar = carList.get(0);
            if (direction == carDirection.Left) {
                if (firstCar.x + firstCar.WIDTH / 2 < 0) {
                    carList.remove(0);
                }
            } else {
                if (firstCar.x - firstCar.WIDTH / 2 > length) {
                    carList.remove(0);
                }
            }
        }

        if (canSpawn()) {
            spawnCar();
        }
    }

}
