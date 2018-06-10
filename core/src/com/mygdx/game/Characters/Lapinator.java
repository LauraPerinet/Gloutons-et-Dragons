/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author PERINETM
 */
public class Lapinator extends Monster {

    public Lapinator(String name) {
        super(name);
        spriteSheet=new TextureAtlas(Gdx.files.internal("Characters/monsters/lapinator.atlas"));
        attackSound=Gdx.audio.newSound(Gdx.files.internal("sounds/LapinatorAttack.mp3"));
        deadSound=Gdx.audio.newSound(Gdx.files.internal("sounds/LapinatorDead.mp3"));
        initiative=1;
        hp=4;
        attack=3;
        defense=0;
        xp=2;
        attackType="AC";
        
        this.MAX_WALK=0;
        this.MAX_ATTACK=23;
    }
    
}
