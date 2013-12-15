package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Component;

public class BeamEntity extends ComponentEntity {
	public BeamEntity(Component structure, Vector2 robotCenter, boolean flipped) {
		super(structure, robotCenter, flipped);
	}

	@Override
	public boolean canBePunctured() {
		return false;
	}
}
