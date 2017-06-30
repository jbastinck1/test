package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.awt.peer.SystemTrayPeer;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor{
	SpriteBatch batch;
	Texture img;
	String state = "Menu";
	private Handler handler = new Handler();

	@Override
	public void create() {
		batch = new SpriteBatch();

		img = new Texture("enemyrobot.png");

	}


	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.input.setInputProcessor(this);
		batch.begin();
		BitmapFont font = new BitmapFont();
		font.getData().setScale(5);
		font.setColor(Color.WHITE);
		ShapeRenderer sr = new ShapeRenderer();

		if(state == "Game"){
			handler.render(batch);
			batch.end();
		}else if (state == "Menu") {
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
		} else if (state == "Help") {
			font.draw(batch, "Help", 915, 1000);
			font.draw(batch, "Go to menu", 795, 240);
			batch.end();
			sr.begin(ShapeRenderer.ShapeType.Line);
			sr.setColor(Color.WHITE);
			sr.rect(750, 150, 480, 120);
			sr.end();
		} else if (state == "End") {
			font.draw(batch, "GAME OVER", 780, 1000);
			font.draw(batch, "Your score is: " + 1000, 700, 620);
			font.draw(batch, "Go to menu", 795, 240);
			batch.end();
			sr.begin(ShapeRenderer.ShapeType.Line);
			sr.setColor(Color.WHITE);
			sr.rect(750, 150, 480, 120);
			sr.end();
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

		if (state == "Menu") {
			Rectangle start = new Rectangle(850, 310, 280, 120);
			Rectangle exit = new Rectangle(850, 810, 280, 120);
			Rectangle help = new Rectangle(850, 560, 280, 120);
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				if(start.contains(Gdx.input.getX(), Gdx.input.getY())){
					System.out.println("start pressed");
					handler.addObject(new Player(1980/2-32,1080/2-32, ID.Player, handler));
					state = "Game";}
				if (exit.contains(Gdx.input.getX(), Gdx.input.getY())) {
					System.exit(1);
				}
				if (help.contains(Gdx.input.getX(), Gdx.input.getY())) {
					state = "Help";
				}


		}}

		if (state == "Help") {
			Rectangle back1 = new Rectangle(750, 810, 480, 120);
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				if (back1.contains(Gdx.input.getX(), Gdx.input.getY())) {
					state = "Menu";
				}
			}
			if (state == "End") {
				Rectangle back2 = new Rectangle(750, 810, 480, 120);
				if (back2.contains(Gdx.input.getX(), Gdx.input.getY())) {
					state = "Menu";
				}
			}

		}


		return true;

	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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