/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.DungeonGUI.Dungeon;
import com.mygdx.game.DungeonGUI.MapDungeon;
import com.mygdx.game.Game;
import java.util.Random;

/**
 *
 * @author Laura
 */
public class Monster extends Character{
    
    public Monster(String name, int order){
        spriteSheet=new TextureAtlas("Characters/monsters/lapinator.atlas");
        this.name=name;
        this.order=order;
        this.initiative=1;
        this.type="monster";
        hp=8;
        attack=3;
        defense=0;
        xp=2;
        
        this.MAX_WALK=0;
        this.MAX_ATTACK=23;
    }

    public boolean attack() {
        hasAttack=true;
        Heros firstHeros=MapDungeon.getInstance().getHeros(2);
        if(firstHeros.isAlive()){
            int attack=this.attack+new Random().nextInt(xp);
            // Return false if heros is killed
            firstHeros.getHurt(attack);
        }
        return firstHeros.isAlive();
    }

    public boolean hasAttack() {
        return hasAttack;
    }
    public void setHasAttack(boolean b){ hasAttack=b;}
    
    public boolean getHurt(int attack){
        hp-=attack-defense;
        statesGUI.setHealth(this);
        if(hp<=0) statesGUI.clear();
        return true;
    }
    
}
