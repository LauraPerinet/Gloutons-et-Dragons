/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author Laura
 */
public class RoomGUI extends Group{
    //private Image background;
    public RoomGUI(){
        setName("room");
        Image background=new Image(new Texture("chateau.jpg"));
        background.setName("background");
        addActor(background);
    }
    
}
