package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Game extends ApplicationAdapter {
	   Stage map;
	
	@Override
	public void create () {
		map=new MapDungeon(new ScreenViewport());
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		map.act(Gdx.graphics.getDeltaTime());
                map.draw();
	}
	
	@Override
	public void dispose () {
		map.dispose();
	}
}
