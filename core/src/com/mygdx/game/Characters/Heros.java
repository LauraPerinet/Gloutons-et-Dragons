/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Characters;

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
    protected CharactersFullGUI img;
    
    public String getName(){return name;}
    public HerosMapGUI getMapActor(){return mapPt;}
    public CharactersFullGUI getImg(){ return img; }
    public int getOrder(){ return order;}
    
    public void setOrder(int order){
        this.order=order;
        this.mapPt.setOrder(order);
    }
    
}
