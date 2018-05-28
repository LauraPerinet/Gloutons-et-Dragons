/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.AddListenerAction;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Characters.Mage;
import com.mygdx.game.Characters.Monster;
import com.mygdx.game.Characters.Thief;
import com.mygdx.game.Characters.Warrior;
import com.mygdx.game.DungeonGUI.Dungeon;
import com.mygdx.game.DungeonGUI.MapDungeon;
import com.mygdx.game.DungeonGUI.RoomGUI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Laura
 */
public class Fight {
    private ArrayList<Monster> monsters;
    private Mage mage;
    private Thief thief;
    private Warrior warrior;
    private int initHeroes=0, initMonsters=0;
    private  boolean gameOn=true;
   
    private RoomGUI room;
    
    public Fight(ArrayList<Monster> monsters, RoomGUI room){
        this.room=room;
        this.monsters=monsters;
        this.mage=MapDungeon.getInstance().getMage();
        this.thief=MapDungeon.getInstance().getThief();
        this.warrior=MapDungeon.getInstance().getWarrior();
       
        
        Gdx.app.log("Combat", "START");
        if(checkInit()){
        }else{
        }  
        
    }
    
    private boolean checkInit(){
        for(Monster monster : monsters ){
             initMonsters+=monster.getInit();  
        }
        initHeroes=mage.getInit()+thief.getInit()+warrior.getInit();
        if(initHeroes>=initMonsters){ return true; }
        return false;
    }
    
    public boolean monsterAttack(){
        boolean go = false;
        Monster monster=getMonsterAttacking();
        if(monster!=null){
            boolean herosAlive=monster.attack();
            room.setReady(false);
            
            try {
                monster.getActor().attack(room);
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Fight.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*
            if(!herosAlive){
                gameOn=MapDungeon.getInstance().setHerosPosition();
                if(!gameOn){
                    return false;
                }else{
                    room.setReady(false);
                    room.setActorsPosition(true);
                }
            }*/
            return false;
        }else{
            return false;
        }
        
    }
    Monster getMonsterAttacking(){
        for(Monster monster : monsters){
            if(!monster.hasAttack()) return monster;
        }
        return null;
    }
    
}
