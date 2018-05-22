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

/**
 *
 * @author Laura
 */
public class Character {
    protected String name;
    protected int order, initiative;
    protected TextureAtlas spriteSheet;
    protected int MAX_WALK, MAX_ATTACK;
    protected CharactersFullGUI actor, staticActor;
    
    protected void createActor(){
        actor = new CharactersFullGUI(spriteSheet, this); 
        staticActor= new CharactersFullGUI(spriteSheet, this);
        actor.setName(getName());
        staticActor.setName(getName());
    }
    public String getName(){return name;}
    public CharactersFullGUI getActor(){ return actor;}
    public CharactersFullGUI getStaticActor(){ return staticActor;}
    public int getOrder(){ return order;}
    public int getMaxWalk(){ return MAX_WALK;}
    public int getMaxAttack(){ return MAX_ATTACK;}
    public int getInit(){return initiative;}
    
    public void setOrder(int order){
        this.order=order;
    }
   
    
}
