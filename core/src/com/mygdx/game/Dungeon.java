/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author Laura
 */
public class Dungeon extends Stage implements InputProcessor{
    private Game game;
    private MapDungeon map;
    private RoomGUI room;
    private Group main;
    private Menu menu;
    private Action changeScreenAction=new Action(){
        @Override
        public boolean act(float Delta){
            main.remove();
            if(main.getName().equals("map")){
                main=new RoomGUI();
            }else{
                main=map;
            }
            main.setY(menu.getHeight());
            addActor(main);
            return true;
        }
    };

    
    public Dungeon(Viewport view, Game game) {
        this.game=game;
        menu=new Menu();
        map=new MapDungeon();
        main=map;
        main.setY(menu.getHeight());
        addActor(main);
        addActor(menu);
        
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 tmp=screenToStageCoordinates(new Vector2((float) screenX, (float) screenY));
        Actor touched=hit(tmp.x,tmp.y,false);
        if(touched.getParent().getName().equals("menu")){
            menu.touchDown(screenX, screenY);
        }else{
            if(touched.getParent().getName().equals("map")){
                String roomId= map.touchDown(screenX, screenY);
                if(roomId!=null) goTo();
            }else{
                goTo();
            }
        }
        return true;        
    }

    private void goTo() {
        main.addAction(Actions.sequence(Actions.fadeOut(1), changeScreenAction, Actions.fadeIn(1)));
    }

   
    
    
    
}
