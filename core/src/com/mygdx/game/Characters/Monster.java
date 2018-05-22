/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author Laura
 */
public class Monster extends Character{
    public Monster(String name){
        spriteSheet=new TextureAtlas("Characters/monsters/lapinator.atlas");
        createActor();
        this.name=name;
        this.order=1;
        this.initiative=2;
        this.MAX_WALK=1;
        this.MAX_ATTACK=1;
    }
}
