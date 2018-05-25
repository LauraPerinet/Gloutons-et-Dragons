/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import com.mygdx.game.CharactersGUI.HerosMapGUI;

/**
 *
 * @author Laura
 */
public class Warrior extends Heros{

    public Warrior(){
        spriteSheet=new TextureAtlas("Characters/warrior/warrior.atlas");
        name="warrior";
        order=2;
        initiative=1;
        hp=10;
        attack=3;
        defense=1;
        xp=1;
        mapPt=new HerosMapGUI(1, 10, -20, spriteSheet);
        MAX_WALK=59;
        MAX_ATTACK=39;
        

    }
}
