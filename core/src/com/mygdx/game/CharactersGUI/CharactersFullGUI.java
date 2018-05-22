/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.CharactersGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Characters.Character;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.utils.Timer;

/**
 *
 * @author Laura
 */
public class CharactersFullGUI extends Actor{
    private Sprite sprite;
    private TextureRegion region;
    private TextureAtlas spriteSheet;
    private Character heros;
    private int currentFrame =0, MAX_ATTACK, MAX_WALK;
     
    
    public CharactersFullGUI(TextureAtlas spriteSheet, Character heros){
        this.spriteSheet=spriteSheet;
        region = new TextureRegion(spriteSheet.findRegion("walk"));
        sprite=new Sprite(region);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setY(50);
        setTouchable(Touchable.enabled);

        this.heros=heros;
        this.MAX_ATTACK=heros.getMaxAttack();
        this.MAX_WALK=heros.getMaxWalk();
    }
    
    public Character getHeros(){
        return heros;
    }
    public void walk(float x) {
        //MoveByAction move=new MoveByAction();
        //move.setAmountX(x);
        //move.setDuration(5f);
        this.setX(-200);
        
        currentFrame=0;
        Action walkAnim=new Action(){
            @Override
            public boolean act(float Delta){
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        currentFrame++;
                        if(currentFrame>MAX_WALK) currentFrame=1;
                        sprite.setRegion(CharactersFullGUI.this.spriteSheet.findRegion( "walk", currentFrame));
                    }
                }, 0);
                return true;
            }
        };
        ParallelAction walk=new ParallelAction(walkAnim, Actions.moveTo(x, getY(),5f));
        
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
