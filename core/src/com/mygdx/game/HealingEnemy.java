package com.mygdx.game;

/**
 * Created by Karan on 3-7-2017.
 */


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class HealingEnemy extends GameObject {
    private Handler handler;
    public int time;
    public int timeY;
    private Texture img = new Texture("healrobot.png");

    public HealingEnemy(int x, int y, ID id, Handler handler) {
        super((float)x, (float)y, id);
        this.handler = handler;
        this.velX = 9.0D;
        this.velY = 9.0D;



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

    private void collision() {
        for(int i = 0; i < this.handler.object.size(); ++i) {
            GameObject tempObject = this.handler.object.get(i);
            if(tempObject.getId() == ID.Projectile && this.getBounds().contains(tempObject.getBounds())) { //intersects = contains?
                this.handler.removeObject(tempObject);
            }
        }

    }

    public void render(SpriteBatch g) {
        if(id == ID.HealingEnemy){

            this.tick();
            g.draw(this.img, (int)this.x, (int)this.y, 52, 52);

        }

    }

    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, 52, 52);
    }
}

