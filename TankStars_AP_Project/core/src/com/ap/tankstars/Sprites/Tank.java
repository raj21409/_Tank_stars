package com.ap.tankstars.Sprites;

import com.ap.tankstars.Screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class Tank extends Sprite implements Serializable {
    public  int health;
    public World world;
    public Body b2body;
    public int Power;
    public float Angle;
    public int fuel;
    public boolean health_up=false;
    public boolean weapon=false;

    public Tank(GameScreen screen,World world,String name,int x, int y,int posx, int posy){
        super(screen.getAtlas_tank().findRegion(name));
        this.health=100;
        this.fuel=100;
        this.Power=0;
        this.Angle=0;
        this.world=world;
        defineTank(posx,posy);
        TextureRegion tank = new TextureRegion(getTexture(), x, y, 64, 37);
        setBounds(x,y,64,37);
        setRegion(tank);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);
    }

    public void defineTank(int x, int y){
        BodyDef bdef=new BodyDef();
        bdef.position.set(x,y);
        bdef.type= BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fedf = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10, 20);
        fedf.shape =shape;
        b2body.createFixture(fedf);

    }

}
