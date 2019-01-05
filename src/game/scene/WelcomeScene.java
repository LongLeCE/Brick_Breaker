package game.scene;

import game.GameObject;
import game.explosion.Particle;
import tklibs.Vector2D;

public class WelcomeScene extends Scene {
    @Override
    public void init() {
        GameObject.recycleGameObject(BackgroundWelcomeScene.class);
    }

    @Override
    public void clear() {
        GameObject.clearAll();
    }
}
