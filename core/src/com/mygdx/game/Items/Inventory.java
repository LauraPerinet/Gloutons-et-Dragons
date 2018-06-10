/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Laura
 */
public class Inventory {
    private static Inventory INSTANCE;
    private HashMap<String, Integer> items=new HashMap<String, Integer>();
    private InventoryGUI inventoryGUI;
    
    private Inventory(Skin skin){
        inventoryGUI = new InventoryGUI(skin);
    }
    
    public static Inventory getInstance(Skin skin){
        if(INSTANCE==null) INSTANCE = new Inventory(skin);
        return INSTANCE;
    }
    public static Inventory getInstance(){
        return INSTANCE;
    }
    
    public void addItem(Items item){
        if(item.getName().equals("gold")){ 
            Gold gold = (Gold) item;
            addGold(gold.getValue());
            inventoryGUI.setGold(items.get("gold"));
        }else{
            if(!item.getClass().equals("Food")){
               if(items.get(item.getName())!=null){
                    items.replace(item.getName(), items.get(item.getName())+1);
                }else{
                    items.put(item.getName(), 1);
                } 
            }

            if(item.getDraggable() && item.getName().equals("potions")){
                
                Potion p=(Potion) item;
                p.addDragAndDrop();
            }
            if(item.getName().equals("food")){
                inventoryGUI.addFood(item.getType());
            }else if(item.getName().equals("potions")){
                
                Potion p=(Potion) item;
                Gdx.app.log(item.getName(), p.getType());
                 inventoryGUI.addItem(p.getType());
            }else{
                
                inventoryGUI.addItem(item.getName());
            }
            
        } 

    }
    public void addGold(int value){
        if(items.get("gold")!=null){
            items.replace("gold", items.get("gold")+value);
        }else{
            items.put("gold", value);
        }
        
    }
    
    public InventoryGUI getImg(){ return inventoryGUI;}
    public void clear(){
        INSTANCE=null;
    }
    public ArrayList<Items> getItem(String name){ return inventoryGUI.getItem(name);};

    public void removeItem(String name) {
        inventoryGUI.removeItems(name);
    }
}
