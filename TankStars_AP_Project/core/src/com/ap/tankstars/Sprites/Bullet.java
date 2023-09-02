package com.ap.tankstars.Sprites;

import com.ap.tankstars.Screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;

public class Bullet extends Sprite {
    public World world;
    public Body b2body;
    public Fixture fixture;

    public Bullet(GameScreen screen, World world, String name, int x, int y, float posx, float posy){
        super(screen.getAtlas_bullet().findRegion(name));
        this.world=world;
        defineBullet(posx,posy);
        TextureRegion bullet = new TextureRegion(getTexture(), x, y, 64, 37);
        setBounds(x,y,64,37);
        setRegion(bullet);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/4,b2body.getPosition().y-getHeight()/3);
    }

    public void defineBullet(float x,float y){
        BodyDef bdef=new BodyDef();
        bdef.position.set(x,y);
        bdef.type= BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fedf = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(4);
        fedf.shape =shape;
        b2body.createFixture(fedf);
        fixture=b2body.createFixture(fedf);
    }

    public Fixture getFixture() {
        return fixture;
    }
}