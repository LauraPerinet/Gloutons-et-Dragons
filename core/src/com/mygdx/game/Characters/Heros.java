/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import com.mygdx.game.CharactersGUI.HerosMapGUI;

/**
 *
 * @author Laura
 */
public class Heros {
    protected String name;
    protected int order;
    protected HerosMapGUI mapPt;
    protected TextureAtlas spriteSheet;
    protected int MAX_WALK;
    protected int MAX_ATTACK;
    
    public String getName(){return name;}
    public HerosMapGUI getMapActor(){return mapPt;}
    public CharactersFullGUI getActor(){ return new CharactersFullGUI(spriteSheet, this); }
    public int getOrder(){ return order;}
    public int getMaxWalk(){ return MAX_WALK;}
    public int getMaxAttack(){ return MAX_ATTACK;}
    
    public void setOrder(int order){
        this.order=order;
        this.mapPt.setOrder(order);
    }
   
    
}
