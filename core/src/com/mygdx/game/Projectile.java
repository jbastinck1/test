package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


/**
 * Created by Steven on 16-Jun-17.
 */
public class Projectile extends GameObject {
    private Handler handler;
    private float velX;
    private float velY;
//    Spawn p;

    public Projectile(float x, float y, ID id, Handler handler, float velX, float velY) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;
        if (y <= 0 || y >= 1080)
            handler.removeObject(this);
        if (x <= 0 || x >= 1980)
            handler.removeObject(this);
        collision();
    }

    public void render(SpriteBatch g) {
        tick();
        g.end();
        ShapeRenderer sr = new ShapeRenderer();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.ORANGE);
        sr.rect((int)x, (int)y,16,16);
        sr.end();
        g.begin();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy) {
                if (getBounds().contains(tempObject.getBounds())) {
                    if (tempObject.Health == 2) {
                        System.out.println("enemy has been damaged 3 times!");
                        handler.removeObject(tempObject);
//                        p.scoreKeep += 50;
                        System.out.println("ScoreKeep");// + p.scoreKeep




                    } else {
                        tempObject.Health += 1;
                        System.out.println(" Enemy has been Damaged");
                    }

                }
            } if (tempObject.getId() == ID.MineEnemy) {
                if (getBounds().contains(tempObject.getBounds())) {
                    if (tempObject.Health == 0) {
                        System.out.println("Mine has been destroyed!");
                        handler.removeObject(tempObject);
//                        p.scoreKeep += 10;

                    } else {
                        tempObject.Health = 0;
                        System.out.println("Not possible...");
                    }

                }
            }
            else if (tempObject.getId() == ID.HealingEnemy) {
                if (getBounds().contains(tempObject.getBounds())) {
                    if (tempObject.Health == 0) {
                        System.out.println("Healingenemy has been killed!");
                        handler.removeObject(tempObject);
                        HUD.HEALTH += 10;
                    }

				}
			}

                else if (tempObject.getId() == ID.SmartEnemy) {
                    if (getBounds().contains(tempObject.getBounds())) {
                        if (tempObject.Health == 4) {
                            System.out.println("enemy has been damaged 4 times!");
                            handler.removeObject(tempObject);
//                            p.scoreKeep += 60;
                            System.out.println("Scorekeep" );//+ p.scoreKeep
                        }
                        else {
                            tempObject.Health += 1;
                            System.out.println(" Enemy has been Damaged");
                        }


                    }

                }
            }
        }
    }

