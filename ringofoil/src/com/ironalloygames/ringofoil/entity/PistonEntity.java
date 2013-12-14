package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.component.Component;
import com.ironalloygames.ringofoil.component.Piston;

public class PistonEntity extends ComponentEntity {

	boolean commandExtend = false;

	PrismaticJoint joint;

	Piston piston;

	Body rodBody;

	public PistonEntity(Component component, Vector2 robotCenter,
			boolean flipped) {
		super(component, robotCenter, flipped);

		piston = (Piston) component;
	}

	@Override
	public void commandKeyDown() {
		super.commandKeyDown();

		commandExtend = true;
	}

	@Override
	public void commandKeyUp() {
		super.commandKeyUp();

		commandExtend = false;
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
				component.getBoundingBox().y / 2);

		rodBody.createFixture(shape, getDensity());

		// if (commandExtend) {
		PrismaticJointDef jd = new PrismaticJointDef();
		jd.initialize(body, rodBody, body.getPosition(), new Vector2(1, 0));
		jd.enableMotor = true;
		jd.enableLimit = true;
		jd.lowerTranslation = 0;
		jd.upperTranslation = component.getBoundingBox().x * .9f;
		jd.maxMotorForce = 10;

		joint = (PrismaticJoint) ((ArenaState) RG.currentState).world
				.createJoint(jd);

		// joint.setMotorSpeed(-10);
		// }
	}

	@Override
	protected Body getBodyForChildConnection(AttachmentPoint ap) {
		return rodBody;
	}

	@Override
	protected float getDensity() {
		return 1.5f;
	}

	@Override
	public void render() {
		piston.render(body.getPosition(), rodBody.getPosition(),
				body.getAngle(), rodBody.getAngle(), flipped);

		renderConnector();
	}

	@Override
	public void update() {
		super.update();

		System.out.println(body.getAngle());

		if (commandExtend) {
			if (joint.getJointTranslation() < component.getBoundingBox().x * .8f)
				joint.setMotorSpeed(10);
			else
				joint.setMotorSpeed(0);
		} else {
			if (joint.getJointTranslation() > component.getBoundingBox().x * .1f)
				joint.setMotorSpeed(-10);
			else
				joint.setMotorSpeed(0);
		}
	}

}
