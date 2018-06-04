/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.CharactersGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Characters.Character;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Characters.Heros;
import com.mygdx.game.Characters.Monster;
import com.mygdx.game.DungeonGUI.Dungeon;

/**
 *
 * @author Laura
 */
public class CharactersFullGUI extends Actor{
    private Sprite sprite;
    private TextureRegion region;
    private TextureAtlas spriteSheet=null;
    private final Character heros;
    private int currentFrame =0, MAX_ATTACK, MAX_WALK, goTo;
    private boolean goOn=true;
    
   
    
    public CharactersFullGUI(TextureAtlas spriteSheet, final Character heros, boolean addListener){
        this.spriteSheet=spriteSheet;
        String action="walk";
        if(!heros.isAlive()){
            action="dead";
        }
        region = new TextureRegion(spriteSheet.findRegion(action));
        sprite=new Sprite(region);
        this.heros=heros;
        MAX_ATTACK=heros.getMaxAttack();
        MAX_WALK=heros.getMaxWalk();
        
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        if(heros.getName().equals("warrior")){ setWidth(230); }
        if(heros.getName().equals("mage")){ setWidth(130); }
        setY(50);
        setTouchable(Touchable.disabled);
        if(addListener){
            addListener(new ClickListener(){
                   @Override
                   public void clicked(InputEvent event, float x, float y) {
                      if(heros.getType()=="heros"){
                          heros.getFight().selectHeros((Heros) getHeros());
                      }else{
                          heros.getFight().attackMonster((Monster) getHeros());
                      }
                   }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if(heros.getType().equals("monster")){
                        String typeAttack=heros.getFight().getHerosActing().getAttackType();
                        Dungeon.getInstance().setCursor(typeAttack);
                        //heros.setAction("target"+heros.getFight().getHerosActing().getAttackType());
                    }
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    if(heros.getType().equals("monster")){
                        Dungeon.getInstance().setCursor("arrow");
                    }
                }
                
                   
                   
               });
            
        }
    }
    
    public Character getHeros(){
        return heros;
    }
    public void goTo(int to) {
        goOn=to>goTo;
        goTo=to;
    }
    public boolean canBePlayed(){ 
        if(heros.getType().equals("heros")){
            Heros h=(Heros) heros;
            return h.canBePlayed();
        }
        return true;
    }
    public void canBePlayed(boolean b) {
         if(heros.getType().equals("heros")){
            Heros h=(Heros) heros;
            h.canBePlayed(b);
            if(b){
                setTouchable(Touchable.enabled);
            }else{
                setTouchable(Touchable.disabled);
            }
         }
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
    
    @Override
    public void act(float delta) {
        if(heros.getActionChange()){
            currentFrame=0;
            heros.setActionChange(!heros.getActionChange());
        }
        if(heros.getAction().equals("walk") && goTo!=0){
            Gdx.app.log("CFGUI act walk", heros.getName()+"   - goOn ="+goOn+"   -  x="+getX()+"   - go To="+goTo);
            currentFrame++;
            if(currentFrame>=MAX_WALK) currentFrame=0;
            if(goOn && getX()<goTo){
                setX(getX()+8);
            }else if(!goOn && getX()>goTo){
                setX(getX()-8);
            }else{
                heros.setAction("nothing");
                if(heros.getFight()!=null) heros.getFight().actionFinished("walk", heros.getType());
                if(heros.isAlive()) sprite.setRegion(spriteSheet.findRegion( "walk", 0));
            }
            if(heros.isAlive()) sprite.setRegion(spriteSheet.findRegion( "walk", currentFrame));
            
        }
        if(heros.getAction().equals("attack")){
            
            currentFrame++;
            if(currentFrame>=MAX_ATTACK){
                currentFrame=0;
                heros.setAction("nothing");
                heros.getFight().actionFinished("attack", heros.getType());
                sprite.setRegion(spriteSheet.findRegion( "walk", currentFrame));
            }else{
                sprite.setRegion(spriteSheet.findRegion( "attack", currentFrame));
            }
            
        }
        if(heros.getAction().equals("selected")){
            heros.setAction("nothing");
            sprite.setRegion(spriteSheet.findRegion( "selected"));
        }
        if(heros.getAction().equals("dead")){
            if(getHeros().getType().equals("heros")) sprite.setRegion(spriteSheet.findRegion( "dead"));
            heros.setAction("nothing");
            if(heros.getFight()!=null) heros.getFight().actionFinished("dead", heros.getType());
        }
        if(heros.getAction().equals("nothing")){
            if(heros.isAlive()){
                if(heros.isSelected()){
                    sprite.setRegion(spriteSheet.findRegion( "selected"));
                }else{
                     sprite.setRegion(spriteSheet.findRegion( "walk",0));
                }
            }
        }
    }

    
    
    


    
    
    
    
         
}
