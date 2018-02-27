/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import java.util.HashMap;

/**
 *
 * @author Laura
 */
public class Inventory {
    private static Inventory INSTANCE;
    private HashMap<String, Integer> items=new HashMap<String, Integer>();
    
    private Inventory(){}
    
    public static Inventory getInstance(){
        if(INSTANCE==null) INSTANCE = new Inventory();
        return INSTANCE;
    }
    
    public void addItem(Items item){
        if(item.getName().equals("gold")){ 
            Gold gold = (Gold) item;
            addGold(gold.getValue());
        }else{
            if(!item.getClass().equals("Food")){
               if(items.get(item.getName())!=null){
                    items.replace(item.getName(), items.get(item.getName())+1);
                }else{
                    items.put(item.getName(), 1);
                } 
            }
        } 
    }
    
    public void addGold(int value){
        if(items.get("gold")!=null){
            items.replace("gold", items.get("gold")+value);
        }else{
            items.put("gold", value);
        }
        Gdx.app.log("Gold", items.get("gold")+"");
    }
}
