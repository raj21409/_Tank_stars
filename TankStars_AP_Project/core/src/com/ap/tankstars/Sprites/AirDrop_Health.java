package com.ap.tankstars.Sprites;

import com.ap.tankstars.Screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;

public class AirDrop_Health extends Sprite {
    public World world;
    public Body b2body;
    public Fixture fixture;

    public Fixture getFixture() {
        return fixture;
    }

    public AirDrop_Health(GameScreen screen, World world, String name, int x, int y, int posx, int posy){
        super(screen.getAtlas_AirDrop_Health().findRegion(name));
        this.world=world;
        defineAirDrop_Health(posx,posy);
        TextureRegion AirDrop_Health = new TextureRegion(getTexture(), x, y, 32, 32);
        setBounds(x,y,32,32);
        setRegion(AirDrop_Health);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);
    }

    public void defineAirDrop_Health(int x,int y){
        BodyDef bdef=new BodyDef();
        bdef.position.set(x,y);
        bdef.type= BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fedf = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2, 10);
        fedf.shape =shape;
        b2body.createFixture(fedf);
        fixture=b2body.createFixture(fedf);
    }
}
