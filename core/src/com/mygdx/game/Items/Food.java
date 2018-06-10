/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;

/**
 *
 * @author Laura
 */
public class Food  extends Items{
    private String name;
    private TextureRegion region;
    
    public Food(CharactersFullGUI monster, boolean fromRoom) {
        super("food", fromRoom);
        this.fromRoom=fromRoom;
        draggable=true;
        type=monster.getHeros().getName();
        setImage();
        setX(monster.getX());
        setY(monster.getY());
        addClickToPick();
    }
    public Food(String type, Boolean fromRoom){
        super("food", fromRoom);
        this.type=type;
        setImage();
    }
    private void setImage(){
        region = new TextureRegion(new TextureAtlas(Gdx.files.internal("Characters/monsters/"+type+".atlas")).findRegion("meet"));
        setDrawable(new TextureRegionDrawable(region));
        setSize(region.getRegionWidth(), region.getRegionHeight());
    }
}