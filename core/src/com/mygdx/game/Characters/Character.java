/*
Personnages du jeu.
 */
package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import com.mygdx.game.CharactersGUI.HerosMapGUI;
import com.mygdx.game.CharactersGUI.StateHeros;
import com.mygdx.game.DungeonGUI.MapDungeon;
import com.mygdx.game.Fight;
import com.mygdx.game.Items.Potion;

/**
 *
 * @author Laura
 */
public class Character {
    protected String name, action="walk", type, attackType, label;
    protected int order, initiative, hp, attack, defense, xp;
    protected TextureAtlas spriteSheet;
    protected int MAX_WALK, MAX_ATTACK;
    protected CharactersFullGUI actor, staticActor;
    protected boolean ready=false, hasAttack=false, isHurt=false, isSelected=false, actionChange=true;
    protected Fight fight=null;
    protected StateHeros statesGUI;
    protected Sound attackSound, deadSound;
    
    public String getName(){return name;}
    public CharactersFullGUI getActor(boolean addListener){ 
        CharactersFullGUI actor = new CharactersFullGUI(spriteSheet, this, addListener); 
        actor.setName(getName());
        return actor;
    }

    public int getOrder(){ return order;}
    public int getMaxWalk(){ return MAX_WALK;}
    public int getMaxAttack(){ return MAX_ATTACK;}
    public int getInit(){return initiative;}
    
    public void setOrder(int order){
        this.order=order;
    }
    
    public boolean getHurt(int attack) {
        int lost=attack-defense;
        hp-=lost;
        label="-"+lost;
        statesGUI.setHealth(this);
        if(hp<=0){
           return false;
        }
        return true;
    }
    public boolean isAlive(){
        return hp>0 ? true : false;
    }
    public boolean isReady(){ return ready;}

    public void setReady(boolean ready) {
        this.ready=ready;
    }
    public void setStatesGUI(StateHeros statesGUI){
        this.statesGUI=statesGUI;
    }
    public String getAction(){ return action; }
    public void setAction(String action){ 
        actionChange=true; 
        this.action=action; 
        if(action.equals("attack")){
            attackSound.play();
        }
    }
     public void setFight(Fight fight){ this.fight=fight;}
    public int getHp(){return hp;}
    public Fight getFight() { return fight;}
    public String getType(){return type;}
    public String getAttackType() { return attackType; }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean b){
        isSelected=b;
    }
    public String getLabel(){return label;}
    public boolean getActionChange() {
       return actionChange;
    }

    public void setActionChange(boolean b) {
        actionChange=b;
    }

    
}
