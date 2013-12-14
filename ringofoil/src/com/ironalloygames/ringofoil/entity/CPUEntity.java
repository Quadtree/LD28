package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.CPU;

public class CPUEntity extends ComponentEntity {
	CPU cpu;

	public CPUEntity(CPU cpu, Vector2 robotCenter, boolean flipped) {
		super(cpu, robotCenter, flipped);
		this.cpu = cpu;
	}
}
