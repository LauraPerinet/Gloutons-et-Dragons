/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


/**
 *
 * @author PERINETM
 */
public class Daytime extends Group{
    private static Daytime INSTANCE;
    private int hour=0, maxHour=2500;
    private Rectangle time;
    private Image astre;
    private Label label;
    private TextureAtlas txtAtlas;
    
    private Daytime(){
        txtAtlas=new TextureAtlas(Gdx.files.internal("items/pictos.atlas"));
        astre=new Image(new TextureRegion(txtAtlas.findRegion("sun")));
        time=new Rectangle(0, 0, 250, 10, Color.BLUE);
        addActor(time);
        addActor(astre);
        setBounds(1600, 700, 250, 40);
    } 
    
    public static Daytime getInstance(){
        if(INSTANCE==null){
            INSTANCE=new Daytime();
        }
        return INSTANCE;
    }
    public String getMoment(){ 
        String moment="Day";
        if(hour>=maxHour*0.75) moment="Night";
        if(hour>=maxHour) moment="Lunch";
        
        return moment;
    }
    public void newDay(){
        hour=0;
        astre.setX(0);
        astre.setDrawable(new TextureRegionDrawable(new TextureRegion(txtAtlas.findRegion("sun"))));
    }
    @Override
    public void act(float delta) {
        hour++;
        if(hour%10==0 && hour<=maxHour) astre.setX(astre.getX()+1);
        if(hour==maxHour*0.75){
            astre.setDrawable(new TextureRegionDrawable(new TextureRegion(txtAtlas.findRegion("moon"))));
        }
    }
    
    
    
    
}
