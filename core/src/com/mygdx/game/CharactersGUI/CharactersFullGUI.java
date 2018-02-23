/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.CharactersGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Characters.Heros;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;

/**
 *
 * @author Laura
 */
public class CharactersFullGUI extends Actor{
    private Sprite sprite;
    private TextureRegion region;
    private TextureAtlas spriteSheet;
    private Heros heros;
    private int currentFrame =0, MAX_ATTACK, MAX_WALK;
    
    
    private Action walk=new Action(){
        final Image img = (Image) getActor();
        @Override
        public boolean act(float Delta){
            currentFrame++;
            if(currentFrame>MAX_WALK) currentFrame=1;
            sprite.setRegion(CharactersFullGUI.this.spriteSheet.findRegion( "walk", currentFrame));
            return true;
        }
    };
    
    public CharactersFullGUI(TextureAtlas spriteSheet, Heros heros){
        this.spriteSheet=spriteSheet;
        region = new TextureRegion(spriteSheet.findRegion("walk"));
        sprite=new Sprite(region);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);

        this.heros=heros;
        this.MAX_ATTACK=heros.getMaxAttack();
        this.MAX_WALK=heros.getMaxWalk();
    }
    
    public Heros getHeros(){
        return heros;
    }
    public void walk(float x) {
        MoveByAction move=new MoveByAction();
        move.setAmountX(x);
        move.setDuration(2.3f);
        addAction(walk);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(), getY()); //To change body of generated methods, choose Tools | Templates.
        super.positionChanged();
    }
    
    
    
    
         
}
