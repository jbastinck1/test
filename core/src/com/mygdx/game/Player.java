package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Steven on 23-4-2016.
 */
public class Player extends GameObject {
	Handler handler;
    MyGdxGame game;
//	Game game;
    private Texture img = new Texture("robot.png");

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

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() {
        System.out.println("kms");
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

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.HealingEnemy) {
                if (getBounds().contains(tempObject.getBounds())) {
                    HUD.HEALTH -= 2;
                }
            }
            if (tempObject.getId() == ID.MineEnemy) {
                if (getBounds().contains(tempObject.getBounds())) {
                    HUD.HEALTH -= 100;
                }
            }
//            else if (tempObject.getId() == ID.BasicHealth) {
//                if (getBounds().intersects(tempObject.getBounds())) {
//                    HUD.HEALTH += 2;
//                    handler.removeObject(tempObject);
//                }
//            }
//            else if (tempObject.getId() == ID.FastEnemy) {
//                if (getBounds().intersects(tempObject.getBounds())) {
//                    HUD.HEALTH -= 3;
//                }
//            }

			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.HealingEnemy) {
				if (getBounds().contains(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
			if (tempObject.getId() == ID.SmartEnemy) {
				if (getBounds().contains(tempObject.getBounds())) {
					HUD.HEALTH -= 4;
				}
			}


		}
	}// collision codeollision code

	@Override
	public void render(SpriteBatch g) {
		if (id == ID.Player) {
            this.tick();
			System.out.println("kys");
			g.draw(img, (int) x, (int) y, 32, 32);
		}
	}

}
