/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.DungeonGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import com.mygdx.game.Game;
import com.mygdx.game.Items.Inventory;
import com.mygdx.game.Menu;

/**
 *
 * @author Laura
 * 
 * The main stage, where games really happen. Deals with passages between map screen and room's interior screens
 */
public class Dungeon extends Stage implements InputProcessor{
    public static Dungeon INSTANCE;
    private Game game;
    private MapDungeon map;
    private Group main;
    private RoomGUI room;
    private Menu menu;
    private String roomBackground;
    private Inventory inventory;
    private Action changeScreenAction=new Action(){
        @Override
        public boolean act(float Delta){
            main.remove();
            if(main.getName().equals("map")){
                room = new RoomGUI( roomBackground );
                main=room;
            }else{
                main=map;
            }
            main.setY(menu.getHeight());
            addActor(main);
            return true;
        }
    };

    private Dungeon(Viewport view, Game game) {
       
        this.game=game;
        inventory=Inventory.getInstance(game.getSkin());
        menu=new Menu();
        map=MapDungeon.getInstance(game.getSkin());
        
        main=map;
        main.setY(menu.getHeight());
        addActor(main);
        addActor(menu);
        Gdx.input.setInputProcessor(this);
    }
    
    public static Dungeon getInstance(Viewport view, Game game){
        INSTANCE=new Dungeon(view,game); 
        return INSTANCE;
    }
    
    public static Dungeon getInstance(){
       return INSTANCE; 
    }

    public Skin getSkin(){return game.getSkin();}
    public void goTo( String roomBackground ) {
        this.roomBackground = roomBackground;
        main.addAction(Actions.sequence(Actions.fadeOut(1), changeScreenAction, Actions.fadeIn(1)));
    }
    
    public void goTo() {
        main.addAction(Actions.sequence(Actions.fadeOut(1), changeScreenAction, Actions.fadeIn(1)));
    }

    public void gameOver() {
        main.clear();
        map.clear();
        inventory.clear();
        game.gameOver();
    }

    public RoomGUI getRoom() {
        return room;
    }

    public Action setReady(boolean b) {
        final boolean ready=b;
        Action action=new Action() {
            @Override
            public boolean act(float delta) {
                CharactersFullGUI character=(CharactersFullGUI) this.actor;
                character.getHeros().setReady(ready);
                return true;
            }
        };
        return action;
    }

   
    
    
    
}
