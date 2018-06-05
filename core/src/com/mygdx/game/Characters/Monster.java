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
    
    public Monster(String name){
        this.name=name;
        type="monster";
    }

    public boolean canAttack(){
        Gdx.app.log(name, "canAttack : attackType "+attackType+"   ordre "+order);
        if(attackType.equals("AC") && order>0) return false;
        Gdx.app.log(name, "canAttack");
        return true;
    }
    public boolean attack() {
        hasAttack=true;
        Heros heros;
        if(attackType.equals("AC")){
            heros=MapDungeon.getInstance().getHeros(2);
        }
        else{
            heros=MapDungeon.getInstance().getLastHeros();
        }
        if(heros.isAlive()){
            int attack=this.attack+new Random().nextInt(xp);
            // Return false if heros is killed
            heros.getHurt(attack);
        }
        return heros.isAlive();
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
