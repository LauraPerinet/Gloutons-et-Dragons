/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.DungeonGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *
 * @author Laura
 */
public class Tile extends Image {
    private int id;
    private String orientation;
    private String type;
    
    public Tile(int x, int y, String type, String orientation) {
            super(new Texture("tiles/"+type+orientation+".png"));
            setName("tile");
            this.type=type;
            this.orientation=orientation;
            id=x*10+y;
            setBounds(getWidth()*x, getHeight()*y, getWidth(), getHeight());
            setPosition(getX(), getY());
            addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(canWeGo((Tile) hit(x, y, true))) MapDungeon.getInstance().goToRoom((Tile) hit(x, y, true));
                }
            });
    }

    public int getId(){
        return id;
    }
    public String getOrientation(){
        return orientation;
    }
    private boolean canWeGo(Tile tileTouched) {
        MapDungeon map=MapDungeon.getInstance();
        Tile heroesOn = map.getTile();
        
        int fromId=heroesOn.getId();
        int toId=tileTouched.getId();
        
        int GO_LEFT = fromId-10;
        int GO_RIGHT = fromId+10;
        int GO_UP = fromId+1;
        int GO_DOWN = fromId-1;
        
        if(toId == GO_LEFT){
            if(!heroesOn.getOrientation().contains("W")) return false;
        }else if(toId== GO_RIGHT){
            if(!heroesOn.getOrientation().contains("E")) return false;
        }else if(toId == GO_UP){
            if(!heroesOn.getOrientation().contains("N")) return false;
        }else if(toId == GO_DOWN ){
            if(!heroesOn.getOrientation().contains("S")) return false;
        }else{
            Gdx.app.log("canweGo", "false");
            return false;
        } 
        Gdx.app.log("canweGo", "true");
        return true;
        
        
    }

    
    
}
