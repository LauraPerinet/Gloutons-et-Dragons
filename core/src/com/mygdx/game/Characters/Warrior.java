/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import com.mygdx.game.CharactersGUI.HerosMapGUI;

/**
 *
 * @author Laura
 */
public class Warrior extends Heros{

    public Warrior(){
        spriteSheet=new TextureAtlas(Gdx.files.internal("Characters/warrior/warrior.atlas"));
        attackSound=Gdx.audio.newSound(Gdx.files.internal("sounds/warriorAttack.mp3"));
        deadSound=Gdx.audio.newSound(Gdx.files.internal("sounds/warriorDead.mp3"));
        name="warrior";
        order=2;
        initiative=1;
        hp=10;
        maxHp=10;
        attack=3;
        defense=1;
        xp=1;
        energy=8;
        maxEnergy=8;
        attackType="AC";
        mapPt=new HerosMapGUI(1, 10, -20, spriteSheet);
        MAX_WALK=20;
        MAX_ATTACK=14;
    }
    public boolean testIfCanActOn(Character character){ 
        boolean test=false;
        if(order==2 && character.getOrder()==0) test=true;
        return test;
    }
}
