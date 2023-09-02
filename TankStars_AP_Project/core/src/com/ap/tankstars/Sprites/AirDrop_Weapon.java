package com.ap.tankstars.Sprites;

import com.ap.tankstars.Screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;

public class AirDrop_Weapon extends Sprite {
    public World world;
    public Body b2body;
    public Fixture fixture;

    public Fixture getFixture() {
        return fixture;
    }

    public AirDrop_Weapon(GameScreen screen, World world, String name, int x, int y, float posx, float posy){
        super(screen.getAtlas_AirDrop_Weapon().findRegion(name));
        this.world=world;
        defineAirDrop_Weapon(posx,posy);
        TextureRegion AirDrop_Weapon = new TextureRegion(getTexture(), x, y, 32, 32);
        setBounds(x,y,32,32);
        setRegion(AirDrop_Weapon);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/4,b2body.getPosition().y-getHeight()/3);
    }

    public void defineAirDrop_Weapon(float x,float y){
        BodyDef bdef=new BodyDef();
        bdef.position.set(x,y);
        bdef.type= BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fedf = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2, 10);
        fedf.shape =shape;
        b2body.createFixture(fedf);
        fixture= b2body.createFixture(fedf);
    }
}
