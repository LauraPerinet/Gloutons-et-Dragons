/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import java.util.Random;

/**
 *
 * @author Laura
 */
public class Gold extends Items{
    private int value;
    
    
    public Gold(boolean fromRoom){
        super("gold", fromRoom);
        setName("gold");
        if(fromRoom){
            int ran=new Random().nextInt(5)+1;
            value=ran;
            this.minX=800;
            this.maxY=70;
        }
    }
   
    public int getValue(){ return value;}
}
