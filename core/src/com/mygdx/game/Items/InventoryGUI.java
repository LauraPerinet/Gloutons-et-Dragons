/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.DungeonGUI.Dungeon;
import com.mygdx.game.Fabricator;

/**
 *
 * @author Laura
 */
public class InventoryGUI extends Group{
     private Image background = new Image(new Texture("items/inventoryBg.png"));
     private String[] lockers = new String[21]; 
     private final int MAX_CASE_X=8, MAX_CASE_Y=2, CASE_OUT=6, CASE_SIZE=(int) background.getWidth()/(MAX_CASE_X+1);
     private Label goldValue;
     private Skin skin;
     
     public InventoryGUI(Skin skin){
         this.skin=skin;
         setBounds(850, 0, background.getWidth(), background.getHeight());
         addActor(background);

         Group g=new Group();
         Gold gold = new Gold(false);
         g.addActor(gold);
         
         goldValue=new Label("0", skin, "default");
         g.setBounds(gold.getX(), gold.getY(), CASE_SIZE-20, CASE_SIZE-20);
         g.addActor(gold);
         g.addActor(goldValue);
         addItemOnCase(g, CASE_OUT-1, MAX_CASE_Y);
         
         
     }

    public void setGold(int newValue) {
        goldValue.setText(newValue+"");
    }

    public void addItem(String item) {
        for(int i=0; i<lockers.length; i++){
            if(lockers[i]==null){
                lockers[i]=item;
                Items it = Fabricator.createItem(item, false);
                it.setLock(i);
                i+=CASE_OUT;
                int y=i/9;
                int x=i%9;
                y= y==0 ? MAX_CASE_Y:y==MAX_CASE_Y?0:1; 
                
                addItemOnCase(it, x, y);
                break;
            }
        }
    }
    
    private void addItemOnCase(Actor item, int x, int y){
        float newX=x*CASE_SIZE+10;
        float newY=y*CASE_SIZE+10;
        item.setPosition(newX, newY);
        addActor(item);
    }
    private void addItemOnCase(Items item, int x, int y){
        float newX=x*CASE_SIZE+10;
        float newY=y*CASE_SIZE+10;
        item.setPositionOnInventory(newX, newY);
        addActor(item);
    }

    void remove(int lock) {
        lockers[lock]=null;
    }
    
}
