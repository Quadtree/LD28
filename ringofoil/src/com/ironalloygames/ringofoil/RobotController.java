package com.ironalloygames.ringofoil;

import java.util.ArrayList;

import com.badlogic.gdx.InputProcessor;
import com.ironalloygames.ringofoil.entity.ComponentEntity;
import com.ironalloygames.ringofoil.entity.Entity;

public class RobotController implements InputProcessor {
	protected ArrayList<ComponentEntity> entities = new ArrayList<ComponentEntity>();

	public void addEntity(Entity e) {
		entities.add((ComponentEntity) e);
	}

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
		// TODO Auto-generated method stub
		return false;
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
