package com.ironalloygames.ringofoil;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetManager {
	TextureAtlas atlas;

	HashMap<String, Texture> bigTextures = new HashMap<String, Texture>();
	BitmapFont font;

	HashMap<String, Sprite> loaded = new HashMap<String, Sprite>();

	HashMap<String, Sound> sounds = new HashMap<String, Sound>();

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

	public BitmapFont getFont() {
		if (font == null) {
			// if (Gdx.graphics.getWidth() > 1000)
			font = new BitmapFont(Gdx.files.internal("font_1366.fnt"));
			// else
			// font = new BitmapFont(Gdx.files.internal("font_800.fnt"));
		}

		return font;
	}

	public Sound getSound(String name) {
		if (!sounds.containsKey(name)) {
			sounds.put(name, Gdx.audio.newSound(Gdx.files.internal(name + ".ogg")));

			System.out.println("Loaded sound texture " + name + " as " + loaded.get(name));
		}

		return sounds.get(name);
	}
}
