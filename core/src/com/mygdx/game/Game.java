package com.mygdx.game;

import com.mygdx.game.DungeonGUI.Dungeon;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends ApplicationAdapter {
    private Stage dungeon, mainMenu, actual;
    private Viewport viewMainWindow;
    private Skin skin;
    
    @Override
    public void create () {
             skin = new Skin(Gdx.files.internal("uiskin.json"));
             viewMainWindow=new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
             mainMenu = new MainMenu(this);
             actual=mainMenu;
             changeStage();
    }

    @Override
    public void render () {
             Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
             actual.act(Gdx.graphics.getDeltaTime());
             actual.draw();

    }

    @Override
    public void dispose () {
            actual.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewMainWindow.update(width, height);
    }
    public Skin getSkin(){
        return skin;
    }

    void changeStage() {
        dungeon=new Dungeon(viewMainWindow, this);
        actual=dungeon;
    }
        
}
