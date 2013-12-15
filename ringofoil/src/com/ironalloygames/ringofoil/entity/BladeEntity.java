package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Component;

public class BladeEntity extends ComponentEntity {

	public BladeEntity(Component component, Vector2 robotCenter, boolean flipped) {
		super(component, robotCenter, flipped);
	}

	@Override
	protected float getDensity() {
		return .15f;
	}

}
