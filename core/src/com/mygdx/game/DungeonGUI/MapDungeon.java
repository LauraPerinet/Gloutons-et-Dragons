/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.DungeonGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.CharactersGUI.HerosMapGUI;
import com.mygdx.game.CharactersGUI.HerosPosition;
import com.mygdx.game.Characters.Mage;
import com.mygdx.game.Characters.Thief;
import com.mygdx.game.Characters.Warrior;
import java.util.Iterator;


/**
 *
 * @author Laura
 */
class MapDungeon extends Group{
    private static MapDungeon INSTANCE;
    final private Skin skin;
    private Table table, leftTable;
    private Image parchment=new Image(new Texture("parchment.jpg"));
    private Tile tile, nextTile;
    private Thief thief;
    private Mage mage;
    private Warrior warrior;
    private Group heroes;
    final private int MAP_WIDTH=5;
    final private int MAP_HEIGHT=5;
    
    private MapDungeon(Skin skin){
        this.skin=skin;
        setName("map");
        createBackground();
        
        Group map=createMap(1);        

        table = createTable(getWidth());
        createLeftTable();
        
        table.add(map)
                .width(getWidth()/2)
                .height(getHeight()-100) //100 = padding bottom
                .padLeft((getWidth()/2-map.getWidth())/2);
        table.add(leftTable).width(getWidth()/2).height(getHeight());
        addActor(table);
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
    
    private void createBackground() {
        setBounds(parchment.getX(), parchment.getY(), parchment.getWidth(), parchment.getHeight());
        addActor(parchment);
    }
    
    private Group createMap(int l) {
        Group map = new Group();
        JsonReader json=new JsonReader();
        JsonValue level=json.parse(Gdx.files.internal("levels/"+l+".json"));
        TextureAtlas spriteSheet=new TextureAtlas("tiles/tiles.atlas");
        
        for (JsonValue room : level.get("rooms")){
            Tile tile=new Tile(room.getInt("x"), room.getInt("y"), room.getString("type"), room.getString("orientation"), spriteSheet);
            map.addActor(tile);
            if(room.getBoolean("enter"))
                this.tile=tile;

        }
        map.setBounds(map.getChildren().first().getX(), 
                map.getChildren().first().getY(), 
                map.getChildren().first().getWidth()*MAP_WIDTH, 
                map.getChildren().first().getWidth()*MAP_HEIGHT
                );
        
        
        map.addActor(createHeros());
        return map;
    }

    private Table createTable(float width) {
        Table t = new Table();
        t.setWidth(width);
        t.align(Align.left|Align.top);
        t.setPosition(getX(), getHeight());
        return t;
    }
    public void goToRoom(Tile room){ nextTile= room; }
    
    public Tile getTile(){return tile;}

    private Group createHeros() {
        heroes=new Group();
        thief=new Thief();
        thief.getMapActor().setPositionOnMap(tile.getX()+tile.getWidth()/2, tile.getY()+tile.getHeight()/2);

        mage=new Mage();
        mage.getMapActor().setPositionOnMap( tile.getX()+tile.getWidth()/2, tile.getY()+tile.getHeight()/2);
        warrior=new Warrior();
        warrior.getMapActor().setPositionOnMap( tile.getX()+tile.getWidth()/2, tile.getY()+tile.getHeight()/2);
        
        heroes.addActor(thief.getMapActor());
        heroes.addActor(mage.getMapActor());
        heroes.addActor(warrior.getMapActor());
        return heroes;
    }

    private void createLeftTable() {
        Label title=new Label("L'appetit vient en chassant", skin, "default");
        TextButton nextRoom = new TextButton("Entrer dans la salle dans cet ordre", skin, "default");
        nextRoom.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Iterator mapHeros=heroes.getChildren().iterator();
                while(mapHeros.hasNext()){
                    HerosMapGUI mapH=(HerosMapGUI) mapHeros.next();
                    mapH.moveHerosOnMap(nextTile);
                    tile=nextTile;
                }

                Dungeon.getInstance().goTo();
            }
        });

        leftTable = createTable(getWidth()/2);
        leftTable.add(title);
        leftTable.row();
        leftTable.add(new HerosPosition(warrior, thief, mage));
        leftTable.row();
        leftTable.add(nextRoom);
    }

    

}
