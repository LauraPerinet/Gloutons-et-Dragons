/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.DungeonGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
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
    private boolean ready=false;
    private String roomBackground;
    private Inventory inventory;
    private Cursor AD, AC, arrow;
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
        createCursors();
        map=MapDungeon.getInstance(game.getSkin());
        menu=new Menu(game.getSkin());
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
    public Menu getMenu(){return menu;}
    public Skin getSkin(){return game.getSkin();}
    public void goTo( String roomBackground ) {
        this.roomBackground = roomBackground;
        main.addAction(Actions.sequence(Actions.fadeOut(1), changeScreenAction, Actions.fadeIn(1)));
    }
    
    public void goTo() {
        menu.removeMonsters();;
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

    private void createCursors() {
        arrow=Gdx.graphics.newCursor(new Pixmap(new FileHandle("arrow.png")), 0, 0);
        AD=Gdx.graphics.newCursor(new Pixmap(new FileHandle("AD.png")), 0, 0);
        AC=Gdx.graphics.newCursor(new Pixmap(new FileHandle("AC.png")), 0, 0);
        Gdx.graphics.setCursor(arrow);
    }
    public void setCursor(String cursor){
        if(cursor.equals("AC")){
            Gdx.graphics.setCursor(AC);
        }else if(cursor.equals("AD")){
            Gdx.graphics.setCursor(AD);
        }else{
            Gdx.graphics.setCursor(arrow);
        }
    }

    
    
    

   
    
    
    
}
