package com.rawit.crosstheroad.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarLane extends BaseLane {

    private static final int DISTANCE_BLOCK_MIN = 3;
    private static final int DISTANCE_BLOCK_RANDOM = 4;
    private static final int SPEED_MIN = 3;

    enum CarDirection {
        Left, Right
    }

    public List<Car> carList;
    private CarDirection direction;
    private float carSpeed;
    private float blockWidth;

    private Random random;


    public CarLane(float length, int columnLength, World world) {
        super(length, columnLength, world);
        type = LaneType.Car;
        carList = new ArrayList<Car>();
        random = new Random();
        carSpeed = random.nextInt(3) + SPEED_MIN;
        blockWidth = length / columnLength;

        direction = CarDirection.values()[random.nextInt(2)];
    }

    private void spawnCar() {
        float spawnX;
        if(direction == CarDirection.Left) {
            spawnX = length + blockWidth * (random.nextInt(DISTANCE_BLOCK_RANDOM) + DISTANCE_BLOCK_MIN) + Car.width /2;
        } else {
            spawnX = 0 - blockWidth * (random.nextInt(DISTANCE_BLOCK_RANDOM) + DISTANCE_BLOCK_MIN) - Car.width /2;
        }
        Car newCar = new Car(spawnX, y, world);
        newCar.setSpeed(carSpeed, direction);
        carList.add(newCar);
    }

    private boolean canSpawn() {
        if (carList.isEmpty()) return true;

        /* Check if last car is inside map */
        Car lastCar = carList.get(carList.size() - 1);
        if (direction == CarDirection.Left) {
            if (lastCar.x + lastCar.width /2 <= length) {
                return true;
            }
        } else {
            if (lastCar.x - lastCar.width /2 >= 0) {
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
            if (direction == CarDirection.Left) {
                if (firstCar.x + firstCar.width / 2 < 0) {
                    carList.remove(0);
                }
            } else {
                if (firstCar.x - firstCar.width / 2 > length) {
                    carList.remove(0);
                }
            }
        }

        /* Spawn car when the last car is inside */
        if (canSpawn()) {
            spawnCar();
        }

        /* Checking collision between car and player */
        Player player = world.getPlayer();
        if (player.isAlive()) {
            if (player.lane == this) {
                float playerLeft = player.x - player.width * player.COLLISION_RATIO / 2;
                float playerRight = playerLeft + player.width * player.COLLISION_RATIO;
                for (Car curCar : carList) {
                    float curCarLeft = curCar.x - curCar.width / 2;
                    float curCarRight = curCarLeft + curCar.width;
                    if ((playerLeft >= curCarLeft && playerLeft <= curCarRight) || (playerRight >= curCarLeft && playerRight <= curCarRight)) {
                        player.setLifeStatus(false);
                    }
                }
            }
        }
    }

}
