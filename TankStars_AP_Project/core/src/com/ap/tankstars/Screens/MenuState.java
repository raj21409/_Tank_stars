package com.ap.tankstars.Screens;

import com.ap.tankstars.TankStars;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import java.io.Serializable;

public class MenuState implements Screen, Serializable {

    private final Texture background;
    private final TankStars game;
    public MenuState(TankStars game) {
        this.game=game;
        background = new Texture("BackGround1.png");
    }

    @Override
    public void show() {

    }
    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
            game.setScreen(new HomeState(this.game));
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background,0,0,(float)TankStars.V_WIDTH,(float)TankStars.V_HEIGHT);
        game.batch.end();
    }

    public void update(float delta){
        handleInput(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
    }

}
