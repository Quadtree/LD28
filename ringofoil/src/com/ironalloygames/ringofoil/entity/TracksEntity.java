package com.ironalloygames.ringofoil.entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Tracks;

public class TracksEntity extends ComponentEntity {
	float moveCommand = 0;

	Tracks tracks;

	List<RevoluteJoint> wheelJoints;
	List<Body> wheels;

	public TracksEntity(Tracks tracks, Vector2 robotCenter, boolean flipped) {
		super(tracks, robotCenter, flipped);
		this.tracks = tracks;
	}

	public void commandMove(float dir) {
		moveCommand = dir;

		for (RevoluteJoint rj : wheelJoints) {
			rj.setMaxMotorTorque(100);
			rj.setMotorSpeed(-dir * 20);
			rj.enableMotor(true);
		}
	}

	@Override
	protected void createFixture() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(component.getBoundingBox().x / 2, component.getBoundingBox().y / 2 * .7f, new Vector2(0, component.getBoundingBox().y / 2 * .4f), 0);

		body.createFixture(shape, getDensity()).setFilterData(getFilter());

		wheels = new ArrayList<Body>();
		wheelJoints = new ArrayList<RevoluteJoint>();

		createWheel(new Vector2(-component.getBoundingBox().x / 2 * .6f, -component.getBoundingBox().y / 2 * .4f));
		createWheel(new Vector2(component.getBoundingBox().x / 2 * .6f, -component.getBoundingBox().y / 2 * .4f));
		createWheel(new Vector2(0, -component.getBoundingBox().y / 2 * .4f));
	}

	private void createWheel(Vector2 relPos) {

		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;

		Vector2 wheelPos = body.getPosition().cpy().add(relPos);

		bd.position.x = wheelPos.x;
		bd.position.y = wheelPos.y;

		Body wheel = ((ArenaState) RG.currentState).world.createBody(bd);
		wheel.setUserData(this);

		CircleShape cs = new CircleShape();
		cs.setRadius(component.getBoundingBox().x * .125f);
		wheel.createFixture(cs, 1).setFilterData(getFilter());

		wheels.add(wheel);

		RevoluteJointDef jd = new RevoluteJointDef();
		jd.initialize(body, wheel, wheel.getWorldCenter());
		wheelJoints.add((RevoluteJoint) ((ArenaState) RG.currentState).world.createJoint(jd));
	}

	@Override
	protected float getDensity() {
		return 4;
	}

	@Override
	public void render() {
		tracks.renderWithWheels(body.getPosition(), body.getAngle(), flipped, new Vector2[] { wheels.get(0).getPosition(), wheels.get(1).getPosition(), wheels.get(2).getPosition() }, new float[] {
				wheels.get(0).getAngle(), wheels.get(1).getAngle(), wheels.get(2).getAngle() });

		renderConnector();
	}

	@Override
	protected void setToNewGroup(short group) {
		Filter fd = new Filter();
		fd.categoryBits = 2;
		fd.maskBits = 2;
		fd.groupIndex = group;

		for (Body b : wheels) {
			b.getFixtureList().get(0).setFilterData(fd);
		}

		super.setToNewGroup(group);
	}

	@Override
	public void update() {
		super.update();

		if (loose) {
			for (RevoluteJoint rj : wheelJoints) {
				rj.enableMotor(false);
			}
		}
	}
}
