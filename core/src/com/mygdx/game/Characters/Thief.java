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
public class Thief extends Heros{
    
    
    public Thief(){
        spriteSheet=new TextureAtlas(Gdx.files.internal("Characters/thief/thief.atlas"));
        attackSound=Gdx.audio.newSound(Gdx.files.internal("sounds/thiefAttack.mp3"));
        deadSound=Gdx.audio.newSound(Gdx.files.internal("sounds/thiefDead.mp3"));
        hp=8;
        attack=2;
        defense=1;
        xp=1;
        name="thief";
        order=0;
        initiative=3;
        energy=10;
        maxEnergy=10;
        maxHp=8;
        attackType="AD";
        mapPt=new HerosMapGUI(1, 15, -10, spriteSheet);
        MAX_WALK=19;
        MAX_ATTACK=10;
    }
}
