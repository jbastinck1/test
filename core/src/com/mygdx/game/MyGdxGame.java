package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor{
	SpriteBatch batch;
	Texture img;
	Texture img2;
//	String state = "Menu";
	private Handler handler;
	public State GameState;
	private KeyInput controls;
	Texture missile;
	private HUD hud;
	Texture movebuttonup;
	Texture movebuttondown;
	Texture movebuttonleft;
	Texture movebuttonright;
    public BasicEnemy be;
    public SmartEnemy sm;
    private Spawn spawn;

	public MyGdxGame(){
		this.spawn = new Spawn(this.handler, this.hud, this);
        this.handler = new Handler();
		this.hud = new HUD();


	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		handler = new Handler();
		hud = new HUD();
		spawn = new Spawn(handler,hud,this);
		controls = new KeyInput(handler);
		GameState = State.Menu;
		img = new Texture("enemyrobot.png");
		img2 = new Texture("Smart_enemy.png");
		missile = new Texture("missile.png");
		movebuttonup = new Texture("movebutton.png");
		movebuttonleft = new Texture("movebuttonL.png");
		movebuttondown = new Texture("movebuttonD.png");
		movebuttonright = new Texture("movebuttonR.png");

	}

	public void tick(){
		handler.tick();
		hud.tick();
		spawn.tick();

	}

	public static float clamp(float var, float min, float max){
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.input.setInputProcessor(this);
		batch.begin();

		this.tick();
		BitmapFont font = new BitmapFont();
		font.getData().setScale(5);
		font.setColor(Color.WHITE);
		ShapeRenderer sr = new ShapeRenderer();

		if(GameState == State.Game){
			handler.render(batch);
			hud.render(batch);
			spawn.tick();
			batch.draw(missile, 1640, 0, 160, 160);
			batch.draw(movebuttonright, 320, 0, 160, 160);
			batch.draw(movebuttonleft, 0, 0, 160, 160);
			batch.draw(movebuttonup, 160, 160, 160, 160);
			batch.draw(movebuttondown, 160, 0, 160, 160);
			batch.end();
		}else if (GameState == State.Menu) {
			font.setColor(Color.WHITE);
			font.draw(batch, "Play", 915, 740);
			font.draw(batch, "Help", 915, 490);
			font.draw(batch, "Quit", 915, 240);

			font.draw(batch, "Menu", 900, 1000);
			batch.end();
			sr.begin(ShapeRenderer.ShapeType.Line);
			sr.setColor(Color.WHITE);
			sr.rect(850, 150, 280, 120);
			sr.rect(850, 400, 280, 120);
			sr.rect(850, 650, 280, 120);
			sr.end();
		} else if (GameState == State.Help) {
			font.draw(batch, "Help", 915, 1000);
			font.draw(batch, "Go to menu", 795, 240);
			batch.end();
			sr.begin(ShapeRenderer.ShapeType.Line);
			sr.setColor(Color.WHITE);
			sr.rect(750, 150, 480, 120);
			sr.end();
		} else if (GameState == State.End) {
			font.draw(batch, "GAME OVER", 780, 1000);
			font.draw(batch, "Your score is: " + 1000, 700, 620);
			font.draw(batch, "Go to menu", 795, 240);
			batch.end();
			sr.begin(ShapeRenderer.ShapeType.Line);
			sr.setColor(Color.WHITE);
			sr.rect(750, 150, 480, 120);
			sr.end();


//            public void run() {
//                this.requestFocus();
//                long lastTime = System.nanoTime();
//                double amountOfTicks = 60.0D;
//                double ns = 1.0E9D / amountOfTicks;
//                double delta = 0.0D;
//                long timer = System.currentTimeMillis();
//                int var11 = 0;
//
//                while(this.running) {
//                    long now = System.nanoTime();
//                    delta += (double)(now - lastTime) / ns;
//
//                    for(lastTime = now; delta >= 1.0D; --delta) {
//                        this.tick();
//                    }
//
//                    if(this.running) {
//                        this.render();
//                    }
//
//                    ++var11;
//                    if(System.currentTimeMillis() - timer > 1000L) {
//                        timer += 1000L;
//                        var11 = 0;
//                    }
//                }
//
//                this.stop();
//            }
//		ShapeRenderer sr = new ShapeRenderer();
//
//		batch.begin();
//		BitmapFont font = new BitmapFont();
//		font.getData().setScale(5);
//		font.setColor(Color.WHITE);
//		Rectangle rect = new Rectangle(850,810,280,120);
//		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
//			if(rect.contains(Gdx.input.getX(), Gdx.input.getY())){
//			System.exit(1);
//		}}
//		sr.begin(ShapeRenderer.ShapeType.Line);
//		sr.setColor(Color.WHITE);
//		sr.rect(850,150,280,120);
//		sr.rect(850,400,280,120);
//		sr.rect(850,650,280,120);
//		sr.end();
//		batch.begin();
//		font.draw(batch,"Menu", 900, 1000);
//
//		font.draw(batch,"Play",915,740);
//		font.draw(batch,"Help",915,490);
//		font.draw(batch,"Quit",915,240);
//		font.draw(batch,"GAME OVER", 780,1000);
//		font.draw(batch, "Your score is: " + 1000, 700,620);
//		font.draw(batch,"Go to menu", 795,240);
//		batch.end();
//		sr.begin(ShapeRenderer.ShapeType.Line);
//		sr.setColor(Color.WHITE);
//		sr.rect(750,150,480,120);
//		sr.end();
//		batch.begin();

//		batch.end();

		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (GameState == State.Game){
			Rectangle left = new Rectangle(0,920, 160, 160);
			Rectangle right = new Rectangle(320,920, 160, 160);
			Rectangle up = new Rectangle(160,760, 160, 160);
			Rectangle down = new Rectangle(160,920, 160, 160);
			Rectangle shoot = new Rectangle(1640,920, 160, 160);
			for (int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player){
					if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
						if(left.contains(Gdx.input.getX(), Gdx.input.getY())){
							tempObject.setVelX(-10);}
						if(right.contains(Gdx.input.getX(), Gdx.input.getY())){
							tempObject.setVelX(10);}
						if(up.contains(Gdx.input.getX(), Gdx.input.getY())){
							tempObject.setVelY(10);}
						if(down.contains(Gdx.input.getX(), Gdx.input.getY())){
							tempObject.setVelY(-10);}
						if(shoot.contains(Gdx.input.getX(), Gdx.input.getY())){
							handler.addObject(new Projectile(tempObject.getX(),tempObject.getY(), ID.Projectile, handler,tempObject.getFacing() * 15,tempObject.getGoingUP()* 15));}
					}		}}}
		if (GameState == State.Menu) {
			Rectangle start = new Rectangle(850, 310, 280, 120);
			Rectangle exit = new Rectangle(850, 810, 280, 120);
			Rectangle help = new Rectangle(850, 560, 280, 120);
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				if(start.contains(Gdx.input.getX(), Gdx.input.getY())){
					handler.addObject(new Player(1980/2-32,1080/2-32, ID.Player, handler, this	));
					handler.addObject(new SmartEnemy(200,400, ID.SmartEnemy,	handler));
					handler.addObject(new BasicEnemy(300,500, ID.BasicEnemy,	handler));
					handler.addObject(new BasicEnemy(200,600, ID.BasicEnemy,handler));
					handler.addObject(new BasicEnemy(200,100, ID.BasicEnemy,handler));
					GameState = State.Game;}
				if (exit.contains(Gdx.input.getX(), Gdx.input.getY())) {
					System.exit(1);
				}
				if (help.contains(Gdx.input.getX(), Gdx.input.getY())) {
					GameState = State.Help;
				}


		}}


		if (GameState == State.Help) {
			Rectangle back1 = new Rectangle(750, 810, 480, 120);
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				if (back1.contains(Gdx.input.getX(), Gdx.input.getY())) {
					GameState = State.Menu;
				}
			}
			if (GameState == State.End) {
				Rectangle back2 = new Rectangle(750, 810, 480, 120);
				if (back2.contains(Gdx.input.getX(), Gdx.input.getY())) {
					GameState = State.Menu;
				}
			}

		}


		return true;

	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(button == Input.Buttons.LEFT){
			for (int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player){
					tempObject.setVelY(0);
					tempObject.setVelX(0);
				}}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}