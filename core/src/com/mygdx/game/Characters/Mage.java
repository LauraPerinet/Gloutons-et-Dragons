/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.CharactersGUI.HerosMapGUI;

/**
 *
 * @author Laura
 */
public class Mage extends Heros{
    
    
    public Mage(){
        spriteSheet=new TextureAtlas("Characters/mage/mage.atlas");
        attackSound=Gdx.audio.newSound(Gdx.files.internal("sounds/mageAttack.mp3"));
        deadSound=Gdx.audio.newSound(Gdx.files.internal("sounds/mageDead.mp3"));
        this.name="mage";
        this.order=1;
        this.initiative=1;
        hp=5;
        attack=2;
        defense=0;
        xp=1;
        energy=5;
        maxEnergy=5;
        maxHp=5;
        attackType="AD";
        this.mapPt=new HerosMapGUI(1, -10, -10, spriteSheet);
        this.MAX_WALK=15;
        this.MAX_ATTACK=29;
    }
    public boolean testIfCanActOn(Character character){ 
        return true;
    }
}
