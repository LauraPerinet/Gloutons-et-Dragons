/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.mygdx.game.Characters.Lapinator;
import com.mygdx.game.Characters.FlyinSnake;
import com.mygdx.game.Characters.Monster;
import com.mygdx.game.Items.Gold;
import com.mygdx.game.Items.Items;
import com.mygdx.game.Items.Potion;

/**
 *
 * @author Laura
 */
public abstract class Fabricator {
    public static Items createItem(String name, boolean fromRoom){
        Items it=null;
  
        if(name.equals("gold")){
                it=new Gold(fromRoom);
        }else if( name.equals("potion")){
                it=new Potion(fromRoom);
        }else if( name.equals("energy") || name.equals("strength") || name.equals("healing")){
                it=new Potion(name, fromRoom);
        }else{
                it=new Items(name, fromRoom);
        }
  
        return it;
    }

    public static Monster createMonster(String name) {
        Monster monster=null;
        if(name.equals("FlyinSnake")){
            monster=new FlyinSnake(name);
        }else if(name.equals("Lapinator")){
            monster=new Lapinator(name);
        }
        
        
        return monster;
    }
}
