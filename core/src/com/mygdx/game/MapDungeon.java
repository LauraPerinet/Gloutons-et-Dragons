/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 *
 * @author Laura
 */
public class MapDungeon extends Stage implements InputProcessor{
    private Group map;
    private Tile tile;
    private Image heros;
    private int MAP_WIDTH=5;
    private int MAP_HEIGHT=5;
    
    
    public MapDungeon(ScreenViewport screenViewport) {
        Image parchment=new Image(new Texture("parchment.jpg"));
        map=new Group();
        map.setName("map");
        heros=new Image(new Texture("point.png"));
        heros.setPosition(50-heros.getWidth()*0.5f, 50-heros.getHeight()*0.5f);
        
        for(int y=0; y<MAP_HEIGHT; y++){
            for(int x=0; x<MAP_WIDTH; x++){
                Tile tile=new Tile(x,y);
                map.addActor(tile);
            }
        }
        
        tile=map.findActor("0 0");
  
        map.addActor(heros);
        addActor(parchment);
        addActor(map);
        
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 tmp=screenToStageCoordinates(new Vector2((float) screenX, (float) screenY));
        Tile tileTouched=(Tile) hit(tmp.x, tmp.y, false);
        
        Gdx.app.log("pixel heros", heros.getX()+" "+heros.getY());
        
        if(tileTouched!=null){
            Gdx.app.log("On est", tile.getName());
            Gdx.app.log("HIT", tileTouched.getName());
            if(canWeGo(tileTouched))
                moveHeros(tileTouched);
        }
        return true;        
    }

    private boolean canWeGo(Tile tileTouched) {
        int fromId=tile.getId();
        int toId=tileTouched.getId();
        
        int GO_LEFT = fromId-10;
        int GO_RIGHT = fromId+10;
        int GO_UP = fromId+1;
        int GO_DOWN = fromId-1;

        if(toId == GO_LEFT || toId== GO_RIGHT || toId == GO_UP || toId == GO_DOWN ) return true;
        
        return false;
    }

    private void moveHeros(Tile tileTouched) {
        MoveToAction move=new MoveToAction();
        move.setPosition(tileTouched.getX()+tileTouched.getWidth()/2-heros.getWidth()/2, tileTouched.getY()+tileTouched.getHeight()/2+heros.getHeight()/2);
        move.setDuration(5f);
        heros.addAction(move);
        tile=tileTouched;
    }
    
    
    
}
