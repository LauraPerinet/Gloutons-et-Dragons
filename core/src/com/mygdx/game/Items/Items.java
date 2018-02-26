/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Laura
 */
public class Items extends Image{
    protected String name;
    protected int x,y, minX=0, minY=20, maxX=1920-(int) getWidth(), maxY=800- (int) getHeight();
    
    public Items(Texture texture){
        super(texture);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Inventory.getInstance().addItem(Items.this);
                Items.this.setVisible(false);
            }
        });
    }
    
}
