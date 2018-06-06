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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *
 * @author Laura
 */
public class Tile extends Image {
    private int id, x, y, state=0;
    private String orientation;
    private String type;
    //private int selected=-1;
    private TextureAtlas sprite;
    private RoomGUI roomGUI=null;
    public static int hide=0, nextHide=1, next=2, selected=3, visited=4, visitedSelected=5;
    
    public Tile(int x, int y, String type, String orientation, TextureAtlas spriteSheet ) {
            sprite=spriteSheet;
            TextureRegion region = new TextureRegion(sprite.findRegion(type+orientation, -1));
            setDrawable(new TextureRegionDrawable(region));
            setName("tile");
            this.type=type;
            this.orientation=orientation;
            this.x=x;
            this.y=y;
            id=x*10+y;
            setBounds(region.getRegionWidth()*x, region.getRegionHeight()*y, region.getRegionWidth(), region.getRegionHeight());
            setPosition(getX(), getY());
            addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.log("Tile click", id+" "+state);
                    MapDungeon map=MapDungeon.getInstance();
                     Tile heroesOn = map.getTile();
                    if(canWeGo(heroesOn)) MapDungeon.getInstance().goToRoom((Tile) hit(x, y, true));
                }
            });
    }

    public int getId(){
        return id;
    }
    public String getOrientation(){
        return orientation;
    }
    public boolean canWeGo(Tile heroesOn) {
        
        int fromId=heroesOn.getId();
        int toId=getId();
        
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
            return false;
        } 
        setDrawable(new TextureRegionDrawable(sprite.findRegion(type+orientation, 1)));
        return true;
    }
    //public void select( int select ){ setDrawable(new TextureRegionDrawable(sprite.findRegion(type+orientation, select )));}
    public void setState(int state){
        this.state=state;
    }

    public String getBackground() {
        return this.type;
    }
    public RoomGUI getRoomGUI(boolean isEmpty){
        if(roomGUI==null){
            if(isEmpty){
                roomGUI=new RoomGUI(type, true);
            }else{
                roomGUI=new RoomGUI(type, false);
            }
        }
        return roomGUI;
    }
    public int getMapX(){ return x;}
    public int getMapY(){ return y;}

    public int getState() {
       return state;
    }
    
}
