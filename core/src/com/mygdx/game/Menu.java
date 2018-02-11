/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


/**
 *
 * @author Laura
 */
public class Menu extends Group{
    
    private Image background=new Image(new Texture("backgroundMenu.png"));
    public Menu() {
        setName("menu");
        setBounds(0, 0, background.getWidth(), background.getHeight());
        addActor(background);
        
    }

    public void touchDown(int screenX, int screenY) {
        Gdx.app.log("Menu", "touché");
    }
}
