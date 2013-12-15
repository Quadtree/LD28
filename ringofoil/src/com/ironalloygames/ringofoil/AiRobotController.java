package com.ironalloygames.ringofoil;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.entity.CPUEntity;
import com.ironalloygames.ringofoil.entity.ComponentEntity;
import com.ironalloygames.ringofoil.entity.Entity;
import com.ironalloygames.ringofoil.entity.TracksEntity;

public class AiRobotController extends RobotController {

	boolean firingState = false;
	boolean moveForward = true;

	@Override
	public void update() {
		super.update();

		Vector2 myPos = new Vector2(0, 0);

		for (Entity e : ((ArenaState) RG.currentState).entities) {
			if (e instanceof CPUEntity) {
				if (!entities.contains(e)) {
					aimPoint = e.getPosition();
				} else {
					myPos = e.getPosition();
				}
			}
		}

		int move = 0;

		if (MathUtils.random.nextInt(360) == 0 || (moveForward == false && MathUtils.random.nextInt(90) == 0)) {
			moveForward = !moveForward;
		}

		if (myPos.x > aimPoint.x) {
			move = -1;
		} else {
			move = 1;
		}

		if (!moveForward)
			move = -move;

		for (ComponentEntity e : entities) {
			if (e instanceof TracksEntity) {
				((TracksEntity) e).commandMove(move);
			}
		}

		if (MathUtils.random.nextInt(30) == 0) {
			firingState = !firingState;

			if (firingState) {
				for (ComponentEntity e : entities) {
					e.commandKeyDown();
				}
			} else {
				for (ComponentEntity e : entities) {
					e.commandKeyUp();
				}
			}
		}
	}

}
