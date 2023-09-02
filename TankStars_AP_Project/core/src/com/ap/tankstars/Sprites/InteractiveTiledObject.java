package com.ap.tankstars.Sprites;

import com.ap.tankstars.Screens.GameScreen;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public  abstract class InteractiveTiledObject {
    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body body;
    protected GameScreen screen;
    protected MapObject object;
    protected Fixture fixture;

    public InteractiveTiledObject(GameScreen screen,MapObject object) {
        this.object=object;
        this.screen=screen;
        this.world=screen.getWorld();
        this.map=screen.getMap();

        this.bounds=((RectangleMapObject) object).getRectangle();

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2), (bounds.getY() + bounds.getHeight() / 2) );

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 , bounds.getHeight() / 2 );
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void onHit();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x / 16), (int)(body.getPosition().y / 16));
    }

}
