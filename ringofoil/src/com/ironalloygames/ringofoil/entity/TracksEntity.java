package com.ironalloygames.ringofoil.entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Tracks;

public class TracksEntity extends ComponentEntity {
	Tracks tracks;

	List<Body> wheels;

	public TracksEntity(Tracks tracks, Vector2 robotCenter, boolean flipped) {
		super(tracks, robotCenter, flipped);
		this.tracks = tracks;
	}

	@Override
	protected void createFixture() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(component.getBoundingBox().x / 2, component
				.getBoundingBox().y / 2 * .7f,
				new Vector2(0, component.getBoundingBox().y / 2 * .4f), 0);

		body.createFixture(shape, getDensity());

		wheels = new ArrayList<Body>();

		createWheel(new Vector2(-component.getBoundingBox().x / 2 * .6f,
				-component.getBoundingBox().y / 2 * .4f));
		createWheel(new Vector2(component.getBoundingBox().x / 2 * .6f,
				-component.getBoundingBox().y / 2 * .4f));
		createWheel(new Vector2(0, -component.getBoundingBox().y / 2 * .4f));
	}

	private void createWheel(Vector2 relPos) {

		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;

		Vector2 wheelPos = body.getPosition().cpy().add(relPos);

		bd.position.x = wheelPos.x;
		bd.position.y = wheelPos.y;

		Body wheel = ((ArenaState) RG.currentState).world.createBody(bd);

		CircleShape cs = new CircleShape();
		cs.setRadius(component.getBoundingBox().x * .125f);
		wheel.createFixture(cs, 1);

		wheels.add(wheel);

		RevoluteJointDef jd = new RevoluteJointDef();
		jd.initialize(body, wheel, wheel.getWorldCenter());
		((ArenaState) RG.currentState).world.createJoint(jd);
	}

	@Override
	protected float getDensity() {
		return 4;
	}
}
