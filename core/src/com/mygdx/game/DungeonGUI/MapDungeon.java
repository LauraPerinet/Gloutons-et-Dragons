/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.DungeonGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.mygdx.game.Characters.Heros;
import com.mygdx.game.CharactersGUI.HerosMapGUI;
import com.mygdx.game.CharactersGUI.HerosPosition;
import com.mygdx.game.Characters.Mage;
import com.mygdx.game.Characters.Thief;
import com.mygdx.game.Characters.Warrior;
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author Laura
 * 
 * The map. Creation from "assets\levels\[numLevel].json" (for now, numLevel is always 1).
 * Manage if heroes can or not enter the room
 * 
 */
public class MapDungeon extends Group{
    private static MapDungeon INSTANCE;
    final private Skin skin;
    private Table table, leftTable;
    private final Image parchment=new Image(new Texture("parchment.jpg")), arrow=new Image(new Texture("arrowDragAndDrop.png")), arrow2=new Image(new Texture("arrowDragAndDrop.png"));
    private Tile tile, nextTile;
    private Thief thief;
    private Mage mage;
    private Warrior warrior;
    private Group heroesMapImg, map;
    private TextButton nextRoomBtn;
    final private int MAP_WIDTH=5;
    final private int MAP_HEIGHT=5;
    private HerosPosition herosLeftTable;
    
    
    private MapDungeon(Skin skin){
        this.skin=skin;
        setName("map");
        createBackground();
        nextRoomBtn = createButton();
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
    
    public static MapDungeon getInstance(){  return INSTANCE; }
    
    public static MapDungeon getInstance(Skin skin){
        if(INSTANCE==null){
            INSTANCE = new MapDungeon(skin);
        }
        return INSTANCE;
    }
    
    public Tile getTile(){return tile;}
    public Thief getThief(){  return thief;  }
    public Warrior getWarrior(){  return warrior;  }
    public Mage getMage(){  return mage;  }
    
    private void createBackground() {
        setBounds(parchment.getX(), parchment.getY(), parchment.getWidth(), parchment.getHeight());
        arrow.setPosition(1160, 450);
        arrow2.setPosition(1460,450);
        addActor(parchment);
        addActor(arrow);
        addActor(arrow2);
    }
    
    private Group createMap(int l) {
        map = new Group();
        JsonReader json=new JsonReader();
        JsonValue level=json.parse(Gdx.files.internal("levels/"+l+".json"));
        TextureAtlas spriteSheet=new TextureAtlas("room/tiles.atlas");
        
        for (JsonValue room : level.get("rooms")){
            Tile tile=new Tile(room.getInt("x"), room.getInt("y"), room.getString("type"), room.getString("orientation"), spriteSheet);
            map.addActor(tile);
           
            if(room.getBoolean("enter")){
                this.tile=tile;
                this.tile.getRoomGUI(true);
                this.tile.setState(Tile.visited);
            }else{
                tile.setState(Tile.hide);
            }
        }
        ArrayList<Tile> nextHideTiles=setTilesStates(tile, Tile.next);
        for(Tile t:nextHideTiles){
            setTilesStates(t, Tile.nextHide);
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
    
    private Group createHeros() {
        heroesMapImg=new Group();

        thief=new Thief();
        thief.getMapActor().setPositionOnMap(tile.getX()+tile.getWidth()/2, tile.getY()+tile.getHeight()/2);

        mage=new Mage();
        mage.getMapActor().setPositionOnMap( tile.getX()+tile.getWidth()/2, tile.getY()+tile.getHeight()/2);
        warrior=new Warrior();
        warrior.getMapActor().setPositionOnMap( tile.getX()+tile.getWidth()/2, tile.getY()+tile.getHeight()/2);
        
        heroesMapImg.addActor(thief.getMapActor());
        heroesMapImg.addActor(mage.getMapActor());
        heroesMapImg.addActor(warrior.getMapActor());
        
        return heroesMapImg;
    }

    private void createLeftTable() {
        Label title=new Label("L'appetit vient en chassant", skin, "default");
        herosLeftTable=new HerosPosition(warrior, thief, mage);
        leftTable = createTable(getWidth()/2);
        leftTable.add(title);
        leftTable.row();
        leftTable.add(herosLeftTable);
        leftTable.row();
        leftTable.add();
    }

    private TextButton createButton() {
        TextButton btn = new TextButton("Entrez dans la salle", skin, "default");
        btn.setWidth(150);
        btn.setHeight(50);
        btn.getLabel().setScale(0.8f);
        btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Iterator mapHeros=heroesMapImg.getChildren().iterator();
                while(mapHeros.hasNext()){
                    HerosMapGUI mapH=(HerosMapGUI) mapHeros.next();
                    mapH.moveHerosOnMap(nextTile);
                }
                leftTable.getCell(nextRoomBtn).clearActor();
                tile.setState(Tile.visited);
                nextTile.setState(Tile.selected);
                tile=nextTile;
                nextTile=null;
                
                ArrayList<Tile> nextHideTiles=setTilesStates(tile, Tile.next);
                for(Tile t:nextHideTiles){
                    setTilesStates(t, Tile.nextHide);
                }
                //Dungeon.getInstance().goTo( tile.getRoomGUI(false));
                //Dungeon.getInstance().goTo( tile.getBackground());
            }
        });
        return btn;
    }
    
