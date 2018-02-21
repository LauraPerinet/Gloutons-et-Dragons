/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.CharacteresGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Laura
 */
public class HerosPosition extends Group{
    private Image warrior;
    private Image thief;
    private Image mage;
    private TextureAtlas spriteSheet = new TextureAtlas("Characters/heros.atlas");

    public HerosPosition( int posWarrior, int posThief, int posMage){
        warrior=createImage(warrior, "warrior"); 
        thief=createImage(thief, "thief"); 
        mage=createImage(mage, "mage"); 
     
        addActorAt(posWarrior, warrior);
        addActorAt(posThief, thief);
        addActorAt(posMage, mage);
        
        setHeight(warrior.getHeight());
        for(int i=0; i<getChildren().size;i++){
            getChildren().get(i).setX(i*200-200);
        }
    }

    private Image createImage(Image heros, String typeHeros) {
        TextureRegion herosImg=spriteSheet.findRegion(typeHeros);
        heros=new Image(herosImg);
        heros.setName(typeHeros);
        heros.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("clic", "clicq");
            }
            
        });
        return heros;
    }
}
