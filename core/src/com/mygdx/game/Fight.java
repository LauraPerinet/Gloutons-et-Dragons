/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.AddListenerAction;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Characters.Heros;
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
public class Fight extends Actor{
    private ArrayList<Monster> monsters;
    private ArrayList<Heros> heroes;
    private Mage mage;
    private Thief thief;
    private Warrior warrior;
    private int initHeroes=0, initMonsters=0;
    private  boolean gameOn=true, monsterTurn=false;
    private RoomGUI room;
    
    public Fight(ArrayList<Monster> monsters, RoomGUI room){
        heroes=new ArrayList<Heros>();
        this.room=room;
        this.monsters=monsters;
        this.mage=MapDungeon.getInstance().getMage();
        this.thief=MapDungeon.getInstance().getThief();
        this.warrior=MapDungeon.getInstance().getWarrior();
        heroes.add(mage);
        heroes.add(thief);
        heroes.add(warrior);

        for(Monster monster : monsters)  monster.setFight(this);
        for(Heros heros : heroes)  heros.setFight(this);
    }
    
    private boolean checkInit(){
        for(Monster monster : monsters ){
             initMonsters+=monster.getInit();  
        }
        initHeroes=mage.getInit()+thief.getInit()+warrior.getInit();
        if(initHeroes>=initMonsters){ return true; }
        return false;
    }
    
    public void actionFinished(String action, String type){
        
        if(type.equals("heros")){
            if(action.equals("walk")){
                if(isMonsterTurn()) monsterAttack();
            }
        }else{
            if(action.equals("attack")){
                if(isMonsterTurn()) monsterAttack();
            }
        }
    }
    
    private boolean isMonsterTurn(){
        boolean monsterTurn=true;
        for(Heros heros : heroes){
            if(!heros.getAction().equals("nothing")) monsterTurn=false;
        }
        return monsterTurn;
    }
    
    public boolean monsterAttack(){
        Monster monster=getMonsterAttacking();
        if(monster==null) return false;
        
        monster.setAction("attack");
        // monstre attack. Heros est blessé. Retourne si le heros est vivant
        if(monster.attack()) MapDungeon.getInstance().setHerosPosition();
        return true;
    }
    Monster getMonsterAttacking(){
        for(Monster monster : monsters){
            if(!monster.hasAttack()) return monster;
        }
        return null;
    }
    
}
