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
	public void render() {
		super.render();

		System.out.println("ROUND " + ((Cannon) component).roundsLeft);
		for (int i = 0; i < ((Cannon) component).roundsLeft; i++) {
			Vector2 pt = new Vector2((-.38f + i * .076f) * Component.SCALE, .18f * Component.SCALE);
			pt = body.getWorldPoint(pt);
			RG.batch.draw(RG.am.get("shell"), pt.x - .5f, pt.y - .5f, .5f, .5f, 1, 1, 8 / 128f * Component.SCALE, 23 / 128f * Component.SCALE, body.getAngle() * (180f / MathUtils.PI));
			System.out.println(pt);
		}
	}

}
