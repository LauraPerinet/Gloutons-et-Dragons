/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Characters;

import com.mygdx.game.CharactersGUI.StateHeros;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.CharactersGUI.HerosMapGUI;

/**
 *
 * @author Laura
 */
public class Heros extends Character{
    protected HerosMapGUI mapPt;
    protected String attackType;
    protected int energy, maxEnergy;
    protected boolean canBePlayed=false;
    
    public HerosMapGUI getMapActor(){return mapPt;}
    public boolean canBePlayed(){ return canBePlayed;}
    public void canBePlayed(boolean b){ canBePlayed=b;}
    public void setOrder(int order){
        this.order=order;
        this.mapPt.setOrder(order);
        this.type="heros";
    }
    
    
    
    public boolean testIfCanActOn(Character character){ return energy>0;}
    public void setSelected(boolean b){
        isSelected=b;
        if(b){
            setAction("selected");
        }else{
            setAction("nothing");
        }
    }
    public void attack(Monster monster){
        int a;
        if(energy>0){
            energy--;
            statesGUI.setEnergy(this);
            a=attack;
        }else{
            a=attack/2;
        }
        monster.getHurt(a);
        action="attack";
        isSelected=false;
    }

    public String getAttackType() {
        return attackType;
    }

    public int getEnergy() {
        return energy;
    }
}
