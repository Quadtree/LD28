package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;

public abstract class Entity {
	Body body;

	Vector2 lastHitPos;
	int lastHitSparks = 0;

	public Entity() {
		((ArenaState) RG.currentState).addEntity(this);
	}

	public void destroyed() {
		if (body != null)
			((ArenaState) RG.currentState).world.destroyBody(body);
		body = null;
	}

	public float getHeavyDamageMultiplier() {
		return 0.1f;
	}

	public float getHeavyDamageReduction() {
		return 0;
	}

	public float getHeavyDamageResistance() {
		return 1;
	}

	public float getHp() {
		return 1;
	}

	public float getLightDamageMultiplier() {
		return 0.1f;
	}

	public float getLightDamageReduction() {
		return 0;
	}

	public float getLightDamageResistance() {
		return 1;
	}

	public float getMaxHp() {
		return 1;
	}

	public Vector2 getPosition() {
		return body.getPosition().cpy();
	}

	public void impact(float force, Entity otherEntity) {
		otherEntity.takeDamage(force * getLightDamageMultiplier(), force * getHeavyDamageMultiplier());

		lastHitPos = this.getPosition().sub(otherEntity.getPosition()).scl(.5f).add(this.getPosition());
		lastHitSparks += (int) (force);

		if (force > 0.1f) {
			String soundStub = "hitdmg";

			if (this.getLightDamageResistance() < .5f) {
				soundStub = "ding";
			}

			RG.am.getSound(soundStub + MathUtils.random(1, 4)).play();
		}

		// if (force > .25f)
		// System.out.println("IMPACT! " + this + " " + otherEntity + " " +
		// force);
	}

	public boolean keep() {
		return true;
	}

	public void render() {
	}

	public void setHp(float hp) {

	}

	public void takeDamage(float lightDamage, float heavyDamage) {
		float damage = Math.max(0, (lightDamage - getLightDamageReduction()) * getLightDamageResistance());
		damage += Math.max(0, (heavyDamage - getHeavyDamageReduction()) * getHeavyDamageResistance());

		setHp(getHp() - damage);

		if (damage > 0.01f) {
			System.out.println(this + " takes " + damage + " damage!");
		}

		((ArenaState) RG.currentState).lastDamageTick = ((ArenaState) RG.currentState).tick;

		if (this.body.getFixtureList().get(0).getFilterData().groupIndex == -1) {
			((ArenaState) RG.currentState).robot1DamageTaken += damage;
		}
		if (this.body.getFixtureList().get(0).getFilterData().groupIndex == -2) {
			((ArenaState) RG.currentState).robot2DamageTaken += damage;
		}
	}

	public void update() {
		for (int i = 0; i < lastHitSparks; i++) {
			new SparkEntity(lastHitPos, MathUtils.random(0, MathUtils.PI2), 5, body.getFixtureList().get(0).getFilterData(), "spark");
		}
		lastHitSparks = 0;
	}
}
