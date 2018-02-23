/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.DungeonGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Characters.Heros;
import com.mygdx.game.Characters.Mage;
import com.mygdx.game.Characters.Thief;
import com.mygdx.game.Characters.Warrior;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import com.mygdx.game.CharactersGUI.HerosPosition;

/**
 *
 * @author Laura
 */
public class RoomGUI extends Group{
    private Image background;
    private Thief thief;
    private Warrior warrior;
    private Mage mage;
    private Group heroes;
    
    public RoomGUI(String background){
        setName("room");
        createBackground(background);
        getHeros();
        
        setHerosPosition();
        addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Dungeon.getInstance().goTo();
                }
            });
    }

    private void createBackground(String background) {
        this.background=new Image(new Texture("room/"+background+".jpg"));
        this.background.setName("background");
        addActor(this.background);
    }

    private void getHeros() {
        thief=MapDungeon.getInstance().getThief();
        mage=MapDungeon.getInstance().getMage();
        warrior=MapDungeon.getInstance().getWarrior();
        
        heroes=new Group();
        heroes.addActorAt(thief.getOrder(),thief.getActor());
        heroes.addActorAt(warrior.getOrder(), warrior.getActor());
        heroes.addActorAt(mage.getOrder(), mage.getActor());
        
        addActor(heroes);
        
    }

    private void setHerosPosition() {
        for(int i=0; i<heroes.getChildren().size;i++){
            CharactersFullGUI heros = (CharactersFullGUI) heroes.getChildren().get(i);
            float from=-600+200*i;
            heros.setX(200*i+100);
            heros.walk(700);
            //Gdx.app.log("heros "+i,"from "+from+" to "+to);
        }
    }
    
}
