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
        this.spriteSheet=new TextureAtlas("Characters/warrior/warrior.atlas");
        this.name="warrior";
        this.order=3;
        this.mapPt=new HerosMapGUI(1, 10, -20, spriteSheet);
        this.MAX_WALK=59;
        this.MAX_ATTACK=39;
        

    }
}
