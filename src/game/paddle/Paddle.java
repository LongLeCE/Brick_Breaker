package game.paddle;

import game.*;
import game.ball.Ball;
import game.brick.AbilityBrickType4;
import game.physics.BoxCollider;
import game.physics.Physics;
import game.renderer.SingleimageRenderer;
import game.scene.GameOverScene;
import game.scene.Scene;
import tklibs.SpriteUtils;

public class Paddle extends GameObject implements Physics {

    BoxCollider boxCollider;


    public Paddle() {
        // game.paddle.paddle.playerName; // co the goi y het ben canvas
        super();

        this.createRenderer();
        this.position.set(400, 570);
        this.anchor.set(0f,0f);
        this.createBoxCollider();
        GameObject.midLayer.add(this);
    }

    private void createRenderer() {
        this.renderer = new SingleimageRenderer(SpriteUtils.loadImage("assets/images/paddle/0.png"));

    }

    @Override
    public void run() {
        super.run();
        this.move();
        this.hitAb();
        this.limitPaddlePosition();
    }
    //TODO continue editting

    public void hitAb() {
        AbilityBrickType4 ab4 = GameObject.findIntercepts(AbilityBrickType4.class, this.getBoxCollider());
        if (ab4 != null){
            ab4.destroy();
            ab4.position.set(0, 0);
            int currentSize = GameObject.gameObjects.size();
            for (int i = 0; i < currentSize; i++) {
                GameObject gameObject = GameObject.gameObjects.get(i);
                if (gameObject.active && gameObject instanceof Ball) {
                    for (int j = 0; j < 2; j++) {
                        GameObject.recycleGameObject(gameObject.getClass()).position.set(gameObject.position);
                        float angle = gameObject.velocity.getAngle();
                        GameObject.gameObjects.get(currentSize + j).velocity.setAngle((float)(angle + (2 * j - 1) * Math.PI / 9));
                    }
                }
            }
        }
    }

    private void move() {

        this.velocity.set(0, 0);

        if (GameWindow.isLeftPress) {
            this.velocity.addThis(Setting.PADDLE_VECLOCITY_LEFT);
        }
        if (GameWindow.isRightPress) {
            this.velocity.addThis(Setting.PADDLE_VECLOCITY_RIGHT);
        }
        this.velocity.setLength(Setting.PADDLE_VECLOCITY_DOWN.y);
    }

    private void limitPaddlePosition() {
        if (this.position.x < 0) {
            this.position.set(0, this.position.y);
        }
        if (this.position.x > 800 - 150) {
            this.position.set(800 - 150, this.position.y);
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        this.createBoxCollider();
        return this.boxCollider;
    }

    private void createBoxCollider() {
        this.boxCollider = new BoxCollider(this, this.renderer.getCurrentImageSize(), Float.POSITIVE_INFINITY);
    }

    @Override
    public void destroy() {
        super.destroy();
        Scene.signNewScene(new GameOverScene());
    }

}

//Gioi han di chuyen cho player
//Vector 2D: diem(x, y) vector (x, y)

// Polymorphisim
// override and overload

// Dong giooi ke thua da hinh
// BTVN
// Ban nhieu vien (nhieu huong)
// Ban nhieu loai doan
// Nang cap duong dan

// Setting