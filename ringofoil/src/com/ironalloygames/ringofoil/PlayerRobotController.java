package com.ironalloygames.ringofoil;

import com.badlogic.gdx.Input.Keys;
import com.ironalloygames.ringofoil.entity.ComponentEntity;
import com.ironalloygames.ringofoil.entity.TracksEntity;

public class PlayerRobotController extends RobotController {

	boolean moveLeft;
	boolean moveRight;

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.A || keycode == Keys.LEFT)
			moveLeft = true;

		if (keycode == Keys.D || keycode == Keys.RIGHT)
			moveRight = true;

		for (ComponentEntity e : entities) {
			if (e.getCommandKey() == keycode)
				e.commandKeyDown();
		}

		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {

		if (keycode == Keys.A || keycode == Keys.LEFT)
			moveLeft = false;

		if (keycode == Keys.D || keycode == Keys.RIGHT)
			moveRight = false;

		for (ComponentEntity e : entities) {
			if (e.getCommandKey() == keycode)
				e.commandKeyUp();
		}

		return super.keyUp(keycode);
	}

	@Override
	public void update() {
		super.update();

		int move = 0;

		if (moveLeft && !moveRight)
			move = -1;
		if (!moveLeft && moveRight)
			move = 1;

		for (ComponentEntity e : entities) {
			if (e instanceof TracksEntity) {
				((TracksEntity) e).commandMove(move);
			}
		}
	}
}
