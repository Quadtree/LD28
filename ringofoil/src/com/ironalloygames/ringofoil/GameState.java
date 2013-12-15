package com.ironalloygames.ringofoil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class GameState implements InputProcessor {
	public Vector2 mouseScreenPosition = new Vector2(0, 0);

	public Vector2 mouseWorldPosition = new Vector2(0, 0);

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseScreenPosition.x = (screenX * 800 / Gdx.graphics.getWidth()) - 400;
		mouseScreenPosition.y = -(screenY * 600 / Gdx.graphics.getHeight() - 300);

		mouseWorldPosition.x = mouseScreenPosition.x / 128.f;
		mouseWorldPosition.y = mouseScreenPosition.y / 128.f;

		return false;
	}

	public void render() {
	}

	public void renderUi() {
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	public void update() {
	}
}
