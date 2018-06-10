/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.CharactersGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Characters.Heros;
import com.mygdx.game.Characters.Character;
import com.mygdx.game.Characters.Monster;
import com.mygdx.game.Rectangle;

/**
 *
 * @author Laura
 */
public class StateHeros extends Table {
    private Heros heros;
    private Monster monster;
    private int width=200, maxHP, maxEnergy;
    private Rectangle health, energy;
    private Image cadre, cadreBis;
    private Label healthL, energyL;

    public StateHeros(Heros heros, Skin skin) {
        this.heros=heros;
        cadre=new Image(new Texture(Gdx.files.internal("menu/cadre.png")));
        cadreBis=new Image(new Texture(Gdx.files.internal("menu/cadre.png")));
        setBounds(heros.getOrder()*220+20, 0, 220, 260);

        skin.getFont("little").getData().setScale(0.5f,0.5f);

        Label name=new Label(heros.getName(), skin, "little");
        Label level=new Label("Init "+heros.getInit(), skin, "verylittle");
        heros.setStatesGUI(this);
        maxHP=heros.getHp();
        health=new Rectangle(5, 5, width, 15, Color.FOREST);
        energy=new Rectangle(5, 5, width, 15, Color.BLUE);
        maxEnergy=heros.getEnergy();
        healthL=new Label(heros.getHp()+"/"+maxHP, skin, "verylittle");
        energyL=new Label(heros.getEnergy()+"/"+maxEnergy, skin, "verylittle");
        add(name).align(Align.left);
        add(level).align(Align.right);
        row();
        Group h=new Group();
        h.addActor(health);
        h.addActor(cadre);
        h.setBounds(0, 0, cadre.getWidth(), cadre.getHeight());
        
        Group e=new Group();
        e.addActor(energy);
        e.addActor(cadreBis);
        e.setBounds(0, 0, cadre.getWidth(), cadre.getHeight());
        
        add(h).align(Align.left);
        add(healthL).align(Align.right);
        row();
        add(e).padTop(10).align(Align.left);
        add(energyL).align(Align.right);
        
    }
    public StateHeros(Monster monster, Skin skin) {
        this.monster=monster;
        Label name=new Label(monster.getName(), skin, "little");
        Label level=new Label("Init "+monster.getInit(), skin, "verylittle");
        cadre=new Image(new Texture(Gdx.files.internal("menu/cadre.png")));
        cadreBis=new Image(new Texture(Gdx.files.internal("menu/cadre.png")));
        
        monster.setStatesGUI(this);
        maxHP=monster.getHp();
        health=new Rectangle(5, 5, width, 15, Color.FOREST);
        healthL=new Label(monster.getHp()+"/"+maxHP, skin, "verylittle");
        Group h=new Group();
        h.addActor(health);
        h.addActor(cadre);
        h.setBounds(0, 0, cadre.getWidth(), cadre.getHeight());
        
        add(name).align(Align.left);
        add(level).align(Align.right);
        row();
        add(h).align(Align.left);
        add(healthL).align(Align.right);
    }
    
    public void setHealth(Character heros){
        int hpGUI=width/maxHP*heros.getHp();
        if(hpGUI<0) hpGUI=0;
        healthL.setText(heros.getHp()+"/"+maxHP);
        health.setWidth(hpGUI);
    }
    private Group getGroup(Rectangle rect){
        Group hp=new Group();
        hp.addActor(rect);
        hp.addActor(cadre);
        return hp;
    }

    public void setEnergy(Heros aThis) {
        Gdx.app.log("Energy "+aThis.getName()+" "+energy+"", "");
        int energ=width/maxEnergy*heros.getEnergy();
        if(energ<0) energ=0;
        energyL.setText(heros.getEnergy()+"/"+maxEnergy);
        energy.setWidth(energ);
    }
    
}
