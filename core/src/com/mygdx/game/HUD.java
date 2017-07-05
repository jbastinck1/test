package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * HUD class which creates the overlay for the game and stores the player's healthand level
 */
public class HUD {

    public static int HEALTH = 100;
    private int score = 0;
    public int level = 1;
    Spawn p;


    /**
     * Method to update the displayed health
     */
    public void tick() {
        HEALTH = (int) MyGdxGame.clamp(HEALTH, 0, 100);


    }

    /**
     * Method to render the HUD
     *
     * @param g Given instance of Graphics upon which the HUD elements are rendered
     */
    public void render(SpriteBatch g) {
        Rectangle player = new Rectangle(15, 1033, 200, 32);
        ShapeRenderer sr = new ShapeRenderer();
        BitmapFont font = new BitmapFont();
        g.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.RED);
        sr.rect(15, 1033, 200, 32);
        sr.setColor(Color.GREEN);
        sr.rect(15, 1033, HEALTH * 2, 32);
        sr.setColor(Color.WHITE);
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(player.x, player.y, player.width, player.height);
        sr.end();
        g.begin();
        font.draw(g, "Level: " + level, 15, 1000);
        font.draw(g, "Health: " + HEALTH, 15, 970);
        font.draw(g, "Score: " + p.scoreKeep, 15, 940);

    }

    /**
     * Method that returns the level the player is currently in
     *
     * @return Returns an integer representing the player's level
     */
    public int getLevel() {

        return level;
    }

    /**
     * Method that sets the player's current level
     *
     * @param level Integer which represents the player's current level
     */
    public void setLevel(int level) {
        this.level = level;
    }
}

