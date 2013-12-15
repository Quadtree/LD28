package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Cannon;
import com.ironalloygames.ringofoil.component.Component;

public class CannonEntity extends ComponentEntity {

	public CannonEntity(Component component, Vector2 robotCenter, boolean flipped) {
		super(component, robotCenter, flipped);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void commandKeyDown() {
		super.commandKeyDown();

		if (loose)
			return;

		if (((Cannon) component).roundsLeft > 0) {
			new ShellEntity(body.getPosition(), body.getAngle() + (flipped ? MathUtils.PI : 0), body.getFixtureList().get(0).getFilterData());
			((Cannon) component).roundsLeft--;
		}
	}

	@Override
	public void render() {
		super.render();

		for (int i = 0; i < ((Cannon) component).roundsLeft; i++) {
			Vector2 pt = new Vector2((-.38f + i * .076f) * Component.SCALE, .18f * Component.SCALE);

			if (flipped) {
				pt.x = -pt.x;
			}

			pt = body.getWorldPoint(pt);
			RG.batch.draw(RG.am.get("shell"), pt.x - .5f, pt.y - .5f, .5f, .5f, 1, 1, 8 / 128f * Component.SCALE, 23 / 128f * Component.SCALE, body.getAngle() * (180f / MathUtils.PI));
		}
	}
}
