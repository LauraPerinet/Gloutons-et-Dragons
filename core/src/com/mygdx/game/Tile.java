/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Laura
 */
class Tile extends Actor {
    private Sprite tileImg=new Sprite(new Texture("tile.png"));
    private int id;       
    public Tile(int x, int y) {
            setName(x+" "+y);
            id=x*10+y;
            setBounds(tileImg.getWidth()*x, tileImg.getHeight()*y, tileImg.getWidth(), tileImg.getHeight());
            tileImg.setPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        tileImg.draw(batch);
    }
    public int getId(){
        return id;
    }
    
    
}
