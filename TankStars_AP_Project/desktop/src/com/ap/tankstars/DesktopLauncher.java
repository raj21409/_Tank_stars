package com.ap.tankstars;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(1200,640);
		config.setForegroundFPS(60);
		config.setTitle("Tank_Stars");
		new Lwjgl3Application(new TankStars(), config);
	}
}
