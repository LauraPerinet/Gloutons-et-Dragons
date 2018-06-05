/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.Random;

/**
 *
 * @author Laura
 */
public class Items extends Image{
    protected String name;
    protected int  minX=0, minY=70, maxX=1920-(int) getWidth(), maxY=800-(int) getHeight(), lock;
    protected float x,y;
    protected boolean fromRoom, draggable=false;
    
    public Items(String item, Boolean fromRoom){
        super(new Texture("items/"+item+".png"));
        setName(item);
        this.fromRoom=fromRoom;
        if(fromRoom) addClickToPick();
        
    }
    public Items(TextureRegion region){
        super(region);
        if(fromRoom) addClickToPick();
        
    }

    private void addClickToPick() {
        
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Inventory.getInstance().addItem(Items.this);
                Items.this.remove();
            }
        });
    }
    public void setPos(){
        int x=new Random().nextInt(maxX-minX)+minX;
        int y=new Random().nextInt(maxY-minY)+minY;
        setPosition(x, y);
    }
    public void setPositionOnInventory(float x, float y){
        this.x=x;
        this.y=y;
        setPosition(x, y);
    }
    public boolean getDraggable(){ return draggable;}
    public void setLock(int lock){ this.lock=lock;}
    public int getLock(){return lock;}
    


    
    
}
