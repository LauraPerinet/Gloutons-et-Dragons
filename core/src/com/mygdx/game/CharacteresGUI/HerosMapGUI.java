/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.CharacteresGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

/**
 *
 * @author Laura
 */
public class HerosMapGUI extends Actor{
    private int position;
    private Sprite sprite; 
    protected final int mapDeltaX;
    protected final int mapDeltaY;
    
    public HerosMapGUI( int position, int mDX, int mDY){
        this.position=position;
        this.mapDeltaX=mDX;
        this.mapDeltaY=mDY;
    }
    protected void setSprite(TextureRegion texture){
        this.sprite=new Sprite(texture);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(), getY());
    }
    public void moveHerosOnMap(Actor tileTouched) {
        MoveToAction move=new MoveToAction();
        move.setPosition(tileTouched.getX()+tileTouched.getWidth()/2-getWidth()/2 + mapDeltaX, tileTouched.getY()+tileTouched.getHeight()/2+getHeight()/2+mapDeltaY);
        move.setDuration(1.4f+position*0.1f);
        addAction(move);
    }
    public int getPosition(){ return position; }
}
