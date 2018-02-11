/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/**
 *
 * @author Laura
 */
public class MainMenu extends Stage {
    private Game game;
    
    public MainMenu(Game game) {
        this.game=game;
        Image background=new Image(new Texture("parchment2.jpg"));
        Image title=new Image(new Texture("title.png"));
        final TextButton playButton = new TextButton("Entrez dans le dongeon", game.getSkin(), "default");
        playButton.setWidth(400);
        playButton.setHeight(80);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeStage();
            }
        });
        
        Table table = new Table();
        table.setWidth(getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        table.padTop(100);
        table.add(title).minWidth(title.getWidth()).minHeight(title.getHeight()).padBottom(100);
        table.row();
        table.add(playButton);
        
        addActor(background);
        addActor(table);
        
        Gdx.input.setInputProcessor(this);
    }
    private void changeStage() {
        game.changeStage();
    }
    
}
