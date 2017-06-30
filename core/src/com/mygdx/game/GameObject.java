package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public abstract class GameObject {
    protected float x,y;  //only inheritable who inherits GameObject
    protected ID id;
    protected double velX, velY;
    protected float facing;
    protected float GoingUP;
    protected  float Health = 0;

    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public abstract void tick();
    public abstract void render(SpriteBatch g);
    public abstract Rectangle getBounds();

    public void setId(ID id){
        this.id = id;
    }
    public ID getId(){
        return id;
    }
    public void setVelX(double velX){
        this.velX = velX;
    }
    public void setVelY (double velY){
        this.velY = velY;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public double getVelX(){
        return velX;
    }
    public double getVelY(){
        return velY;
    }
    public float getFacing(){return facing;}
    public float getGoingUP(){return GoingUP;}
}
