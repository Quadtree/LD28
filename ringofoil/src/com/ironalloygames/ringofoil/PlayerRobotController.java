package com.ironalloygames.ringofoil;

import com.badlogic.gdx.Input.Keys;
import com.ironalloygames.ringofoil.entity.Entity;
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

		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {

		if (keycode == Keys.A || keycode == Keys.LEFT)
			moveLeft = false;

		if (keycode == Keys.D || keycode == Keys.RIGHT)
			moveRight = false;

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

		for (Entity e : entities) {
			if (e instanceof TracksEntity) {
				((TracksEntity) e).commandMove(move);
			}
		}
	}
}
