package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * HealingEnemy class which extends GameObject and has all the additional implementation
 * for the methods that are needed to have it interact with other GameObjects.
 */
public class HealingEnemy extends GameObject {
    private Handler handler;
    public int time;
    public int timeY;
    private Texture img = new Texture("healrobot.png");

    /**
     * Instantiate a new instance of HealingEnemy.
     *
     * @param x       X coordinate of the class
     * @param y       Y coordinate of the class
     * @param id      Type of GameObject
     * @param handler Instance of Handler class which loops through all GameObjects
     */
    public HealingEnemy(int x, int y, ID id, Handler handler) {
        super((float) x, (float) y, id);
        this.handler = handler;
        this.velX = 9.0D;
        this.velY = 9.0D;


    }

    /**
     * Method which updates all the data of the class
     */
    public void tick() {
        if (HUD.HEALTH > 0) {
            this.x = (float) ((double) this.x + this.velX);
            this.y = (float) ((double) this.y + this.velY);
            ++this.time;
            ++this.timeY;
            if ((double) this.time > 200.0D + Math.random() * 3800.0D) {
                this.velX *= -1.0D;
                this.time = 0;
            }

            if ((double) this.timeY > 200.0D + Math.random() * 3800.0D) {
                this.velY *= -1.0D;
                this.timeY = 0;
            }

            if (this.y <= 0.0F || this.y >= 874.0F) {
                this.velY *= -1.0D;
            }

            if (this.x <= 0.0F || this.x >= 1228.0F) {
                this.velX *= -1.0D;
            }
        }

        this.collision();
    }

    /**
     * Uses the getBounds method to check if the object is being touched by other GameObjects
     */
    private void collision() {
        for (int i = 0; i < this.handler.object.size(); ++i) {
            GameObject tempObject = this.handler.object.get(i);
            if (tempObject.getId() == ID.Projectile && this.getBounds().contains(tempObject.getBounds())) { //intersects = contains?
                this.handler.removeObject(tempObject);
            }
        }

    }

    /**
     * Draws the image of the class on the given instance of Graphics
     *
     * @param g Instance of SpriteBatch originating from the Game class
     */
    public void render(SpriteBatch g) {
        if (id == ID.HealingEnemy) {

            this.tick();
            g.draw(this.img, (int) this.x, (int) this.y, 52, 52);

        }

    }

    /**
     * Creates a hitbox that allows the program to check when it gets touched by other objects
     *
     * @return Returns a Rectangle at the position of the object that's also the size of the object
     */
    public Rectangle getBounds() {
        return new Rectangle((int) this.x, (int) this.y, 52, 52);
    }
}

