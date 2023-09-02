package com.ap.tankstars.Screens;

import com.ap.tankstars.Sprites.AirDrop_Health;
import com.ap.tankstars.Sprites.AirDrop_Weapon;
import com.ap.tankstars.Sprites.Bullet;
import com.ap.tankstars.Sprites.Tank;
import com.ap.tankstars.TankStars;
import com.ap.tankstars.Tools.B2WorldCreater;
import com.ap.tankstars.Tools.WorldContactListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.io.Serializable;

public class GameScreen implements Screen, Serializable {
    public int tank_one;
    public int tank_two;
    private final Texture texture;
    private final TextureAtlas atlas_tank;
    private final TextureAtlas atlas_bullet;
    private final TextureAtlas atlas_health;
    private final TextureAtlas atlas_weapon;
    private final TankStars game;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private Hud hud;
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;
    private final World world;
    private final Box2DDebugRenderer b2dr;
    private Tank tank1 = null;
    private Bullet bullet1;
    private Bullet bullet2;
    private Tank tank2 = null;
    private final AirDrop_Health airDrop_health;
    private final AirDrop_Weapon airDrop_weapon ;
    private boolean flag=true;

    public GameScreen(TankStars game,int tank_one,int tank_two) {
        this.tank_one=tank_one;
        this.tank_two=tank_two;
        atlas_tank =new TextureAtlas("Tank_Set.pack");
        atlas_bullet = new TextureAtlas("Bullet_Set.pack");
        atlas_health = new TextureAtlas("AirDrop_Set.pack");
        atlas_weapon =new TextureAtlas("AirDrop_Set.pack");
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport( TankStars.V_WIDTH, TankStars.V_HEIGHT, camera);
        hud = new Hud(game.batch,100,100,100,0,0);
        TmxMapLoader mapLoader = new TmxMapLoader();
        texture =new Texture("bg.png");
        map = mapLoader.load("level.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1);
        camera.position.set(viewport.getWorldWidth() /2 , viewport.getWorldHeight() /2, 0);
        world = new World(new Vector2(0, -10), true);
        if(this.tank_one==1){
            tank1 =new Tank(this,world,"tank1",1,49,190,400);
            if(this.tank_two==1){tank2 =new Tank(this,world,"tank1rev",67,49,950,400);}
            else if(this.tank_two==2){tank2 =new Tank(this,world,"tank2rev",133,69,950,400);}
            else{tank2 =new Tank(this,world,"tank3rev",133,29,950,400);}
        }
        else if(this.tank_one==2){
            tank1 =new Tank(this,world,"tank2",1,1,190,400);
            if(this.tank_two==1){tank2 =new Tank(this,world,"tank1rev",67,49,950,400);}
            else if(this.tank_two==2){tank2 =new Tank(this,world,"tank2rev",133,69,950,400);}
            else{tank2 =new Tank(this,world,"tank3rev",133,29,950,400);}
        }
        else if(this.tank_one==3){
            tank1 =new Tank(this,world,"tank3",67,9,190,400);
            if(this.tank_two==1){tank2 =new Tank(this,world,"tank1rev",67,49,950,400);}
            else if(this.tank_two==2){tank2 =new Tank(this,world,"tank2rev",133,69,950,400);}
            else{tank2 =new Tank(this,world,"tank3rev",133,29,950,400);}
        }

        int min = 200;
        int max = 900;
        int b = (int)(Math.random()*(max-min+1)+min);
        int c = (int)(Math.random()*(max-min+1)+min);
        airDrop_health =new AirDrop_Health(this,world,"Health",1,1,b,400);
        airDrop_weapon =new AirDrop_Weapon(this,world,"Weapon",35,1,c,400);

        b2dr = new Box2DDebugRenderer();
        new B2WorldCreater(world,map);
        world.setContactListener(new WorldContactListener());
    }
    public TextureAtlas getAtlas_tank(){
        return atlas_tank;
    }
    public TextureAtlas getAtlas_bullet(){
        return atlas_bullet;
    }
    public TextureAtlas getAtlas_AirDrop_Health(){return atlas_health;}
    public TextureAtlas getAtlas_AirDrop_Weapon(){return atlas_weapon;}

    @Override
    public void show() {}

    public void handleInput(float dt){
        if(tank2.health<=0){game.setScreen(new Tank1_win(this.game));}
        if(tank1.health<=0){game.setScreen(new Tank2_win(this.game));}
        if(Gdx.input.isTouched()){game.setScreen(new InGameMenu(this.game,this.tank_one,this.tank_two));}
        if(flag) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && tank1.b2body.getLinearVelocity().x >= -0.5 && tank1.fuel>10) {
                tank1.b2body.applyLinearImpulse(new Vector2(-25f, 0), tank1.b2body.getWorldCenter(), true);
                tank1.fuel-=10;
                hud = new Hud(game.batch, tank1.health, tank2.health,tank1.fuel, tank1.Power, tank1.Angle);
                hud.stage.draw();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && tank1.b2body.getLinearVelocity().x <= 0.5  && tank1.fuel>10) {
                tank1.b2body.applyLinearImpulse(new Vector2(25f, 0), tank1.b2body.getWorldCenter(), true);
                tank1.fuel-=10;
                hud = new Hud(game.batch, tank1.health, tank2.health,tank1.fuel, tank1.Power, tank1.Angle);
                hud.stage.draw();
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
                tank1.Power+=10;
                hud = new Hud(game.batch, tank1.health, tank2.health,tank1.fuel, tank1.Power, tank1.Angle);
                hud.stage.draw();
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
                tank1.Power-=10;
                hud = new Hud(game.batch, tank1.health, tank2.health,tank1.fuel, tank1.Power, tank1.Angle);
                hud.stage.draw();
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_ADD)){
                tank1.Angle+=10;
                hud = new Hud(game.batch, tank1.health, tank2.health,tank1.fuel, tank1.Power, tank1.Angle);
                hud.stage.draw();
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.MINUS)){
                tank1.Angle-=10;
                hud = new Hud(game.batch, tank1.health, tank2.health,tank1.fuel, tank1.Power, tank1.Angle);
                hud.stage.draw();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                bullet1 = new Bullet(this, world, "bulletrev", 19, 1, tank1.getX() + 50, tank1.getY() + 20);
                bullet1.b2body.applyForce(new Vector2(tank1.Power * 100, tank1.Angle * 100), bullet1.b2body.getWorldCenter(), true);
                bullet1.update(dt);
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                System.out.println(bullet1.getFixture().getBody().getPosition().x + " " + bullet1.getFixture().getBody().getPosition().y);
                if(tank2.getX()-5<bullet1.getFixture().getBody().getPosition().x && tank2.getX()+5>bullet1.getFixture().getBody().getPosition().x){tank2.health -= 30;}
                else if(tank2.getX()-10<bullet1.getFixture().getBody().getPosition().x && tank2.getX()+10>bullet1.getFixture().getBody().getPosition().x){tank2.health -= 20;}
                else if(tank2.getX()-15<bullet1.getFixture().getBody().getPosition().x && tank2.getX()+15>bullet1.getFixture().getBody().getPosition().x){tank2.health -= 10;}
                else if(tank2.getX()-20<bullet1.getFixture().getBody().getPosition().x && tank2.getX()+20>bullet1.getFixture().getBody().getPosition().x){tank2.health -= 5;}

                if(tank1.getX()-5<bullet1.getFixture().getBody().getPosition().x && tank1.getX()+5>bullet1.getFixture().getBody().getPosition().x){tank1.health -= 30;}
                else if(tank1.getX()-10<bullet1.getFixture().getBody().getPosition().x && tank1.getX()+10>bullet1.getFixture().getBody().getPosition().x){tank1.health -= 20;}
                else if(tank1.getX()-15<bullet1.getFixture().getBody().getPosition().x && tank1.getX()+15>bullet1.getFixture().getBody().getPosition().x){tank1.health -= 10;}
                else if(tank1.getX()-20<bullet1.getFixture().getBody().getPosition().x && tank1.getX()+20>bullet1.getFixture().getBody().getPosition().x){tank1.health -= 5;}
                world.destroyBody(bullet1.b2body);
                hud = new Hud(game.batch, tank1.health, tank2.health,tank1.fuel, tank1.Power, tank1.Angle);
                hud.stage.draw();
                flag = !flag;
            }
        }
        else{
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && tank2.b2body.getLinearVelocity().x >= -0.5  && tank2.fuel>10) {
                tank2.b2body.applyLinearImpulse(new Vector2(-18f, 0), tank2.b2body.getWorldCenter(), true);
                tank2.fuel-=10;
                hud = new Hud(game.batch, tank1.health, tank2.health, tank2.fuel, tank2.Power, tank2.Angle);
                hud.stage.draw();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && tank2.b2body.getLinearVelocity().x <= 0.5  && tank2.fuel>10) {
                tank2.b2body.applyLinearImpulse(new Vector2(18f, 0), tank2.b2body.getWorldCenter(), true);
                tank2.fuel-=10;
                hud = new Hud(game.batch, tank1.health, tank2.health, tank2.fuel, tank2.Power, tank2.Angle);
                hud.stage.draw();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
                tank2.Power+=10;
                hud = new Hud(game.batch, tank1.health, tank2.health, tank2.fuel, tank2.Power, tank2.Angle);
                hud.stage.draw();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
                tank2.Power-=10;
                hud = new Hud(game.batch, tank1.health, tank2.health, tank2.fuel, tank2.Power, tank2.Angle);
                hud.stage.draw();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_ADD)){
                tank2.Angle+=10;
                hud = new Hud(game.batch, tank1.health, tank2.health, tank2.fuel, tank2.Power, tank2.Angle);
                hud.stage.draw();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.MINUS)){
                tank2.Angle-=10;
                hud = new Hud(game.batch, tank1.health, tank2.health, tank2.fuel, tank2.Power, tank2.Angle);
                hud.stage.draw();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                bullet2 = new Bullet(this, world, "bullet", 1, 1, tank2.getX() + 15, tank2.getY() + 20);
                bullet2.b2body.applyForce(new Vector2(-tank1.Power*100,tank1.Angle*100),bullet2.b2body.getWorldCenter(),true);
                bullet2.update(dt);
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                System.out.println(bullet2.getFixture().getBody().getPosition().x + " " + bullet2.getFixture().getBody().getPosition().y);
                if(tank1.getX()-5<bullet2.getFixture().getBody().getPosition().x && tank1.getX()+5>bullet2.getFixture().getBody().getPosition().x){tank1.health -= 30;}
                else if(tank1.getX()-10<bullet2.getFixture().getBody().getPosition().x && tank1.getX()+10>bullet2.getFixture().getBody().getPosition().x){tank1.health -= 20;}
                else if(tank1.getX()-15<bullet2.getFixture().getBody().getPosition().x && tank1.getX()+15>bullet2.getFixture().getBody().getPosition().x){tank1.health -= 10;}
                else if(tank1.getX()-20<bullet2.getFixture().getBody().getPosition().x && tank1.getX()+20>bullet2.getFixture().getBody().getPosition().x){tank1.health -= 5;}

                if(tank2.getX()-5<bullet2.getFixture().getBody().getPosition().x && tank2.getX()+5>bullet2.getFixture().getBody().getPosition().x){tank2.health -= 30;}
                else if(tank2.getX()-10<bullet2.getFixture().getBody().getPosition().x && tank2.getX()+10>bullet2.getFixture().getBody().getPosition().x){tank2.health -= 20;}
                else if(tank2.getX()-15<bullet2.getFixture().getBody().getPosition().x && tank2.getX()+15>bullet2.getFixture().getBody().getPosition().x){tank2.health -= 10;}
                else if(tank2.getX()-20<bullet2.getFixture().getBody().getPosition().x && tank2.getX()+20>bullet2.getFixture().getBody().getPosition().x){tank2.health -= 5;}
                world.destroyBody(bullet2.b2body);
                hud = new Hud(game.batch, tank1.health, tank2.health, tank2.fuel, tank2.Power, tank2.Angle);
                hud.stage.draw();
                flag = !flag;
            }
        }
    }

    public void update(float dt){
        handleInput(dt);
        world.step(1/60f , 6 ,2);
        tank1.update(dt);
        tank2.update(dt);
        airDrop_weapon.update(dt);
        airDrop_health.update(dt);
        camera.update();
        renderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(texture,0,0,TankStars.V_WIDTH,TankStars.V_HEIGHT);
        tank1.draw(game.batch);
        tank2.draw(game.batch);
        airDrop_health.draw(game.batch);
        airDrop_weapon.draw(game.batch);
        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        renderer.render();
        b2dr.render(world, camera.combined);
        game.batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {viewport.update(width, height);}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    public World getWorld(){return world;}

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        map.dispose();
        renderer.dispose();
        hud.dispose();
    }

    public TiledMap getMap() {return  map;}
}
