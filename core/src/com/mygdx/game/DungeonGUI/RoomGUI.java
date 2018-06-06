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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Characters.Character;
import com.mygdx.game.Characters.Heros;
import com.mygdx.game.Characters.Mage;
import com.mygdx.game.Characters.Monster;
import com.mygdx.game.Characters.Thief;
import com.mygdx.game.Characters.Warrior;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import com.mygdx.game.CharactersGUI.Notification;
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
    private Label turn;
    
    public RoomGUI(String background, boolean isEmpty){
        JsonReader json=new JsonReader();
        roomThings=json.parse(Gdx.files.internal("room/roomInterior.json")).get(background);
        monsters=new ArrayList();
        actions=new HashMap<CharactersFullGUI, Action>();
        
        setName("room");
        createBackground(background); 
        get("monsters", true, isEmpty);
        get("items", false, isEmpty);
        
        
        
        //A redescendre
        out.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for(Actor h : heroes.getChildren()){
                    CharactersFullGUI heros =(CharactersFullGUI) h;
                    heros.getHeros().setFight(null);
                }
                Dungeon d=Dungeon.getInstance();
                d.goTo();
                //RoomGUI.this.remove();
            }
            
            });
       
        
    }
    public void enter(){
        removeActor(monstersGroup);
        removeActor(heroes);

        createActorsHeros();
        addActor(monstersGroup);
        setActorsPosition(true);
        //out.setTouchable(Touchable.disabled);
        turn=new Label("", Dungeon.getInstance().getSkin(), "little");
        addActor(turn);
        fight=new Fight(monsters, this);
    }
    public Group getHeros(){ return heroes;}
    private void createBackground(String background) {
        String time="Day";
        this.background=new Image(new Texture("room/"+background+time+".jpg"));
        this.background.setName("background");
        out.setPosition(1820, 70);
        addActor(this.background);
        addActor(out);
        
    }

    private void createActorsHeros() {
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
            CharactersFullGUI actor=h.getActor(true);
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
            for(Actor m : monstersGroup.getChildren()){
                CharactersFullGUI monster =(CharactersFullGUI) m;
                int to=900+monster.getHeros().getOrder()*200;
                monster.goTo(to);
                monster.getHeros().setAction("walk");

            } 
        } 
    }
    
    private void get(String type, Boolean isAMonster, boolean isEmpty) {
        addThingsToRoom(readJsonFile(type), isAMonster, isEmpty);
    }

    public boolean checkIfClear() {
        return monstersGroup.getChildren().size>0? false : true;
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

    private void addThingsToRoom(ArrayList<String> thingsToAdd, boolean isAMonster, boolean isEmpty) {
        
        int numItems;
        if(isEmpty){
            numItems= 0;
        }else{
            numItems= new Random().nextInt(4);
        }
        
        ArrayList<Monster> monstersAD=new ArrayList<Monster>();
        for(int i=0; i<numItems; i++){
            int ran =new Random().nextInt( thingsToAdd.size());
            String thing=thingsToAdd.get(ran);
            if(!isAMonster){
                Items it=Fabricator.createItem(thing, true);
                it.setPos();
                addActor(it);
                
            }else{
                Monster monster=Fabricator.createMonster(thing);
                if(monster.getAttackType().equals("AD")){
                    monstersAD.add(monster);
                }else{
                    monsters.add(monster);
                }
            }
            thingsToAdd.remove(ran); 
        }
        if(isAMonster){
            for(Monster m:monstersAD){
                monsters.add(m);
            }
            int i=0;
            for(Monster monster: monsters){
                monster.setOrder(i);
                CharactersFullGUI actor=monster.getActor(true);
                int to=900+monster.getOrder()*200;
                actor.moveBy(to, 50);
                actor.goTo(to);
                monstersGroup.addActor(actor);
                i++;
            }
        }
    }

    public Group getMonsters() {
        return monstersGroup;
    }

    public void remove(Monster monster) {
        
        for(Actor actor:monstersGroup.getChildren()){
            CharactersFullGUI m=(CharactersFullGUI) actor;
            
            if(m.getHeros()==monster){
                monstersGroup.removeActor(m);
            }
        }
    }

    public void setMonstersTouchable(Heros acting) {
        for(Actor m: monstersGroup.getChildren()){
            CharactersFullGUI monster=(CharactersFullGUI) m;
            monster.setTouchable(Touchable.disabled);
            if(acting!=null){
                if(acting.getName().equals("mage")){
                    if(mage.testIfCanActOn(monster.getHeros())) monster.setTouchable(Touchable.enabled);;
                }
                if(acting.getName().equals("warrior")){
                    if(warrior.testIfCanActOn(monster.getHeros())) monster.setTouchable(Touchable.enabled);
                }
                if(acting.getName().equals("thief")){
                    if(thief.testIfCanActOn(monster.getHeros())) monster.setTouchable(Touchable.enabled);
                }
            }
        }
    }

    public void setHerosCanBePlay(boolean canBePlayed) {
        for(Actor actor : heroes.getChildren()){
            CharactersFullGUI heros = (CharactersFullGUI) actor;
            if(heros.getHeros().isAlive()) heros.canBePlayed(canBePlayed);
        }
    }

    public void notif(Character character, String type) {
        Group group;
        CharactersFullGUI hurted;
        if(character.getType().equals("monster")){
            group=monstersGroup;
        }else{
            group=heroes;
        }
        for(Actor a:group.getChildren()){
            CharactersFullGUI actor=(CharactersFullGUI) a;
            if(actor.getHeros()==character){
                Notification notif=new Notification(actor, Dungeon.getInstance().getSkin(), type);
                addActor(notif);
                break;
            }
        }
        
    }
    
    public void setTitle(String text){
        turn.setText(text);
    }

}