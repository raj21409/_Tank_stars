package com.ap.tankstars.Screens;

import com.ap.tankstars.TankStars;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.io.Serializable;

public class Choose_Tank3 implements Screen, Serializable {
    protected Vector3 mouse;

    private final Texture background;
    private final TankStars game;
    public Choose_Tank3(TankStars game){
        this.game=game;
        background=new Texture("Choose3_1.png");
        mouse=new Vector3();
    }
    @Override
    public void show() {

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

    public void update(float dt){
        handleInput(dt);
    }

    public void handleInput(float dt){
        if(Gdx.input.justTouched()) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                System.out.println(mouse.x + " " + mouse.y);
                if (mouse.y > 320 && mouse.y < 385) {
                    if (mouse.x < 360 && mouse.x > 300) {
                        game.setScreen(new Choose_Tank1(this.game));
                        dispose();
                    } else if (mouse.x < 910 && mouse.x > 850) {
                        game.setScreen(new Choose_Tank2(this.game));
                        dispose();
                    }
                }
                else if(mouse.x> 480 && mouse.x<720 && mouse.y> 575 && mouse.y< 625){
                    game.setScreen(new Choose2_Tank1(this.game,3));
                    dispose();
                }
            }
        }
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
