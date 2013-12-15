package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.CPU;

public class CPUEntity extends ComponentEntity {
	CPU cpu;

	public CPUEntity(CPU cpu, Vector2 robotCenter, boolean flipped) {
		super(cpu, robotCenter, flipped);
		this.cpu = cpu;
	}

	@Override
	public void takeDamage(float heavyDamage, float lightDamage) {
		boolean hpAbove = getHp() > 0;
		super.takeDamage(heavyDamage, lightDamage);

		if (getHp() < 0 && hpAbove) {
			this.lastHitPos = body.getPosition();
			this.lastHitSparks = 35;
		}
	}

	@Override
	public void update() {
		super.update();

		if (getHp() < 0) {
			this.lastHitPos = body.getPosition();
			this.lastHitSparks = MathUtils.random(1, 4);
		}
	}
}
