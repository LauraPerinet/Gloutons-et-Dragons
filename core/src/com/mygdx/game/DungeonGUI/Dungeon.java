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
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Game;
import com.mygdx.game.Menu;

/**
 *
 * @author Laura
 */
public class Dungeon extends Stage implements InputProcessor{
    public static Dungeon INSTANCE;
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

    
    private Dungeon(Viewport view, Game game) {
        this.game=game;
        menu=new Menu();
        map=MapDungeon.getInstance(game.getSkin());
        main=map;
        main.setY(menu.getHeight());
        addActor(main);
        addActor(menu);
        
        Gdx.input.setInputProcessor(this);
    }
    public static Dungeon getInstance(Viewport view, Game game){
        if(INSTANCE==null){
            INSTANCE=new Dungeon(view,game);
        }
        return INSTANCE;
    }
    
    public static Dungeon getInstance(){
       return INSTANCE; 
    }
/*
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 tmp=screenToStageCoordinates(new Vector2((float) screenX, (float) screenY));
        Actor touched=hit(tmp.x,tmp.y,false);
        Gdx.app.log("pointer", pointer+"");
        Gdx.app.log("button", button+"");
        if(touched.getParent().getName() != null && touched.getParent().getName().equals("menu")){
            menu.touchDown(screenX, screenY);
        }else{
            if(main.getName().equals("map")){
                String roomId= map.touchDown(screenX, screenY);
                if(roomId!=null) goTo();
            }else{
                goTo();
            }
        }
        return true;        
    }
*/
    public void goTo() {
        main.addAction(Actions.sequence(Actions.fadeOut(2), changeScreenAction, Actions.fadeIn(1)));
    }

   
    
    
    
}
