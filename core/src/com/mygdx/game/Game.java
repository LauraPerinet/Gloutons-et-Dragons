package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends ApplicationAdapter {
    Stage dungeon;
    Viewport viewMainWindow;

    @Override
    public void create () {
             viewMainWindow=new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
             dungeon=new Dungeon(viewMainWindow);

    }

    @Override
    public void render () {
             Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
             dungeon.act(Gdx.graphics.getDeltaTime());
             dungeon.draw();

    }

    @Override
    public void dispose () {
            dungeon.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewMainWindow.update(width, height);
    }
        
}
