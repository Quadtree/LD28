package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Arm;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.component.Component;

public class ArmEntity extends ComponentEntity {

	RevoluteJoint joint;

	Body rodBody;

	public ArmEntity(Component component, Vector2 robotCenter, boolean flipped) {
		super(component, robotCenter, flipped);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createFixture() {
		super.createFixture();

		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.position.x = body.getPosition().x;
		bd.position.y = body.getPosition().y;

		System.out.println("Creating rod body at " + bd.position);

		rodBody = ((ArenaState) RG.currentState).world.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(component.getBoundingBox().x / 2,
				component.getBoundingBox().y / 2 / 2);

		rodBody.createFixture(shape, getDensity()).setFilterData(getFilter());

		System.out.println("GINDEX "
				+ rodBody.getFixtureList().get(0).getFilterData().groupIndex);

		RevoluteJointDef jd = new RevoluteJointDef();
		jd.initialize(body, rodBody, body.getPosition());
		jd.enableMotor = true;
		jd.maxMotorTorque = 20;
		jd.motorSpeed = 5;

		joint = (RevoluteJoint) ((ArenaState) RG.currentState).world
				.createJoint(jd);
	}

	@Override
	protected Body getBodyForChildConnection(AttachmentPoint ap) {
		return rodBody;
	}

	@Override
	public void render() {
		((Arm) component).render(body.getPosition(), body.getAngle(),
				rodBody.getAngle(), flipped);

		renderConnector();
	}
}
