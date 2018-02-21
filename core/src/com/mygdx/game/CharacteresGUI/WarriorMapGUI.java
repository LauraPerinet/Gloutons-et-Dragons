package com.mygdx.game.CharacteresGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Laura
 */
public class WarriorMapGUI extends HerosMapGUI{
    private TextureAtlas spriteSheet = new TextureAtlas("Characters/warrior/warrior.atlas");
    private TextureRegion region;

    public WarriorMapGUI( int position, float x, float y){
        super(position, 10, -5);
        region=spriteSheet.findRegion("pt");
        super.setSprite(region);
        
        setPosition(x-getWidth()/2+mapDeltaX, y-getHeight()/2 + mapDeltaY);
    }
    public void moveHerosOnMap(Actor tileTouched) { super.moveHerosOnMap(tileTouched);}
    
}
