package com.mygdx.game;//package com.project4.robotron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;

public class KeyInput implements InputProcessor {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    private int moveUp = 8;
    private int moveDown = 8;
    private int moveLeft = 5;
    private int moveRight = 5;
    Player p;
//    Projectile b;
//
//
//    public void keyPressed(KeyEvent e){
//
//
//    }
//
//
//    public void keyReleased(KeyEvent e){
//
//    }

    public int getMoveUp() {
        return moveUp;
    }

    public void setMoveUp(int moveUp) {
        this.moveUp = moveUp;
    }

    public int getMoveDown() {
        return moveDown;
    }

    public void setMoveDown(int moveUp) {
        this.moveUp = moveDown;
    }

    public int getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(int moveLeft) {
        this.moveLeft = moveLeft;
    }

    public int getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(int moveRight) {
        this.moveRight = moveRight;
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
        Rectangle left = new Rectangle(0,280, 280, 120);
        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player){
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                if(left.contains(Gdx.input.getX(), Gdx.input.getY())){
                    tempObject.setVelX(-moveLeft);
                }
//                if(key== KeyEvent.VK_W) tempObject.setVelY(- moveUp);
//                if(key==KeyEvent.VK_A)tempObject.setVelX( - moveLeft);
//                if(key==KeyEvent.VK_D)tempObject.setVelX( + moveRight);
//                if(key==KeyEvent.VK_S)tempObject.setVelY( + moveDown);
//                if(key==KeyEvent.VK_SPACE){
//                    handler.addObject(b = new Projectile (tempObject.getX(),tempObject.getY(),ID.Projectile, handler,tempObject.getFacing() * 15,tempObject.getGoingUP()* 15));
                }
            }}

//        }
//        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
        return true;    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//
//        int key = e.getKeyCode();
//        for (int i = 0; i < handler.object.size(); i++){
//            GameObject tempObject = handler.object.get(i);
//
//            if(tempObject.getId() == ID.Player){
//                if(key== KeyEvent.VK_W)tempObject.setVelY( 0);
//                if(key==KeyEvent.VK_A)tempObject.setVelX( 0);
//                if(key==KeyEvent.VK_D)tempObject.setVelX( 0);
//                if(key==KeyEvent.VK_S)tempObject.setVelY( 0);
//
//            }
//        }
        return true;
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

