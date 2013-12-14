package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Component;
import com.ironalloygames.ringofoil.component.Piston;

public class PistonEntity extends ComponentEntity {

	Piston piston;

	public PistonEntity(Component component, Vector2 robotCenter,
			boolean flipped) {
		super(component, robotCenter, flipped);

		piston = (Piston) component;
	}

}
