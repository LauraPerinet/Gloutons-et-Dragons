/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.CharactersGUI;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 *
 * @author PERINETM
 */
public class Notification extends Group{
    CharactersFullGUI actor;
    String type;
    public Notification(CharactersFullGUI actor, Skin skin, String type){
        this.actor=actor;
        this.type=type;
        addActor(new Label(actor.getHeros().getLabel(), skin, type));
        setBounds(actor.getX()+actor.getWidth()/2, actor.getY()+200, 0, 0);
    }

    @Override
    public void act(float delta) {
        if(getY()<500){
            setY(getY()+5);
        }else{
            if(actor.getHeros().getFight()!=null) actor.getHeros().getFight().actionFinished(type, actor.getHeros().getType());
            remove();
        }
    }
    
    
}
