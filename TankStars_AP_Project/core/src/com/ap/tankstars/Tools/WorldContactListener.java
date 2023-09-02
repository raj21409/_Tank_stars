package com.ap.tankstars.Tools;

import com.ap.tankstars.Sprites.InteractiveTiledObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("Begin Contact","");
        Fixture fixA=contact.getFixtureA();
        if(fixA.getUserData()=="Top"){
            Fixture Top = fixA;
            Fixture object=Top;
            if(object.getUserData()!=null && InteractiveTiledObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTiledObject) object.getUserData()).onHit();
            }

        }
    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End Contact","");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
