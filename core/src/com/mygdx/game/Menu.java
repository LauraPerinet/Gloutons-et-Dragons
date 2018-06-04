/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.mygdx.game.CharactersGUI.StateHeros;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Characters.Heros;
import com.mygdx.game.Characters.Monster;
import com.mygdx.game.DungeonGUI.MapDungeon;
import com.mygdx.game.Items.Inventory;
import com.mygdx.game.Items.InventoryGUI;
import java.util.ArrayList;



/**
 *
 * @author Laura
 */
public class Menu extends Group{
    private InventoryGUI inventory;
    private Image background=new Image(new Texture("backgroundMenu.png"));
    private ArrayList<Heros> heroes;
    private Group states, monsters;
    private Skin skin;
    
    public Menu(Skin skin) {
        this.skin=skin;
        setName("menu");
        setBounds(0, 0, background.getWidth(), background.getHeight());
        addActor(background);
        
        inventory=Inventory.getInstance().getImg();
        inventory.setY(10);
        heroes=new ArrayList<Heros>();
        states=new Group();
        monsters=new Group();
        heroes.add(MapDungeon.getInstance().getMage());
        heroes.add(MapDungeon.getInstance().getThief());
        heroes.add(MapDungeon.getInstance().getWarrior());
        
        for(Heros heros : heroes){
            states.addActor(new StateHeros(heros, skin));
        }
        states.addActor(monsters);
        addActor(states);
        //addActor(inventory);
    }
    
    public void addMonsters(Monster monster){
        monsters.addActor(new StateHeros(monster, skin));
    }
    
    public void removeMonsters(){
        monsters.clear();
    }

}
