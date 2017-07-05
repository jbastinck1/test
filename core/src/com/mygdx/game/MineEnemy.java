package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


/**
 * MineEnemy class which extends GameObject and has all the additional implementation
 * for the methods that are needed to have it interact with other GameObjects.
 */
public class MineEnemy extends GameObject {
    private Handler handler;
    private Texture img = new Texture("mine.png");

    /**
     * Instantiate a new instance of MineEnemy.
     *
     * @param x       X coordinate of the class
     * @param y       Y coordinate of the class
     * @param id      Type of GameObject
     * @param handler Instance of Handler class which loops through all GameObjects
     */
    public MineEnemy(int x, int y, ID id, Handler handler) {
        super((float) x, (float) y, id);
        this.handler = handler;
        this.Health = 0.0F;
        this.velX = 0.0D;
        this.velY = 0.0D;


    }

    /**
     * Creates a hitbox that allows the program to check when it gets touched by other objects
     *
     * @return Returns a Rectangle at the position of the object that's also the size of the object
     */
    public Rectangle getBounds() {
        return new Rectangle((int) this.x, (int) this.y, 52, 52);
    }

    /**
     * Method which updates all the data of the class
     */
    public void tick() {
        this.collision();
    }

    /**
     * Draws the image of the class on the given instance of Graphics
     *
     * @param g Instance of SpriteBatch originating from the Game class
     */
    public void render(SpriteBatch g) {
        if (id == ID.MineEnemy) {
            ShapeRenderer sr = new ShapeRenderer();
            sr.setColor(Color.BLUE);
            this.tick();
            g.draw(this.img, (int) this.x, (int) this.y, 52, 52);

        }

    }

    /**
     * Uses the getBounds method to check if the object is being touched by other GameObjects
     */
    private void collision() {
        for (int i = 0; i < this.handler.object.size(); ++i) {
            GameObject tempObject = this.handler.object.get(i);
            if (tempObject.getId() == ID.Projectile && this.getBounds().contains(tempObject.getBounds())) { // intersects = contains?
                this.handler.removeObject(tempObject);
            }
        }

    }
}
