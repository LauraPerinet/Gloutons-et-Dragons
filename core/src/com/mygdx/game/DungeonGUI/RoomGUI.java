/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.DungeonGUI;

import com.mygdx.game.Items.Potion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
    private boolean isClear = false;
    
    
    public RoomGUI(String background){
        JsonReader json=new JsonReader();
        roomThings=json.parse(Gdx.files.internal("room/roomInterior.json")).get(background);
        monsters=new ArrayList();
        
        setName("room");
        createBackground(background); 
        get("monsters", true);
        get("items", false);
        
        getHeros();
        addActor(monstersGroup);
        setActorsPosition();
        
        //A redescendre
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
        
        if(checkIfClear()){
            
        }else{
            Fight figth=new Fight(monsters);
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
            heroes.addActor(h.getActor());
        }
                
        addActor(heroes);
        
    }

    private void setActorsPosition() {
        for(int i=0; i<heroes.getChildren().size;i++){
            CharactersFullGUI heros = (CharactersFullGUI) heroes.getChildren().get(i);
            //float from=-600+200*i;
            float from=(float) (800-i*200);
            Gdx.app.log("walk", "from "+from);
            //heros.setX(from);
            heros.walk( from );
        }
        for(int i=0; i<monsters.size();i++){
             CharactersFullGUI monster =  monsters.get(i).getActor();
             monster.setX(1000+200*i);
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
                Monster monster=Fabricator.createMonster(thing);
                monsters.add(monster);
                monstersGroup.addActor(monster.getActor());
            }
            thingsToAdd.remove(ran); 
        }
    }
    
}
