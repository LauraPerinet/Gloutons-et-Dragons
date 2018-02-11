/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author Laura
 */
class MapDungeon extends Group{
    private Tile tile;
    private Image heros;
    private int MAP_WIDTH=5;
    private int MAP_HEIGHT=5;
    
    public MapDungeon(){
        setName("map");
        
        Image parchment=new Image(new Texture("parchment.jpg"));
        parchment.setName("background");
        
        heros=new Image(new Texture("point.png"));
        heros.setPosition(50-heros.getWidth()*0.5f, 50-heros.getHeight()*0.5f);
        heros.setName("heros");
        
        addActor(parchment);
        createMap();
        addActor(heros);
        tile=findActor("0 0");
        
    }

    
    public String touchDown(int screenX, int screenY){
        Vector2 locTmp=screenToLocalCoordinates(new Vector2( screenX,  screenY));
 
        Actor tileTouched= hit(locTmp.x, locTmp.y, false);
        if(!tileTouched.getName().equals("background")){
            if(canWeGo(tileTouched)){
                moveHeros(tileTouched);
                return tileTouched.getName();
            }
        }
        return null;
    }
    
    private boolean canWeGo(Actor tileTouched) {
        if(tileTouched.getName().equals("heros")) tileTouched=tile;
        Tile t=(Tile) tileTouched;
        int fromId=tile.getId();
        int toId=t.getId();
        
        int GO_LEFT = fromId-10;
        int GO_RIGHT = fromId+10;
        int GO_UP = fromId+1;
        int GO_DOWN = fromId-1;

        if(toId == GO_LEFT || toId== GO_RIGHT || toId == GO_UP || toId == GO_DOWN ) return true;
        
        return false;
    }

    private void moveHeros(Actor tileTouched) {
        MoveToAction move=new MoveToAction();
        move.setPosition(tileTouched.getX()+tileTouched.getWidth()/2-heros.getWidth()/2, tileTouched.getY()+tileTouched.getHeight()/2+heros.getHeight()/2);
        move.setDuration(1f);
        heros.addAction(move);
        tile=(Tile) tileTouched;
    }

    private void createMap() {
        for(int y=0; y<MAP_HEIGHT; y++){
            for(int x=0; x<MAP_WIDTH; x++){
                Tile tile=new Tile(x,y);
                addActor(tile);
            }
        }
    }
}
