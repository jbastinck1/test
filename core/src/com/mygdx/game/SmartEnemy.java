package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * SmartEnemy class which extends GameObject and has all the additional implementation
 * for the methods that are needed to have it interact with other GameObjects.
 */

public class SmartEnemy extends GameObject {
    private Handler handler;
    public int time = 0;
    public int timeY = 0;
    private GameObject player;
    private Texture img2 = new Texture("Smart_enemy.png");

    /**
     * Instantiate a new instance of projectile.
     *
     * @param x       X coordinate of the class
     * @param y       Y coordinate of the class
     * @param id      Type of GameObject
     * @param handler Instance of Handler class which loops through all GameObjects
     */
    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super((float) x, (float) y, id);
        this.handler = handler;
        this.Health = 0.0F;

        for (int i = 0; i < handler.object.size(); ++i) {
            if ((handler.object.get(i)).getId() == ID.Player) {
                this.player = handler.object.get(i);
            }
        }


    }

    /**
     * Method which updates all the data of the class
     */
    public void tick() {
        if (HUD.HEALTH > 0) {
            this.x = (float) ((double) this.x + this.velX);
            this.y = (float) ((double) this.y + this.velY);
            float diffX = this.x - this.player.getX() - 15.0F;
            float diffY = this.y - this.player.getY() - 15.0F;
            float distance = (float) Math.sqrt((double) ((this.x - this.player.getX()) * (this.x - this.player.getX()) + (this.y - this.player.getY()) * (this.y - this.player.getY())));
            this.velX = -1.0D / (double) distance * (double) diffX;
            this.velY = -1.0D / (double) distance * (double) diffY;
            ++this.time;
            ++this.timeY;
            if (this.y <= 0.0F || this.y >= 854.0F) {
                this.velY *= -1.0D;
            }

            if (this.x <= 0.0F || this.x >= 1220.0F) {
                this.velX *= -1.0D;
            }
        }

        this.collision();
    }

    /**
     * Draws the image of the class on the given instance of Graphics
     *
     * @param g Instance of Graphics originating from the Game class
     */
    public void render(SpriteBatch g) {
        if (this.id == ID.SmartEnemy) {
            System.out.println("Smart");
            this.tick();
            g.draw(this.img2, (float) ((int) this.x), (float) ((int) this.y), 60.0F, 60.0F);

        }


    }

    /**
     * Creates a hitbox that allows the program to check when it gets touched by other objects
     *
     * @return Returns a Rectangle at the position of the object that's also the size of the object
     */
    public Rectangle getBounds() {
        return new Rectangle((int) this.x, (int) this.y, 60, 60);
    }

    /**
     * Uses the getBounds method to check if the object is being touched by other GameObjects
     */
    private void collision() {
        for (int i = 0; i < this.handler.object.size(); ++i) {
            GameObject tempObject = this.handler.object.get(i);
            if (tempObject.getId() == ID.Projectile && this.getBounds().contains(tempObject.getBounds())) {  //intersects = contains ?
                this.handler.removeObject(tempObject);
            }
        }

    }
}

