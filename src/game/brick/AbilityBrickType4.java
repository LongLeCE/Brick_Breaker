package game.brick;

import game.GameObject;
import game.ball.Ball;
import game.physics.BoxCollider;
import game.renderer.SingleimageRenderer;
import tklibs.SpriteUtils;

public class AbilityBrickType4 extends AbilityBrick {

    public AbilityBrickType4() {
        this.position.set(400, 400);
        this.velocity.setY(10);
        GameObject.midLayer.add(this);
    }

    @Override
    public void createBoxCollider() {
        this.boxCollider = new BoxCollider(this);
    }

    @Override
    public void createRenderer() {
        this.renderer = new SingleimageRenderer(SpriteUtils.loadImage("assets/images/Backup/powerup/0.png"));
    }

    @Override
    public void triggerSpecialEffectWhenHit() {
        this.position.set(0, 0);
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
