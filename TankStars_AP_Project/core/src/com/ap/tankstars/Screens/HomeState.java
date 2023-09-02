package com.ap.tankstars.Screens;

import com.ap.tankstars.TankStars;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;
import java.io.Serializable;

public class HomeState implements Screen, Serializable {
    protected Vector3 mouse;
    private Texture background;
    private TankStars game;
    public HomeState(TankStars game){
        this.game=game;
        background = new Texture("BackGround2.png");
        mouse=new Vector3();
    }
    @Override
    public void show() {

    }
    public void handleInput(float dt){
        if(Gdx.input.justTouched()) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                System.out.println(mouse.x+" "+ mouse.y);
                if (mouse.x > 835 && mouse.x < 1170) {
                    if (mouse.y < 165 && mouse.y > 50) {
                        System.out.println("new game");
                        game.setScreen(new Choose_Tank1(this.game));
                        dispose();
                    } else if (mouse.y < 355 && mouse.y > 240) {
                        System.out.println("load game");
                        game.setScreen(new MenuState(this.game));
                        dispose();
                    } else if (mouse.y < 555 && mouse.y > 440) {
                        System.out.println("exit game");
                        game.setScreen(new ExitConfirmation(this.game));
                        dispose();
                    }
                }
            }
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

    }
}
