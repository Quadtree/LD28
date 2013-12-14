package com.ironalloygames.ringofoil;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class Main {
	public static void main(String[] args) {
		TexturePacker2.processIfModified("../ringofoil/rawassets",
				"../ringofoil-android/assets", "pack");

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Ring of Oil";
		cfg.useGL20 = true;
		cfg.width = 1024;
		cfg.height = 768;

		new LwjglApplication(new RG(), cfg);
	}
}
