/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.CharactersGUI;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

/**
 *
 * @author Laura
 */
public class HerosMapGUI extends Actor{
    private int order;
    private Sprite sprite; 
    protected final int mapDeltaX;
    protected final int mapDeltaY;
    
    public HerosMapGUI( int order, int deltaX, int deltaY, TextureAtlas spriteSheet){
        this.order=order;
        this.mapDeltaX=deltaX;
        this.mapDeltaY=deltaY;
 
        TextureRegion region=spriteSheet.findRegion("pt");
        this.sprite=new Sprite(region);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(), getY());
    }
    
    public void moveHerosOnMap(Actor tileTouched) {
        MoveToAction move=new MoveToAction();
        move.setPosition(tileTouched.getX()+tileTouched.getWidth()/2-getWidth()/2 + mapDeltaX, tileTouched.getY()+tileTouched.getHeight()/2+getHeight()/2+mapDeltaY);
        move.setDuration(1.4f-order*0.5f);
        addAction(move);
    }
    
    public int getOrder(){ return order; }
    public void setOrder(int order){ this.order=order; }
    public void setPositionOnMap(float x, float y){
        setPosition(x-getWidth()/2+mapDeltaX, y-getHeight()/2 + mapDeltaY);
    }
}
