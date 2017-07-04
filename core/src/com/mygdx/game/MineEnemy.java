package com.mygdx.game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


/**
 * Created by Karan on 3-7-2017.
 */

public class MineEnemy extends GameObject {
    private Handler handler;
    //private Random r;
    public int time = 0;
    public int timeY = 0;
    private Texture img = new Texture("mine.png");

    public MineEnemy(int x, int y, ID id, Handler handler) {
        super((float)x, (float)y, id);
        this.handler = handler;
        this.Health = 0.0F;
        this.velX = 0.0D;
        this.velY = 0.0D;


    }

    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, 52, 52);
    }

    public void tick() {
        this.collision();
    }

    public void render(SpriteBatch g) {
        if(id == ID.MineEnemy){
            ShapeRenderer sr = new ShapeRenderer();
            sr.setColor(Color.BLUE);
            this.tick();
            g.draw(this.img, (int)this.x, (int)this.y, 52, 52);

        }

    }

    private void collision() {
        for(int i = 0; i < this.handler.object.size(); ++i) {
            GameObject tempObject = this.handler.object.get(i);
            if(tempObject.getId() == ID.Projectile && this.getBounds().contains(tempObject.getBounds())) { // intersects = contains?
                this.handler.removeObject(tempObject);
            }
        }

    }
}