    public void goToRoom(Tile room){ 
        for(Actor actor : map.getChildren()){
            if(actor.getName()!=null && actor.getName().equals("tile")){
                 Tile tile = (Tile) actor;
                 //tile.setState(Tile.selected);
                 //Gdx.app.log("Tile goToRoom", tile.getId()+"");
                 //tile.select(-1);
            } 
        }
        nextTile= room;
        if(room.getState()==Tile.visited)   room.setState(Tile.visitedSelected);
        if(room.getState()==Tile.next) room.setState(Tile.selected);
        
        Gdx.app.log("nextTile goToRoom", nextTile.getId()+"");
        //nextTile.select(1);
        
        leftTable.getCells().get(leftTable.getCells().size-1).setActor(nextRoomBtn).padTop(30);
    }

    public Heros getHeros(int index){
        if(mage.getOrder()==index) return mage;
        if(warrior.getOrder()==index ) return warrior;
        if(thief.getOrder()==index ) return thief;
        return null;
    }
    
    public boolean setHerosPosition() {
        Heros first=getHeros(2);
        getHeros(1).setOrder(2);
        getHeros(0).setOrder(1);
        first.setOrder(0);
       
        if(!getHeros(2).isAlive()){ 
            Dungeon.getInstance().gameOver();
            return false;
        }else{
            herosLeftTable.setHerosPosition(false);
            return true;
        }
    }
    @Override
    public void clear(){
        INSTANCE=null;
    }

    public Heros getLastHeros() {
        int i=0;
        while(!getHeros(i).isAlive()){ i++; }
        return getHeros(i);
    }

    private ArrayList<Integer> tilesAround(Tile tile) {
        ArrayList<Integer> tiles =new ArrayList<Integer>();
        if(tile.getMapY()>0) tiles.add(new Integer((int) (tile.getMapX()*10+tile.getMapY()-1)));   
        if(tile.getMapY()<MAP_HEIGHT-1) tiles.add(new Integer((int) (tile.getMapX()*10+tile.getMapY()+1)));   
        if(tile.getMapX()>0) tiles.add(new Integer ((int) ((tile.getMapX()-1)*10+tile.getMapY())));   
        if(tile.getMapX()<MAP_WIDTH-1) tiles.add(new Integer((int) ((tile.getMapX()+1)*10+tile.getMapY())));  
        
        return tiles;
    }

    private ArrayList<Tile> setTilesStates(Tile tile, int state) {
        ArrayList<Integer> idTileNear = tilesAround(tile); 
        ArrayList<Tile> nextHideTiles=new ArrayList<Tile>();
        for (Actor tileActor : map.getChildren()) {
            if(tileActor.getName()!=null && tileActor.getName().equals("tile")){
                Tile t = (Tile) tileActor;
                for(Integer id : idTileNear){
                    if(t.getId()== id && tile.canWeGo(t) && (t!=this.tile) && t.getState()!=Tile.visited){
                        t.setState(state);
                        if(state==Tile.next) nextHideTiles.add(t);
                    }

                }
            }
            
        }
        return nextHideTiles;
    }
    
    


 

    

}
