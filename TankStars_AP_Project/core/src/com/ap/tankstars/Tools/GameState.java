package com.ap.tankstars.Tools;

import com.ap.tankstars.Screens.GameScreen;
import com.ap.tankstars.Screens.Hud;
import com.ap.tankstars.TankStars;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GameState implements Serializable {
    TankStars game;
    GameScreen screen;
    Hud hud;
    public GameState(TankStars game,GameScreen screen,Hud hud){
        this.game=game;
        this.screen=screen;
        this.hud=hud;
    }
    public void writeObject(ObjectOutputStream out) throws IOException{
        out.writeObject(game);
        out.writeObject(screen);
        out.writeObject(hud);

    }

    public void  readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        game=(TankStars) in.readObject();
        screen =(GameScreen) in.readObject();
        hud=(Hud) in.readObject();
    }


}
