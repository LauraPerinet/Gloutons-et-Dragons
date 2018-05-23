/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Characters;

import com.mygdx.game.CharactersGUI.HerosMapGUI;

/**
 *
 * @author Laura
 */
public class Heros extends Character{
    protected HerosMapGUI mapPt;
    
    public HerosMapGUI getMapActor(){return mapPt;}
    public void setOrder(int order){
        this.order=order;
        this.mapPt.setOrder(order);
    }

   
    
}
