/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.CharactersGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.Characters.Heros;
/**
 *
 * @author Laura
 */
public class HerosPosition extends Group{
    private Heros warrior, thief, mage;
    private int[] position={-480, -160, 160};
    private float posY;

    public HerosPosition( Heros warrior, Heros thief, Heros mage){
        this.warrior=warrior; 
        this.thief=thief;
        this.mage=mage;
        posY=warrior.getImg().getY();
        addActorAt(warrior.getOrder(), warrior.getImg());
        addActorAt(thief.getOrder(), thief.getImg());
        addActorAt(mage.getOrder(), mage.getImg());
        
        setHeight(warrior.getImg().getHeight());
        
        setHerosPosition();
        Gdx.app.log("group", getChildren().size+"");
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
                if(target==null) setHerosPosition((Image) payload.getObject(), originX);
            }
            
        });
        dnd.addTarget(new DragAndDrop.Target(this){
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return true;
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                 setHerosPosition((Image)payload.getObject(), x);
            }

            
        });
    }

    private void setHerosPosition() {
        for(int i=0; i<position.length;i++){
            CharactersFullGUI h=(CharactersFullGUI) getChildren().get(i);
            h.setX(position[i]+ (320-getChildren().get(i).getWidth())/2);
            h.setY(posY);
            h.getHeros().setOrder(i);
        }
    }
    private void setHerosPosition(Image heros, float x) {
        for(int i=position.length-1; i>=0;i--){
            if (x > position[i] ){
                addActorAt(i, heros);
                setHerosPosition();
                break;
            }
        }
    }
}
