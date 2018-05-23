/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.DungeonGUI.MapDungeon;
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
        this.initiative=2;
        hp=8;
        attack=10;
        defense=0;
        xp=2;
        this.MAX_WALK=1;
        this.MAX_ATTACK=1;
        
    }

    public void attack() {
        Heros firstHeros=MapDungeon.getInstance().getHeros(2);
        int attack=this.attack+new Random().nextInt(xp);
        Gdx.app.log("Monster 35", name+" "+order+" attaque : "+attack );
        
        // Return true if heros is killed
        if(firstHeros.getHurt(attack)){
            MapDungeon.getInstance().setHerosPosition(firstHeros);
        }

    }
    
}
