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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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

    private Table states,monsters;
    private Skin skin;
    
    public Menu(Skin skin) {
        this.skin=skin;
        setName("menu");
        setBounds(0, 0, background.getWidth(), background.getHeight());
        addActor(background);
        
        inventory=Inventory.getInstance().getImg();
        inventory.setY(10);
        heroes=new ArrayList<Heros>();
        states=new Table();
        monsters=new Table();
        heroes.add(MapDungeon.getInstance().getMage());
        heroes.add(MapDungeon.getInstance().getThief());
        heroes.add(MapDungeon.getInstance().getWarrior());
        states.left().padLeft(20);
        states.bottom().padBottom(10);
        
        monsters.left().padLeft(1000);
        monsters.bottom().padBottom(180);

        for(Heros heros : heroes){
            states.add(new StateHeros(heros, skin));
            states.row();
        }

        addActor(monsters);
        addActor(states);
        addActor(inventory);
    }
    
    public void addMonsters(Monster monster){
        monsters.padBottom(monsters.getPadBottom()-40);
        monsters.add(new StateHeros(monster, skin));
        monsters.row();
    }
    
    public void removeMonsters(){
        monsters.clear();
        monsters.bottom().padBottom(180);
    }

}
