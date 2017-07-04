package com.mygdx.game;

/**
 * Created by Karan on 3-7-2017.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

//import java.awt.Rectangle;
//import java.awt.image.ImageObserver;


/**
 * Created by Karan on 3-7-2017.
 */

public class BasicEnemy extends GameObject {
    private Handler handler;
    public int time = 0;
    public int timeY = 0;
    private Texture img3 = new Texture("enemyrobot.png");

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super((float)x, (float)y, id);
        this.handler = handler;
        this.velX = 9.0D;
        this.velY = 9.0D;}





    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, 52, 52);
    }

    public void tick() {
        if(HUD.HEALTH > 0) {
            this.x = (float)((double)this.x + this.velX);
            this.y = (float)((double)this.y + this.velY);
            ++this.time;
            ++this.timeY;
            if((double)this.time > 200.0D + Math.random() * 3800.0D) {
                this.velX *= -1.0D;
                this.time = 0;
            }

            if((double)this.timeY > 200.0D + Math.random() * 3800.0D) {
                this.velY *= -1.0D;
                this.timeY = 0;
            }

            if(this.y <= 0.0F || this.y >= 874.0F) {
                this.velY *= -1.0D;
            }

            if(this.x <= 0.0F || this.x >= 1228.0F) {
                this.velX *= -1.0D;
            }
        }

        this.collision();
    }

    public void render(SpriteBatch g) {
        if(id == ID.BasicEnemy){

            this.tick();
            g.draw(this.img3, (int)this.x, (int)this.y, 52, 52);

        }



    }

    private void collision() {
        for(int i = 0; i < this.handler.object.size(); ++i) {
            GameObject tempObject = this.handler.object.get(i);
            if(tempObject.getId() == ID.Projectile && this.getBounds().contains(tempObject.getBounds())) { //Intersects = contains?
                this.handler.removeObject(tempObject);
            }
        }

    }
}

