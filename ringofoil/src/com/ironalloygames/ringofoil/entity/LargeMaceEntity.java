package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Component;

public class LargeMaceEntity extends ComponentEntity {

	public LargeMaceEntity(Component component, Vector2 robotCenter, boolean flipped) {
		super(component, robotCenter, flipped);
	}

	@Override
	public boolean canBePunctured() {
		return false;
	}

	@Override
	public float getHeavyDamageMultiplier() {
		return .7f;
	}

	@Override
	public float getLightDamageMultiplier() {
		return .1f;
	}
}
