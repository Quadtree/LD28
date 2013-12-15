package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Component;

public class ArmorEntity extends ComponentEntity {

	public ArmorEntity(Component component, Vector2 robotCenter, boolean flipped) {
		super(component, robotCenter, flipped);
	}

	@Override
	public boolean canBePunctured() {
		return false;
	}

}
