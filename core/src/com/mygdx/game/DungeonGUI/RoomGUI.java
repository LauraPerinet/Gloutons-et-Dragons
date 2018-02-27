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
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Characters.Heros;
import com.mygdx.game.Characters.Mage;
import com.mygdx.game.Characters.Thief;
import com.mygdx.game.Characters.Warrior;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import com.mygdx.game.Items.Gold;
import com.mygdx.game.Items.Items;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Laura
 */
public class RoomGUI extends Group{
    private Image background, out = new Image(new Texture("room/out.png"));;
    private Thief thief;
    private Warrior warrior;
    private Mage mage;
    private Group heroes, monsters;
    private boolean isClear = false;
    
    
    public RoomGUI(String background){
        setName("room");
        createBackground(background);
        getItems(background);
        getHeros();
        getMonsters();
        setHerosPosition();
        
        if(checkIfClear()){
            out.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for(Actor h : heroes.getChildren()){
                    CharactersFullGUI heros =(CharactersFullGUI) h;
                    heros.walk(700);
                }
                Dungeon.getInstance().goTo();
                //RoomGUI.this.remove();
                
            }
            
        });
        }
        
    }

    private void createBackground(String background) {
        this.background=new Image(new Texture("room/"+background+".jpg"));
        this.background.setName("background");
        out.setPosition(1820, 70);
        addActor(this.background);
        addActor(out);
        
    }

    private void getHeros() {
        thief=MapDungeon.getInstance().getThief();
        mage=MapDungeon.getInstance().getMage();
        warrior=MapDungeon.getInstance().getWarrior();
        
        Heros[] hPosition;
        hPosition = new Heros[3];
        
        hPosition[thief.getOrder()]=thief;
        hPosition[mage.getOrder()]=mage;
        hPosition[warrior.getOrder()]=warrior;
        heroes=new Group();
        
        for(Heros h : hPosition){
            heroes.addActor(h.getActor());
        }
                
        addActor(heroes);
        
    }

    private void setHerosPosition() {
        for(int i=0; i<heroes.getChildren().size;i++){
            Gdx.app.log(i+"", heroes.getChildren().get(i).getName());
            CharactersFullGUI heros = (CharactersFullGUI) heroes.getChildren().get(i);
            float from=-600+200*i;
            heros.setX(from);
            heros.walk(700);
        }
    }

    private void getItems(String background) {
        JsonReader json=new JsonReader();
        JsonValue items=json.parse(Gdx.files.internal("room/roomInterior.json")).get(background).get("items");
        ArrayList<String> tabItems= new ArrayList<String>();
        for(int i=0; i<items.size; i++){
            for(int j=0; j<items.get(i).asInt(); j++){
                tabItems.add(items.get(i).name);
            }  
        }
        //On random sur le nombre d'items à pop
        int numItems = new Random().nextInt(4);
        Gdx.app.log("nombre d'items", numItems+"");
        for(int i=0; i<numItems; i++){
            int ran =new Random().nextInt( tabItems.size());
            String item=tabItems.get(ran);
            Gdx.app.log("ran"+ran, item);
            tabItems.remove(ran);
            if(item.equals("gold")){
                addActor(new Gold());
            }else if( item.equals("potion")){
                addActor(new Potion());
            }else{
                addActor(new Items(new Texture("items/"+item+".png")));
            }
        }
    }

    private void getMonsters() {
        monsters=new Group();
    }

    private boolean checkIfClear() {
        return monsters.getChildren().size>0? false : true;
    }
    
}
