/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.Characters.Heros;
import com.mygdx.game.CharactersGUI.CharactersFullGUI;
import com.mygdx.game.DungeonGUI.Dungeon;
import java.util.Random;

/**
 *
 * @author PERINETM
 */
public class Potion extends Items {
    
    private TextureRegion region;
    
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
        setImage(this.type);
        this.minX=800;
        this.maxX=1920- (int)getWidth() -minX;
        this.maxY=100;
    }
    public Potion(String type, Boolean fromRoom){
        super("potions", fromRoom);
        this.type=type;
        setImage(this.type);

        if(!fromRoom){
            addDragAndDrop();
        }
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
                Inventory.getInstance().getImg().remove(potion.getLock());
                
                return payload;
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
               if(target==null){
                   Items potion=(Items) payload.getDragActor();
                    Inventory.getInstance().getImg().addItem(potion.getType());
               }
                
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
                    CharactersFullGUI herosActor=(CharactersFullGUI) heros; 
                    Heros h=(Heros) herosActor.getHeros();
                    Items potion=(Items) payload.getDragActor();
                    if(h.isAlive()){
                        h.drinkPotion(potion);
                        Dungeon.getInstance().getRoom().notif(h, potion.getType());
                    }else{
                        Inventory.getInstance().getImg().addItem(potion.getType());
                    }
                }


            });
        }
    }
}
