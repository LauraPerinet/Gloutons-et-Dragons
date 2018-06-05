/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Characters.Character;
import com.mygdx.game.Characters.Heros;
import com.mygdx.game.Characters.Mage;
import com.mygdx.game.Characters.Monster;
import com.mygdx.game.Characters.Thief;
import com.mygdx.game.Characters.Warrior;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import com.mygdx.game.DungeonGUI.Dungeon;
import com.mygdx.game.DungeonGUI.MapDungeon;
import com.mygdx.game.DungeonGUI.RoomGUI;
import java.util.ArrayList;


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
    private int  heroesActions=0;
    private  boolean gameOn=true, monsterTurn=false;
    private RoomGUI room;
    private Heros acting=null, herosAttacked=null;
    private Monster monsterAttacked=null;
    
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
        
        for(Monster monster : monsters){
            monster.setFight(this);
            Dungeon.getInstance().getMenu().addMonsters(monster);
        }
        for(Heros heros : heroes){
            heros.setFight(this);
        }

        herosTurn(checkInit());
   
    }
    
    private boolean checkInit(){
        int initMonsters=0;
        int initHeroes=0;
        
        for(Monster monster : monsters ){
             if(monster.isAlive()) initMonsters+=monster.getInit();  
        }
        for(Heros heros : heroes){
            if(heros.isAlive()) initHeroes+=heros.getInit();
        }
        if(initHeroes>=initMonsters){ 
            heroesActions=initHeroes-initMonsters;
            return true; }
        return false;
    }
    
    public void actionFinished(String action, String type){

        if(type.equals("heros")){
            if(action.equals("walk")){
                if(monsterTurn && isMonsterTurn()) monsterAttack();
            }  
            if(action.equals("dead")){
                room.setActorsPosition(true);
            }
            if(action.equals("attack")){
                room.notif(monsterAttacked, "healing");
                if(!room.checkIfClear()){
                    room.setTitle("Tour des héros : "+heroesActions+" actions");
                }else{
                    room.setTitle("Le combat est gagné !");
                }
     
                if(monsterAttacked.isAlive()){
                    if(heroesActions==0){
                        monsterTurn=true;
                        herosTurn(false); 
                        monsterAttack();
                    }
                }else{
                    monsterAttacked.setAction("dead");
                }
            }
        }else{
            if(action.equals("attack")){ 
                room.notif(herosAttacked, "healing");  
                if(!herosAttacked.isAlive()){
                    herosAttacked.setAction("dead");
                }else{
                    if(isMonsterTurn()){
                        monsterAttack();
                    }else{
                        herosAttacked=null;
                        herosTurn(true);
                    }
                }
            }
            if(action.equals("dead")){
                deadMonster(monsterAttacked);
                 room.setActorsPosition(false);
            }
            if(action.equals("walk")){
                 if(heroesActions==0){
                        monsterTurn=true;
                        herosTurn(false); 
                        monsterAttack();
                    }
            }

            
            
        }
    }

    private boolean isMonsterTurn(){
        boolean lastHerosPlaying=true;
        for(Heros heros : heroes){
            if(!heros.getAction().equals("nothing")) lastHerosPlaying=false;
        }
        return lastHerosPlaying;
    }
    
    public boolean monsterAttack(){
        Monster monster=getMonsterAttacking();
        if(monster==null){
            monsterTurn=false;
            herosTurn(true);
            return false;
        }
        monster.setAction("attack");
        // monstre attack. Heros est blessé. Retourne true si le heros est vivant
        herosAttacked=monster.attack();
        if(!herosAttacked.isAlive()) MapDungeon.getInstance().setHerosPosition();
        return true;
    }
    Monster getMonsterAttacking(){
        for(Monster monster : monsters){
            if(!monster.hasAttack() && monster.isAlive() && monster.canAttack()) return monster;
        }
        return null;
    }

    public void selectHeros(Heros heros){
        for(Heros h:heroes){
                if(h==heros){
                    h.setSelected(!h.isSelected()); 
                }else{
                    h.setSelected(false); 
                }}
            if(acting==heros){
                for(Actor monster: room.getMonsters().getChildren()){
                    monster.setTouchable(Touchable.disabled);
                }
                acting=null;
            }else{
                acting=(Heros) heros;
                room.setMonstersTouchable(acting);
            }
        
    }
    public void attackMonster(Monster monster) {
        if(acting!=null){
            room.notif(acting, "energy");
            heroesActions=heroesActions-1;
             
            monsterAttacked=monster;
            if(acting.getName().equals("mage")){
                if(mage.testIfCanActOn(monster)) mage.attack(monster);
            }
            if(acting.getName().equals("warrior")){
                if(warrior.testIfCanActOn(monster)) warrior.attack(monster);
            }
            if(acting.getName().equals("thief")){
                if(thief.testIfCanActOn(monster)) thief.attack(monster);
            }
            
        }
        acting=null;
        room.setMonstersTouchable(null);
    }

    private void herosTurn(boolean canBePlayed) {
        
        monsterTurn=!canBePlayed;
        checkInit();
        if(heroesActions<1) heroesActions=1;
        if(canBePlayed){
             room.setTitle("Tour des héros : "+heroesActions+" actions");
        }else{
            room.setTitle("Tour des monstres");
        }
        room.setHerosCanBePlay(canBePlayed);
        monsterAttacked=null;
        for(Monster monster:monsters) monster.setHasAttack(false);
    }

    public Heros getHerosActing() {
        return acting;
    }

    public void deadMonster(Monster monster) {
        for(Monster m:monsters){
            if(monster!=m && m.getOrder()>monster.getOrder()) m.setOrder(m.getOrder()-1);
        }
        Gdx.app.log("deadMonster fight", "dead "+monster.getName());
        room.remove(monster);
        monsters.remove(monster);
    }


    
}
