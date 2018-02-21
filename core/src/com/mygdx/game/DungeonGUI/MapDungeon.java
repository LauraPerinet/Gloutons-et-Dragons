/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.DungeonGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.CharacteresGUI.HerosPosition;
import com.mygdx.game.CharacteresGUI.MageMapGUI;
import com.mygdx.game.CharacteresGUI.ThiefMapGUI;
import com.mygdx.game.CharacteresGUI.WarriorMapGUI;

/**
 *
 * @author Laura
 */
class MapDungeon extends Group{
    private static MapDungeon INSTANCE;
    final private Skin skin;
    private Table table, leftTable;
    Image parchment=new Image(new Texture("parchment.jpg"));
    private Tile tile;
    private ThiefMapGUI thief;
    private MageMapGUI mage;
    private WarriorMapGUI warrior;
    final private int MAP_WIDTH=5;
    final private int MAP_HEIGHT=5;
    
    private MapDungeon(Skin skin){
        this.skin=skin;
        setName("map");
        setBounds(parchment.getX(), parchment.getY(), parchment.getWidth(), parchment.getHeight());
        addActor(parchment);
        
        Group map=createMap(1);        
        
        Label title=new Label("L'appetit vient en chassant", skin, "default");
        
        table = createTable(getWidth());
        leftTable = createTable(getWidth()/2);
        leftTable.add(title);
        leftTable.row();
        leftTable.add(new HerosPosition(warrior.getPosition(), thief.getPosition(), mage.getPosition()));

        table.add(map)
                .width(getWidth()/2)
                .height(getHeight()-100) //100 = padding bottom
                .padLeft((getWidth()/2-map.getWidth())/2);
        table.add(leftTable).width(getWidth()/2).height(getHeight());
        addActor(table);
        Gdx.app.log("tile", tile.getName());
    }
    
    public static MapDungeon getInstance(){
        return INSTANCE;
    }
    public static MapDungeon getInstance(Skin skin){
        if(INSTANCE==null){
            INSTANCE = new MapDungeon(skin);
        }
        return INSTANCE;
    }
    
    private Group createMap(int l) {
        Group map = new Group();
        JsonReader json=new JsonReader();
        JsonValue level=json.parse(Gdx.files.internal("levels/"+l+".json"));
        
        for (JsonValue room : level.get("rooms")){
            Tile tile=new Tile(room.getInt("x"), room.getInt("y"), room.getString("type"), room.getString("orientation"));
            map.addActor(tile);
            if(room.getBoolean("enter"))
                this.tile=tile;

        }
        map.setBounds(map.getChildren().first().getX(), 
                map.getChildren().first().getY(), 
                map.getChildren().first().getWidth()*MAP_WIDTH, 
                map.getChildren().first().getWidth()*MAP_HEIGHT
                );
        
        thief=new ThiefMapGUI(0, "map", tile.getX()+tile.getWidth()/2, tile.getY()+tile.getHeight()/2);
        mage=new MageMapGUI(1, "map", tile.getX()+tile.getWidth()/2, tile.getY()+tile.getHeight()/2);
        warrior=new WarriorMapGUI(2, "map", tile.getX()+tile.getWidth()/2, tile.getY()+tile.getHeight()/2);
        
        map.addActor(thief);
        map.addActor(mage);
        map.addActor(warrior);
        
        return map;
    }

    private Table createTable(float width) {
        Table t = new Table();
        t.setWidth(width);
        t.align(Align.left|Align.top);
        t.setPosition(getX(), getHeight());
        return t;
    }
    public void goToRoom(Tile room){
        thief.moveHerosOnMap(room);
        warrior.moveHerosOnMap(room);
        mage.moveHerosOnMap(room);
        tile= room;
        Dungeon.getInstance().goTo();
    }
    
    public Tile getTile(){
        return tile;
    }

}
