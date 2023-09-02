package com.ap.tankstars.Screens;

import com.ap.tankstars.TankStars;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Hud implements Disposable {
    public Stage stage;

    Label Tank1_Health;
    Label Tank2_Health;
    Label Health1;
    Label Health2;
    Label Power;
    Label Angle;
    Label Tank_Power;
    Label Tank_Angle;
    Label  Tank_Fuel;
    Label Fuel;

    public Hud(SpriteBatch sb,int h1,int h2,int f,int power,float angle){
        Integer tank_1_Health = h1;
        Integer tank_2_Health = h2;
        Integer powers = power;
        Integer fuel = f;

        Viewport viewport = new FitViewport(TankStars.V_WIDTH, TankStars.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Tank1_Health = new Label(String.format("%03d", tank_1_Health), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Tank2_Health = new Label(String.format("%03d", tank_2_Health), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Power = new Label(String.format("%03d", powers), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Fuel = new Label(String.format("%03d", fuel), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Angle = new Label(String.format("%03f", angle), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Health1 = new Label("TANK1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Tank_Power = new Label("POWER",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        Tank_Fuel =new Label("FUEL",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        Tank_Angle = new Label("ANGLE",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        Health2 = new Label("TANK2", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(Health1).expandX().padTop(5);
        table.add(Tank_Power).expandX().padTop(5);
        table.add(Tank_Fuel).expandX().padTop(5);
        table.add(Tank_Angle).expandX().padTop(5);
        table.add(Health2).expandX().padTop(5);
        table.row();
        table.add(Tank1_Health).expandX();
        table.add(Power).expandX();
        table.add(Fuel).expandX();
        table.add(Angle).expandX();
        table.add(Tank2_Health).expandX();

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
