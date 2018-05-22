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
        this.spriteSheet=new TextureAtlas("Characters/thief/thief.atlas");
        
        this.name="thief";
        this.order=0;
        this.initiative=3;
        this.mapPt=new HerosMapGUI(1, 15, -10, spriteSheet);
        this.MAX_WALK=19;
        this.MAX_ATTACK=10;
        createActor();
    }
}
