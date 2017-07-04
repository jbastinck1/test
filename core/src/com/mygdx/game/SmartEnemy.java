package com.mygdx.game;

/**
 * Created by Karan on 3-7-2017.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class SmartEnemy extends GameObject {
    private Handler handler;
    //private Random r;
    public int time = 0;
    public int timeY = 0;
    private GameObject player;
    private Texture img2 = new Texture("Smart_enemy.png");

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super((float)x, (float)y, id);
        this.handler = handler;
        this.Health = 0.0F;

        for(int i = 0; i < handler.object.size(); ++i) {
            if((handler.object.get(i)).getId() == ID.Player) {
                this.player = handler.object.get(i);
            }
        }



    }

    public void tick() {
        if(HUD.HEALTH > 0) {
            this.x = (float)((double)this.x + this.velX);
            this.y = (float)((double)this.y + this.velY);
            float diffX = this.x - this.player.getX() - 15.0F;
            float diffY = this.y - this.player.getY() - 15.0F;
            float distance = (float)Math.sqrt((double)((this.x - this.player.getX()) * (this.x - this.player.getX()) + (this.y - this.player.getY()) * (this.y - this.player.getY())));
            this.velX = -1.0D / (double)distance * (double)diffX;
            this.velY = -1.0D / (double)distance * (double)diffY;
            ++this.time;
            ++this.timeY;
            if(this.y <= 0.0F || this.y >= 854.0F) {
                this.velY *= -1.0D;
            }

            if(this.x <= 0.0F || this.x >= 1220.0F) {
                this.velX *= -1.0D;
            }
        }

        this.collision();
    }

    public void render(SpriteBatch g) {
        if(this.id == ID.SmartEnemy){
            System.out.println("Smart");
            this.tick();
            g.draw(this.img2, (float)((int) this.x), (float)((int)this.y), 60.0F, 60.0F);

        }


    }

    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, 60, 60);
    }

    private void collision() {
        for(int i = 0; i < this.handler.object.size(); ++i) {
            GameObject tempObject = this.handler.object.get(i);
            if(tempObject.getId() == ID.Projectile && this.getBounds().contains(tempObject.getBounds())) {  //intersects = contains ?
                this.handler.removeObject(tempObject);
            }
        }

    }
}

