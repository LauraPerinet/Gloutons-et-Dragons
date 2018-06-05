/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Characters.Monster;

/**
 *
 * @author PERINETM
 */
public class FlyinSnake extends Monster {

    public FlyinSnake(String name) {
        super(name);
        spriteSheet=new TextureAtlas("Characters/monsters/flyinSnake.atlas");
        initiative=2;
        hp=3;
        attack=2;
        defense=0;
        xp=2;
        attackType="AD";
        
        this.MAX_WALK=0;
        this.MAX_ATTACK=23;
    }
    
}
