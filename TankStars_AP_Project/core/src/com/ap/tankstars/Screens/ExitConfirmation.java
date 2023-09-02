package com.ap.tankstars.Screens;

import com.ap.tankstars.TankStars;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.io.Serializable;

public class ExitConfirmation implements Screen, Serializable {
    protected Vector3 mouse;

    private final Texture background;
    private final TankStars game;
    public ExitConfirmation(TankStars game) {
        this.game=game;
        background = new Texture("ExitConfirmation.png");
        mouse=new Vector3();
    }

    @Override
    public void show() {

    }
    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                if (mouse.y > 395 && mouse.y < 445) {
                    if (mouse.x < 530 && mouse.x > 285) {
                        Gdx.app.exit();
                        dispose();
                    } else if (mouse.x < 930 && mouse.x > 685) {
                        game.setScreen(new HomeState(this.game));
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
        background.dispose();
    }

}
