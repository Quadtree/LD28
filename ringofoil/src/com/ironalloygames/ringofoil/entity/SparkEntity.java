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

public class SparkEntity extends Entity {
	String graphic;

	public SparkEntity(Vector2 vector2, float ang, float speed, Filter filter, String graphic) {
		this.graphic = graphic;

		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.position.x = vector2.x;
		bd.position.y = vector2.y;
		bd.linearVelocity.x = MathUtils.cos(ang) * speed;
		bd.linearVelocity.y = MathUtils.sin(ang) * speed;

		body = ((ArenaState) RG.currentState).world.createBody(bd);
		body.setUserData(this);

		CircleShape cs = new CircleShape();
		cs.setRadius(.05f);

		body.createFixture(cs, 1).setFilterData(filter);
	}

	@Override
	public float getHeavyDamageMultiplier() {
		return .01f;

	}

	@Override
	public float getLightDamageMultiplier() {
		return .01f;
	}

	@Override
	public void impact(float force, Entity otherEntity) {
		super.impact(force, otherEntity);
	}

	@Override
	public boolean keep() {
		return body.getLinearVelocity().len() > 2 || getPosition().y > -1.9f;
	}

	@Override
	public void render() {
		super.render();

		RG.batch.draw(RG.am.get(graphic), body.getPosition().x - .5f, body.getPosition().y - .5f, .5f, .5f, 1, 1, 24 / 128f * Component.SCALE, 8 / 128f * Component.SCALE, body.getLinearVelocity().angle());
	}

}
