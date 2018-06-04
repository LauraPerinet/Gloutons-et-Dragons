/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import com.mygdx.game.DungeonGUI.Dungeon;
import com.mygdx.game.DungeonGUI.MapDungeon;
import com.mygdx.game.Items.Items;
import java.util.Random;

/**
 *
 * @author PERINETM
 */
public class Potion extends Items {
    public String type;
    public TextureRegion region;
    
    public Potion(boolean fromRoom) {
        super("potions", fromRoom);
        this.fromRoom=fromRoom;
        int type = new Random().nextInt(3)+1;
        draggable=true;
        
        switch(type){
            case 1:this.type="healing"; break;
            case 2:this.type="energy"; break;
            case 3: this.type="strength"; break;
        }
        setImage();
        this.minX=800;
        this.maxX=1920- (int)getWidth() -minX;
        this.maxY=100;
    }
    public Potion(String type, Boolean fromRoom){
        super("potions", fromRoom);
        this.type=type;
        setImage();
        Gdx.app.log("Potion", "touchable="+getTouchable());
        if(!fromRoom){
            Gdx.app.log("Potion", "touchable="+getTouchable());
            addDragAndDrop();
        }
    }
    private void setImage(){
        setName(this.type);
        region = new TextureRegion(new TextureAtlas("items/potions.atlas").findRegion(this.type));
        setDrawable(new TextureRegionDrawable(region));
        setSize(region.getRegionWidth(), region.getRegionHeight());
    }
    public void addDragAndDrop(){
        DragAndDrop dnd=new DragAndDrop();
        dnd.addSource(new DragAndDrop.Source(this){
            final DragAndDrop.Payload payload=new DragAndDrop.Payload();
            float originX=0;
            float originY=0;
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                Potion potion= (Potion) hit(x,y,true);
                originX=potion.getX();
                originY=potion.getY();
                payload.setObject(potion);
                payload.setDragActor(potion);
                
                
                return payload;
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                Items potion=(Items) payload.getDragActor();
                Inventory.getInstance().getImg().addItem(potion.getName(), potion.getLock());
            }
            
        });
        for(final Actor heros : Dungeon.getInstance().getRoom().getHeros().getChildren()){
            dnd.addTarget(new DragAndDrop.Target(heros){
                @Override
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    return true;
                }

                @Override
                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                     Gdx.app.log("Potion ", "Lachée sur "+heros.getName());
                }


            });
        }
    }
}
