/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import com.mygdx.game.CharactersGUI.HerosMapGUI;
import com.mygdx.game.DungeonGUI.MapDungeon;

/**
 *
 * @author Laura
 */
public class Character {
    protected String name;
    protected int order, initiative, hp, attack, defense, xp;
    protected TextureAtlas spriteSheet;
    protected int MAX_WALK, MAX_ATTACK;
    protected CharactersFullGUI actor, staticActor;
    protected boolean ready=false, hasAttack=false;
    
    public String getName(){return name;}
    public CharactersFullGUI getActor(){ 
        CharactersFullGUI actor = new CharactersFullGUI(spriteSheet, this); 
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
        hp-=attack-defense;
        Gdx.app.log("Character 43", name+" loose "+(attack-defense)+" hp. Still got "+hp);
        if(hp<=0){
           Gdx.app.log("Character 45", name+" is dead");
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
    
}
