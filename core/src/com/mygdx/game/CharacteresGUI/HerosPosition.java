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
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
/**
 *
 * @author Laura
 */
public class HerosPosition extends Group{
    private Image warrior;
    private Image thief;
    private Image mage;
    private TextureAtlas spriteSheet = new TextureAtlas("Characters/heros.atlas");
    private int[] position={-480, -160, 160};
    private float posY;

    public HerosPosition( int posWarrior, int posThief, int posMage){
        warrior=createImage(warrior, "warrior"); 
        thief=createImage(thief, "thief"); 
        mage=createImage(mage, "mage"); 
        posY=warrior.getY();
        addActorAt(posWarrior, warrior);
        addActorAt(posThief, thief);
        addActorAt(posMage, mage);
        
        setHeight(warrior.getHeight());
        
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
                Gdx.app.log("pos", "drop ok");
                 setHerosPosition((Image)payload.getObject(), x);
            }

            
        });
    }

    private Image createImage(Image heros, String typeHeros) {
        TextureRegion herosImg=spriteSheet.findRegion(typeHeros);
        heros=new Image(herosImg);
        heros.setName(typeHeros);
        return heros;
    }

    private void setHerosPosition() {
        for(int i=0; i<position.length;i++){
            Gdx.app.log(i+"",getChildren().get(i).getName());
            getChildren().get(i).setX(position[i]+ (320-getChildren().get(i).getWidth())/2);
            getChildren().get(i).setY(posY);
        }
    }
    private void setHerosPosition(Image heros, float x) {
        for(int i=position.length-1; i>=0;i--){
            if (x > position[i] ){
                Gdx.app.log("ajoute à l'index", i+"");
                addActorAt(i, heros);
                setHerosPosition();
                break;
            }
        }
    }
}
