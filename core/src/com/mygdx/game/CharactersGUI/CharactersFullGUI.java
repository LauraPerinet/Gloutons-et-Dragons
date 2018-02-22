/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.CharactersGUI;

import com.mygdx.game.Characters.Heros;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author Laura
 */
public class CharactersFullGUI extends Image{
    private Sprite sprite;
    private Heros heros;
    
    public CharactersFullGUI(TextureAtlas spriteSheet, Heros heros){
        super(spriteSheet.findRegion("walk"));
        TextureRegion region=spriteSheet.findRegion("walk");
        this.sprite=new Sprite(region);
        this.heros=heros;
    }
    
    public Heros getHeros(){
        return heros;
    }
}
