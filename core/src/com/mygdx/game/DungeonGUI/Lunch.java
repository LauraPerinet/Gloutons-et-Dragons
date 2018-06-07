/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.DungeonGUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author PERINETM
 */
public class Lunch extends Group {

    public Lunch() {
        addActor(new Image(new Texture("room/castleLunch.jpg")));
    }
    
}
