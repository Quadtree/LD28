package com.ironalloygames.ringofoil;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetManager {
	TextureAtlas atlas;

	HashMap<String, Texture> bigTextures = new HashMap<String, Texture>();
	HashMap<String, Sprite> loaded = new HashMap<String, Sprite>();

	public AssetManager() {
		atlas = new TextureAtlas(Gdx.files.internal("pack.atlas"));
	}

	public Sprite get(String name) {
		if (!loaded.containsKey(name)) {
			loaded.put(name, atlas.createSprite(name));

			System.out.println("Loaded sprite " + name + " as " + loaded.get(name));
		}

		return loaded.get(name);
	}

	public Texture getBigTexture(String name) {
		if (!bigTextures.containsKey(name)) {
			bigTextures.put(name, new Texture(Gdx.files.internal(name + ".png")));

			System.out.println("Loaded big texture " + name + " as " + loaded.get(name));
		}

		return bigTextures.get(name);
	}
}
