package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Player class which extends GameObject and has all the additional implementation
 * for the methods that are needed to have it interact with other GameObjects.
 */
public class Player extends GameObject {
	Handler handler;
    MyGdxGame game;
    Sound hero = Gdx.audio.newSound(Gdx.files.internal("data/hero.mp3"));

//	Game game;
    private Texture img = new Texture("robot.png");
    /**
     * Instantiate a new instance of player.
     *
     * @param x       X coordinate of the class
     * @param y       Y coordinate of the class
     * @param id      Type of GameObject
     * @param handler Instance of Handler class which loops through all GameObjects
     * @param game    Instance of MyGdxGame class used to change the GameState if the player dies
     */
	public Player(int x, int y, ID id, Handler handler, MyGdxGame game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
//		try {
//			// Grab the InputStream for the image.
//			img = ImageIO.read(new FileInputStream("robot.png"));
//
//		} catch (IOException e) {
//			System.out.println("The image was not loaded.");
//
//		}

	}
    /**
     * Creates a hitbox that allows the program to check when it gets touched by other objects
     *
     * @return Returns a Rectangle at the position of the object that's also the size of the object
     */
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 50, 50);
	}
    /**
     * Method which updates all the data of the class
     */
	public void tick() {
        if (HUD.HEALTH > 0) { //checks if the player is alive
            x += velX;
            y += velY;
            // -1 links facing 1 rechts facing
            // -1 onder 1 boven
            if (velX < 0 && velY < 0) {
                GoingUP = -1;
                facing = -1;
            } // linksonder
            else if (velX > 0 && velY < 0) {
                GoingUP = -1;
                facing = 1;
            } // rechtsonder
            else if (velX < 0 && velY > 0) {
                GoingUP = 1;
                facing = -1;
            } // linksboven
            else if (velX > 0 && velY > 0) {
                GoingUP = 1;
                facing = 1;
            } // rechtsboven
            else if (velX == 0 && velY > 0) {
                GoingUP = 1;
                facing = 0;
            } // boven
            else if (velX == 0 && velY < 0) {
                GoingUP = -1;
                facing = 0;
            } // onder
            else if (velX < 0 && velY == 0) {
                System.out.println("test");
                GoingUP = 0;
                facing = -1;
            } // links
            else if (velX > 0 && velY == 0) {
                GoingUP = 0;
                facing = 1;
            } // rechts

            x = MyGdxGame.clamp(x, 0,1800 - 38);
            y = MyGdxGame.clamp(y, 0, 1080 - 60);
        }
        else {
               GoingUP = 1;
               facing = 0;
               velY = 50; // how fast the player goes down
                y += velY; // drops the player down
		        if (y >= 2050){  //checks position of the player
		            game.GameState = State.End;//sets gamestate to end screen
                    handler.clearHandler(); //deletes all active enemies
                    HUD.HEALTH = 100; //resets health after the game
		        } }
		collision();

	}
    /**
     * Uses the getBounds method to check if the object is being touched by other GameObjects
     */
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.HealingEnemy) {
                if (getBounds().overlaps(tempObject.getBounds())) {
                    HUD.HEALTH -= 2;
                }
            }
            if (tempObject.getId() == ID.MineEnemy) {
                if (getBounds().overlaps(tempObject.getBounds())) {
                    HUD.HEALTH -= 100;
                }
            }
//
			if (tempObject.getId() == ID.SmartEnemy) {
				if (getBounds().overlaps(tempObject.getBounds())) {
					HUD.HEALTH -= 4;
				}
			}


		}
	}
    /**
     * Draws the image of the class on the given instance of Graphics
     *
     * @param g Instance of Graphics originating from the Game class
     */
	@Override
	public void render(SpriteBatch g) {
		if (id == ID.Player) {
            this.tick();
			g.draw(img, (int) x, (int) y, 50, 50);
		}
	}

}
