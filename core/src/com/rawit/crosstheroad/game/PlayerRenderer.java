package com.rawit.crosstheroad.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerRenderer {

    private SpriteBatch batch;
    private Player player;

    private Texture chickenUpImg;
    private Texture chickenRightImg;
    private Texture chickenDownImg;
    private Texture chickenLeftImg;
    private Texture chickenDeadImg;


    public PlayerRenderer(SpriteBatch batch, Player player) {
        this.player = player;
        this.batch = batch;
        chickenUpImg = new Texture("player/chicken_up.png");
        chickenRightImg = new Texture("player/chicken_right.png");
        chickenDownImg = new Texture("player/chicken_down.png");
        chickenLeftImg = new Texture("player/chicken_left.png");
        chickenDeadImg = new Texture("player/chicken_dead.png");
    }

    public void render(float delta) {
        batch.begin();
        if (player.isAlive()) {
            Texture chickenCurDirectionImg = chickenUpImg;
            switch (player.curDirection) {
                case UP:
                    chickenCurDirectionImg = chickenUpImg;
                    break;
                case RIGHT:
                    chickenCurDirectionImg = chickenRightImg;
                    break;
                case DOWN:
                    chickenCurDirectionImg = chickenDownImg;
                    break;
                case LEFT:
                    chickenCurDirectionImg = chickenLeftImg;
            }
            batch.draw(chickenCurDirectionImg, player.x - player.width/2, player.y - player.height/2, player.width, player.height);
        } else {
            batch.draw(chickenDeadImg, player.x - player.width/2, player.y - player.height/2, player.width, player.height);
        }
        batch.end();
    }

}
