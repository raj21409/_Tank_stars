package com.ap.tankstars.Screens;

import com.ap.tankstars.TankStars;
import com.ap.tankstars.Tools.GameState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.io.Serializable;

public class InGameMenu implements Screen, Serializable {
    protected Vector3 mouse;
    private int tankone;
    private int tanktwo;
    private final Texture background;
    private final TankStars game;
    public InGameMenu(TankStars game,int tankone, int tanktwo) {
        this.game=game;
        this.tankone=tankone;
        this.tanktwo=tankone;
        background = new Texture("InGameMenu.png");
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
        if(Gdx.input.isTouched()){
            mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            System.out.println(mouse.x+" "+mouse.y);
            if(mouse.x>470 && mouse.x<740){
                if(mouse.y>155 && mouse.y<220){
                    game.setScreen(new GameScreen(this.game,this.tankone,this.tanktwo));
                }
                else if(mouse.y>280 && mouse.y<340){
                }
                else if(mouse.y>400 && mouse.y< 460){
                    game.setScreen(new HomeState(this.game));
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
