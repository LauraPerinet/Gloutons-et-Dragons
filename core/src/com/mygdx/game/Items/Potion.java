/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Items.Items;
import java.util.Random;

/**
 *
 * @author PERINETM
 */
public class Potion extends Items {
    public String type;
    public TextureRegion region;
    
    public Potion(boolean fromRoom) {
        super("potions", fromRoom);
        this.fromRoom=fromRoom;
        
        int type = new Random().nextInt(3)+1;
        
        switch(type){
            case 1:this.type="healing"; break;
            case 2:this.type="energy"; break;
            case 3: this.type="strength"; break;
        }
        setImage();
        this.minX=800;
        this.maxX=1920- (int)getWidth() -minX;
        this.maxY=100;
    }
    public Potion(String type, Boolean fromRoom){
        super("potions", fromRoom);
        this.type=type;
        setImage();
    }
    private void setImage(){
        setName(this.type);
        region = new TextureRegion(new TextureAtlas("items/potions.atlas").findRegion(this.type));
        setDrawable(new TextureRegionDrawable(region));
        setSize(region.getRegionWidth(), region.getRegionHeight());
    }
}
