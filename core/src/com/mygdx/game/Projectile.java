package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.io.PrintStream;


/**
 * HealingEnemy class which extends GameObject and has all the additional implementation
 * for the methods that are needed to have it interact with other GameObjects.
 */
public class Projectile extends GameObject {
    private Handler handler;
    private float velX;
    private float velY;
    Spawn p;

    /**
     * Instantiate a new instance of projectile.
     *
     * @param x       X coordinate of the class
     * @param y       Y coordinate of the class
     * @param id      Type of GameObject
     * @param handler Instance of Handler class which loops through all GameObjects
     * @param velX    Horizontal velocity of the class
     * @param velY    Vertical velocity of the class
     */
    public Projectile(float x, float y, ID id, Handler handler, float velX, float velY) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
    }

    /**
     * Creates a hitbox that allows the program to check when it gets touched by other objects
     *
     * @return Returns a Rectangle at the position of the object that's also the size of the object
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    /**
     * Method which updates all the data of the class
     */
    public void tick() {
        x += velX;
        y += velY;
        if (y <= 0 || y >= 1080)
            handler.removeObject(this);
        if (x <= 0 || x >= 1980)
            handler.removeObject(this);
        collision();
    }

    /**
     * Draws the image of the class on the given instance of Graphics
     *
     * @param g Instance of SpriteBatch originating from the Game class
     */
    public void render(SpriteBatch g) {
        tick();
        g.end();
        ShapeRenderer sr = new ShapeRenderer();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.ORANGE);
        sr.rect((int) x, (int) y, 16, 16);
        sr.end();
        g.begin();
    }

    /**
     * Uses the getBounds method to check if the object is being touched by other GameObjects
     */
    private void collision() {
        for (int i = 0; i < this.handler.object.size(); ++i) {
            GameObject tempObject = (GameObject) this.handler.object.get(i);
            Spawn var10000;
            StringBuilder var10001;
            Spawn var10002;
            PrintStream var3;
            if (tempObject.getId() == ID.BasicEnemy && this.getBounds().overlaps(tempObject.getBounds())) {
                if (tempObject.Health == 2.0F) {
                    System.out.println("enemy has been damaged 3 times!");
                    this.handler.removeObject(tempObject);
                    var10000 = this.p;
                    Spawn.scoreKeep += 50;
                    var3 = System.out;
                    var10001 = (new StringBuilder()).append("ScoreKeep");
                    var10002 = this.p;
                    var3.println(var10001.append(Spawn.scoreKeep).toString());
                } else {
                    ++tempObject.Health;
                    System.out.println(" Enemy has been Damaged");
                }
            }

            if (tempObject.getId() == ID.MineEnemy) {
                if (this.getBounds().overlaps(tempObject.getBounds())) {
                    if (tempObject.Health == 0.0F) {
                        System.out.println("Mine has been destroyed!");
                        this.handler.removeObject(tempObject);
                        var10000 = this.p;
                        Spawn.scoreKeep += 10;
                    } else {
                        tempObject.Health = 0.0F;
                        System.out.println("Not possible...");
                    }
                }
            } else if (tempObject.getId() == ID.HealingEnemy) {
                if (this.getBounds().overlaps(tempObject.getBounds()) && tempObject.Health == 0.0F) {
                    System.out.println("Healingenemy has been killed!");
                    this.handler.removeObject(tempObject);
                    HUD.HEALTH += 10;
                }
            } else if (tempObject.getId() == ID.SmartEnemy && this.getBounds().overlaps(tempObject.getBounds())) {
                if (tempObject.Health == 4.0F) {
                    System.out.println("enemy has been damaged 4 times!");
                    this.handler.removeObject(tempObject);
                    var10000 = this.p;
                    Spawn.scoreKeep += 60;
                    var3 = System.out;
                    var10001 = (new StringBuilder()).append("Scorekeep");
                    var10002 = this.p;
                    var3.println(var10001.append(Spawn.scoreKeep).toString());
                } else {
                    ++tempObject.Health;
                    System.out.println(" Enemy has been Damaged");
                }
            }
        }

    }
}