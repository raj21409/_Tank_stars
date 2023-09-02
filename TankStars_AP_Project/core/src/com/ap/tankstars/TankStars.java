package com.ap.tankstars;

import com.ap.tankstars.Screens.MenuState;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TankStars extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH = 1200;
	public static final int V_HEIGHT = 640;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MenuState(this));
	}

	@Override
	public void render () {
		super.render();
	}
	@Override
	public void dispose(){
		batch.dispose();
	}
}
