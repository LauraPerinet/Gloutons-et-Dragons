/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.DungeonGUI;

import com.mygdx.game.Items.Potion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Characters.Character;
import com.mygdx.game.Characters.Mage;
import com.mygdx.game.Characters.Monster;
import com.mygdx.game.Characters.Thief;
import com.mygdx.game.Characters.Warrior;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import com.mygdx.game.Fabricator;
import com.mygdx.game.Fight;
import com.mygdx.game.Items.Gold;
import com.mygdx.game.Items.Items;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    private Group heroes, monstersGroup=new Group();
    private ArrayList<Monster> monsters;
    private JsonValue roomThings;
    private boolean ready=false;
    private Fight fight;
    private HashMap<CharactersFullGUI, Action> actions;
    
    public RoomGUI(String background){
        JsonReader json=new JsonReader();
        roomThings=json.parse(Gdx.files.internal("room/roomInterior.json")).get(background);
        monsters=new ArrayList();
        actions=new HashMap<CharactersFullGUI, Action>();
        
        setName("room");
        createBackground(background); 
        get("monsters", true);
        get("items", false);
        
        getHeros();
        addActor(monstersGroup);
        setActorsPosition(false);
        setActorsPosition(true);
        
        //A redescendre
        out.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for(Actor h : heroes.getChildren()){
                    CharactersFullGUI heros =(CharactersFullGUI) h;
                    heros.goTo(2000);
                }
                Dungeon.getInstance().goTo();
                //RoomGUI.this.remove();
            }
            
            });
       
        if(checkIfClear()){

        }else{
            out.setTouchable(Touchable.disabled);
            fight=new Fight(monsters, this);
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
        
        Character[] hPosition;
        hPosition = new Character[3];
        
        hPosition[thief.getOrder()]=thief;
        hPosition[mage.getOrder()]=mage;
        hPosition[warrior.getOrder()]=warrior;
        heroes=new Group();
        
        for(Character h : hPosition){
            float from=-600+200*h.getOrder();
            CharactersFullGUI actor=h.getActor();
            actor.setX(from);
            heroes.addActor(actor);
        }
                
        addActor(heroes);
        
    }

    public void setActorsPosition(boolean moveHeros) {
        if(moveHeros){
            for(int j=0; j<heroes.getChildren().size;j++){
                CharactersFullGUI heros = (CharactersFullGUI) heroes.getChildren().get(j);
                int order=heros.getHeros().getOrder();
                int to=300+order*200;
                heros.goTo(to);
                heros.getHeros().setAction("walk");
            }
        }else{
            int i=0;
            for(Actor monster : monstersGroup.getChildren()){
                monster.moveBy(900+i*200,0);
                i++;
            } 
        } 
    }
    
    private void get(String type, Boolean isAMonster) {
        addThingsToRoom(readJsonFile(type), isAMonster);
    }

    private boolean checkIfClear() {

        return monsters.size()>0? false : true;
    }

    private ArrayList<String> readJsonFile(String thing) {
        JsonValue things=roomThings.get(thing);
        ArrayList<String> tabItems= new ArrayList<String>();
        for(int i=0; i<things.size; i++){
            for(int j=0; j<things.get(i).asInt(); j++){
                tabItems.add(things.get(i).name);
            }  
        }
        return tabItems;
    }

    private void addThingsToRoom(ArrayList<String> thingsToAdd, Boolean isAMonster) {
        int numItems = new Random().nextInt(thingsToAdd.size());
        
        for(int i=0; i<numItems; i++){
            int ran =new Random().nextInt( thingsToAdd.size());
            String thing=thingsToAdd.get(ran);
            if(!isAMonster){
                Items it=Fabricator.createItem(thing, true);
                it.setPos();
                addActor(it);
            }else{
               
                Monster monster=Fabricator.createMonster(thing, i);
                monsters.add(monster);
                monstersGroup.addActor(monster.getActor());
            }
            thingsToAdd.remove(ran); 
        }
    }
    
}