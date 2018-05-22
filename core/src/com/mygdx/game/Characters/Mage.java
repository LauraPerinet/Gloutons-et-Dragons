/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.CharactersGUI.HerosMapGUI;

/**
 *
 * @author Laura
 */
public class Mage extends Heros{
    
    
    public Mage(){
        spriteSheet=new TextureAtlas("Characters/mage/mage.atlas");
        
        this.name="mage";
        this.order=1;
        this.initiative=1;
        this.mapPt=new HerosMapGUI(1, -10, -10, spriteSheet);
        this.MAX_WALK=29;
        this.MAX_ATTACK=29;
        createActor();
    }

}
