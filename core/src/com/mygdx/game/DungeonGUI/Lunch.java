/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.DungeonGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Characters.Heros;
import com.mygdx.game.Daytime;
import com.mygdx.game.Items.Inventory;

/**
 *
 * @author PERINETM
 */
public class Lunch extends Group {
    private Image background;
    public Lunch() {
        setName("Lunch");
        background=new Image(new Texture(Gdx.files.internal("room/castleLunch.jpg")));
        setBounds(0, 0, background.getWidth(), background.getHeight());
        addActor(background);
        Table btns=new Table();
        /*
        TextButton newDay=new TextButton("Commencer un nouveau jour", Dungeon.getInstance().getSkin(), "default");
        
        MapDungeon.getInstance().getMage().setMaxEnergy();
        MapDungeon.getInstance().getWarrior().setMaxEnergy();
        MapDungeon.getInstance().getThief().setMaxEnergy();
        
        MapDungeon.getInstance().getMage().setHp(1);
                     MapDungeon.getInstance().getWarrior().setHp(1);
                     MapDungeon.getInstance().getThief().setHp(1);
        
        newDay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Daytime.getInstance().newDay();
                Dungeon.getInstance().goTo();
            }
            
        });
        btns.add(newDay);
        
        addActor(btns);    
      
      */
      if(Inventory.getInstance().getItem("food").size()>=3||Inventory.getInstance().getItem("herb").size()>=3){
        if(Inventory.getInstance().getItem("food").size()>=3){
      
            btns.add(button("energy"));
            btns.row();
        }
        if(Inventory.getInstance().getItem("herb").size()>=3){
 
            btns.add(button("health"));
            btns.row();
        }
      }else{

          TextButton newDay=new TextButton("Commencer un nouveau jour", Dungeon.getInstance().getSkin(), "default");
        newDay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                 MapDungeon.getInstance().getMage().sethalfEnergy();
                MapDungeon.getInstance().getWarrior().sethalfEnergy();
                 MapDungeon.getInstance().getThief().sethalfEnergy();   
                Daytime.getInstance().newDay();
                Dungeon.getInstance().goTo();
            }
            
        });
        btns.add(newDay);
        }
        addActor(btns);
       
    }
    private TextButton button(final String type){
        String label;
        if(type.equals("energy")){
            label="Depensez 3 nourritures pour recevoir de l'energie";
        }else{
            label="Depensez 3 herbes magiques pour recevoir 3 pv par heros.";
        }

        TextButton btn = new TextButton(label, Dungeon.getInstance().getSkin(), "default");
        btn.setWidth(500);
        btn.setHeight(50);
        btn.getLabel().setScale(0.8f);
        btn.addListener(new ClickListener(){
            MapDungeon d=MapDungeon.getInstance();
            Heros mage=d.getMage();
            Heros warrior=d.getWarrior();
            Heros thief=d.getThief();
             @Override
            public void clicked(InputEvent event, float x, float y) {
                if(type.equals("energy")){
                    Inventory.getInstance().removeItem("food");
                    mage.setMaxEnergy();
                    warrior.setMaxEnergy();
                    thief.setMaxEnergy();
                }else {
                    Inventory.getInstance().removeItem("herb");
                    mage.setHp(3);
                    warrior.setHp(3);
                     thief.setHp(3);
                     mage.sethalfEnergy();
                     warrior.sethalfEnergy();
                     thief.sethalfEnergy();     
                }
                Daytime.getInstance().newDay();
                Dungeon.getInstance().goTo();
            }
        });
        return btn;
    }

    private EventListener getListener(final String type) {
        ClickListener click=new ClickListener(){
           
            
        };
         return click;      
    }

    
}
