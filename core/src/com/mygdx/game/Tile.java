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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *
 * @author Laura
 */
class Tile extends Image {
    private int id;
    private String orientation;
    private String type;
    
    public Tile(int x, int y, String type, String orientation) {
            super(new Texture("tiles/"+type+orientation+".png"));
            setName(x+" "+y);
            this.type=type;
            this.orientation=orientation;
            id=x*10+y;
            setBounds(getWidth()*x, getHeight()*y, getWidth(), getHeight());
            setPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(this.getColor());
        ((TextureRegionDrawable) getDrawable()).draw(batch, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
    public int getId(){
        return id;
    }
    
    
}
