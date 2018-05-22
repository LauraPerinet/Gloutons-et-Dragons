/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.CharactersGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.Characters.Character;
/**
 *
 * @author Laura
 * 
 * Manage heros's position on map screen, whith drag and drop to decide in wich order heros go on rooms
 */
public class HerosPosition extends Group{
    private Character warrior, thief, mage;
    private int[] position={-480, -160, 160};
    private float posY;

    public HerosPosition( Character warrior, Character thief, Character mage){
        this.warrior=warrior; 
        this.thief=thief;
        this.mage=mage;
        
        
        posY=warrior.getActor().getY();
        setHeight(warrior.getActor().getHeight());
        
        addActorAt(warrior.getOrder(), warrior.getStaticActor());
        addActorAt(thief.getOrder(), thief.getStaticActor());
        addActorAt(mage.getOrder(), mage.getStaticActor());
        
        setHerosPosition();
        
        addDragAndDrop();
    }

    public void setHerosPosition() {
        for(int i=0; i<position.length;i++){
            CharactersFullGUI h=(CharactersFullGUI) getChildren().get(i);
            h.setX(position[i]+ (320-getChildren().get(i).getWidth())/2);
            h.setY(posY);
            h.getHeros().setOrder(i);
        }
    }
    private void setHerosPosition(Actor heros, float x) {
        for(int i=position.length-1; i>=0;i--){
            if (x > position[i] ){
                addActorAt(i, heros);
                setHerosPosition();
                break;
            }
        }
    }

    private void addDragAndDrop() {
        DragAndDrop dnd=new DragAndDrop();
        dnd.addSource(new DragAndDrop.Source(this){
            final DragAndDrop.Payload payload=new DragAndDrop.Payload();
            float originX=0;
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                originX=x;
                Actor dragHeros=hit(x,y,true);
 
                payload.setObject(dragHeros);
                payload.setDragActor(dragHeros);
                return payload;
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                if(target==null) setHerosPosition((Actor) payload.getObject(), originX);
            }
            
        });
        dnd.addTarget(new DragAndDrop.Target(this){
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return true;
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                 setHerosPosition((Actor)payload.getObject(), x);
            }

            
        });
    }
}
