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
public class Thief extends Heros{
    private final TextureAtlas spriteSheet=new TextureAtlas("Characters/thief/thief.atlas");
    
    public Thief(){
        this.name="thief";
        this.order=0;
        this.mapPt=new HerosMapGUI(1, 15, -10, spriteSheet);
        this.img= new CharactersFullGUI(spriteSheet, this);
    }
}
