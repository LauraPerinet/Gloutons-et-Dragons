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
import com.mygdx.game.Fabricator;
import java.util.ArrayList;

/**
 *
 * @author Laura
 */
public class InventoryGUI extends Group{
     private Image background = new Image(new Texture(Gdx.files.internal("items/inventoryBg.png")));
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
        Gdx.app.log("item", item);
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

    
    public void addFood(String type) {
        for(int i=0; i<lockers.length; i++){
            if(lockers[i]==null){
                lockers[i]=type;
                Items it = Fabricator.createFood(type, false);
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
        item.setWidth(CASE_SIZE-5);
        item.setHeight(CASE_SIZE-5);
        addActor(item);
    }

    void remove(int lock) {
        lockers[lock]=null;
    }
    
    public ArrayList<Items> getItem(String name){
        ArrayList<Items> frigo=new ArrayList<Items>();
        for(Actor actor: getChildren()){
            if(actor.getName()!=null){
            if(actor.getName().equals(name)){
                if(name.equals("food")){
                    Food item=(Food) actor;
                    frigo.add(item);
                }else {
                    Items item=(Items) actor;
                    frigo.add(item);
                }
                
            }}
        }
        return frigo;
    }

    void removeItems(String name) {
        int i=0;
        for(Actor actor: getChildren()){
            if(i<4){
            if(actor.getName()!=null){
            if(actor.getName().equals(name)){
                Items it=(Items) actor;
                int index=it.getLock();
                lockers[index]=null;
                actor.remove();
                i++;
            }}}else{ break;}
            
        }
    }
    
}
