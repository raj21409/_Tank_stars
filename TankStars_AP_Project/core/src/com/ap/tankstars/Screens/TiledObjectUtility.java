package com.ap.tankstars.Screens;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.maps.MapObject;

import java.util.Iterator;

public class TiledObjectUtility {
    public static void parseTiledObjectLayer(World world, MapObjects objects){
        Iterator<MapObject> itr= objects.iterator();
        while(itr.hasNext()){
            Shape shape;
            MapObject object =itr.next();
            if(object instanceof PolylineMapObject){
                shape = createPolyline((PolylineMapObject) object);
            }
            else{
                continue;
            }
            Body body;
            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bdef);
            body.createFixture(shape, 1f);
            shape.dispose();

        }
//        for (MapObject object : objects) {
//            Shape shape;
//            if(object instanceof PolylineMapObject){
//                shape = createPolyline((PolylineMapObject) object);
//            }
//            else{
//                continue;
//            }
//            Body body;
//            BodyDef bdef = new BodyDef();
//            bdef.type = BodyDef.BodyType.StaticBody;
//            body = world.createBody(bdef);
//            body.createFixture(shape, 1f);
//            shape.dispose();
//        }
    }

    private static ChainShape createPolyline(PolylineMapObject polylineObject){
        float[] vertices = polylineObject.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];
        for (int i = 0; i < worldVertices.length; i++) {
            worldVertices[i] = new Vector2(vertices[i * 2] , vertices[i * 2 + 1] );
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        return cs;
    }


}
