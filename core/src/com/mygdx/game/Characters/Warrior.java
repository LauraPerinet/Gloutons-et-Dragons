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
    private final TextureAtlas spriteSheet=new TextureAtlas("Characters/warrior/warrior.atlas");
    
    public Warrior(){
        this.name="warrior";
        this.order=3;
        this.mapPt=new HerosMapGUI(1, 10, -20, spriteSheet);
        this.img= new CharactersFullGUI(spriteSheet, this);
    }
}
