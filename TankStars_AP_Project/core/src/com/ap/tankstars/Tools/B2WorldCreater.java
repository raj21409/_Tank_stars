package com.ap.tankstars.Tools;

import com.ap.tankstars.Screens.TiledObjectUtility;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

public class B2WorldCreater {
    public B2WorldCreater(World world, TiledMap map){
        TiledObjectUtility.parseTiledObjectLayer(world, map.getLayers().get("Ground").getObjects());
    }
}
