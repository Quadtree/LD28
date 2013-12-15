package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Component;

public class ShellEntity extends Entity {
	public ShellEntity(Vector2 vector2, float f, Filter filter) {
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.position.x = vector2.x;
		bd.position.y = vector2.y;
		bd.linearVelocity.x = MathUtils.cos(f) * 10;
		bd.linearVelocity.y = MathUtils.sin(f) * 10;

		body = ((ArenaState) RG.currentState).world.createBody(bd);
		body.setUserData(this);

		CircleShape cs = new CircleShape();
		cs.setRadius(.1f);

		body.createFixture(cs, 1).setFilterData(filter);
	}

	@Override
	public float getHeavyDamageMultiplier() {
		return .01f;

	}

	@Override
	public float getLightDamageMultiplier() {
		return 1.2f;
	}

	@Override
	public void impact(float force, Entity otherEntity) {
		super.impact(force, otherEntity);
	}

	@Override
	public boolean keep() {
		return body.getLinearVelocity().len() > 2;
	}

	@Override
	public void render() {
		super.render();

		RG.batch.draw(RG.am.get("shellinflight"), body.getPosition().x - .5f, body.getPosition().y - .5f, .5f, .5f, 1, 1, 64 / 128f * Component.SCALE, 16 / 128f * Component.SCALE, body.getAngle() * (180f / MathUtils.PI));
	}

}
