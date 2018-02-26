/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.util.Random;

/**
 *
 * @author Laura
 */
public class Gold extends Items{
    private int value;
    
    
    public Gold(){
        super(new Texture("items/gold.png"));
        setName("gold");
        int ran=new Random().nextInt(4)+1;
        value=10*ran;
        this.minX=800;
        this.maxY=70;
        
        setPosition(new Random().nextInt(maxX)+minX, new Random().nextInt(maxY)+minY);
        Gdx.app.log("create gold", getX()+"    "+getY());
    }
    public int getValue(){ return value;}
}
