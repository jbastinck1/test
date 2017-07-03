package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


public class HUD {

    public static int HEALTH = 100;

    private int score = 0;
    public int level = 1;
  //  Spawn p;

    public void tick(){
        HEALTH = (int) MyGdxGame.clamp(HEALTH,0,100);


    }

    public void render(SpriteBatch g) {
        g.end();
        Rectangle player = new Rectangle(15,15,200,32);
        ShapeRenderer sr = new ShapeRenderer();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.RED);
        sr.rect(15,15,200,32);
        sr.setColor(Color.GREEN);
        sr.rect(15,15,HEALTH*2,32);
        sr.setColor(Color.WHITE);
        sr.rect(player.x,player.y,player.width,player.height);
        sr.end();
//
//        g.setColor(Color.RED);
//        g.fillRect(15,15,200,32);
//        g.setColor(Color.green);
//        g.fillRect(15,15,HEALTH * 2,32);
//        g.setColor(Color.white);
//        g.draw(player.x,player.y,player.width,player.height);
        BitmapFont font = new BitmapFont();
        g.begin();
        font.draw(g,"Level: " + level,15,86);
        font.draw(g,"Health: " + HEALTH, 15, 108);
        font.draw(g,"Score: ",15,130);//+ p.scoreKeep
    }

    public void score(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }

    public int getLevel()
    {

        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }
}

