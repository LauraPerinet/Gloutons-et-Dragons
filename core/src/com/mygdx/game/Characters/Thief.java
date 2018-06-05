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
        spriteSheet=new TextureAtlas("Characters/thief/thief.atlas");
        hp=8;
        attack=2;
        defense=1;
        xp=1;
        name="thief";
        order=0;
        initiative=3;
        energy=4;
        maxEnergy=4;
        maxHp=8;
        attackType="AC";
        mapPt=new HerosMapGUI(1, 15, -10, spriteSheet);
        MAX_WALK=19;
        MAX_ATTACK=10;
    }
}
