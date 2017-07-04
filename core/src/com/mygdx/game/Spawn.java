package com.mygdx.game;


/**
 * Created by Karan on 3-7-2017.
 */

public class Spawn {
    private Handler handler;
    private HUD hud;
    public static int scoreKeep = 0;
    private int delay = 0;
    private int levelspawner = 0;
    private MyGdxGame game;
    public boolean levelup;


    public Spawn(Handler handler, HUD hud, MyGdxGame game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
        this.levelup = false;
    }

    public void tick(){
        delay++;
        System.out.println("delay is" + delay);
        System.out.println("scorekeep" + scoreKeep);
        if (scoreKeep == 210) {
            levelup = true;
            scoreKeep += 10;
            delay = 0;
            hud.setLevel(hud.getLevel() + 1);
            levelspawner += 2;
            System.out.println("levelspawner" + levelspawner);}
        if (levelspawner == 2 && delay >= 200) {
            levelup = false;
            handler.addObject(new BasicEnemy(100, 100, ID.BasicEnemy, handler));
            handler.addObject(new SmartEnemy(230, 300, ID.SmartEnemy, handler));
            handler.addObject(new SmartEnemy(300, 400, ID.SmartEnemy, handler));
            handler.addObject(new SmartEnemy(400, 700, ID.SmartEnemy, handler));
            delay = 0;
            levelspawner = 0;
        }

        else if(scoreKeep ==450){
            levelup = true;
            scoreKeep +=10;
            delay= 0;
            hud.setLevel(hud.getLevel() + 1);
            levelspawner += 3;
            System.out.println("levelspawner" + levelspawner);}
        if(levelspawner == 3 && delay >=200)
        {   levelup = false;
            handler.addObject(new BasicEnemy(100,100,ID.BasicEnemy,handler));
            handler.addObject(new BasicEnemy(400,120,ID.BasicEnemy,handler));
            handler.addObject(new SmartEnemy(300,400,ID.SmartEnemy,handler));
            handler.addObject(new SmartEnemy(600,700,ID.SmartEnemy,handler));
            handler.addObject(new HealingEnemy(700, 100,ID.HealingEnemy,handler));
            delay = 0;
            levelspawner = 0;
        }

        else if(scoreKeep == 680){
            levelup = true;
            scoreKeep += 10;
            delay = 0;
            hud.setLevel(hud.getLevel() + 1);
            levelspawner += 4;
            System.out.println("levelspawner" + levelspawner);}
        if(levelspawner == 4 && delay >= 200)
        {   levelup = false;
            handler.addObject(new BasicEnemy(100,100,ID.BasicEnemy,handler));
            handler.addObject(new BasicEnemy(400,120,ID.BasicEnemy,handler));
            handler.addObject(new SmartEnemy(300,400,ID.SmartEnemy,handler));
            handler.addObject(new SmartEnemy(600,700,ID.SmartEnemy,handler));
            handler.addObject(new SmartEnemy(700,100,ID.SmartEnemy,handler));
            handler.addObject(new HealingEnemy(700, 100,ID.HealingEnemy,handler));
            handler.addObject(new MineEnemy(500,270,ID.MineEnemy,handler));
            handler.addObject(new MineEnemy(350,670,ID.MineEnemy,handler));
            handler.addObject(new MineEnemy(720,640,ID.MineEnemy,handler));
            delay = 0;
            levelspawner = 0;
        }
        else if(scoreKeep == 1000)
        {scoreKeep += 10;
            delay = 0;
            hud.setLevel(hud.getLevel() + 1);
            levelspawner += 0;
            System.out.println("levelspawner" + levelspawner);
            game.GameState = State.End;
            handler.clearHandler();
            HUD.HEALTH = 100;}


    }
}
