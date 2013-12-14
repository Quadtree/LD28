package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.component.Component;
import com.ironalloygames.ringofoil.component.Piston;

public class PistonEntity extends ComponentEntity {

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

		joint.setMotorSpeed(100);
	}

	@Override
	public void commandKeyUp() {
		super.commandKeyUp();

		joint.setMotorSpeed(-100);
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

		PrismaticJointDef jd = new PrismaticJointDef();
		jd.initialize(body, rodBody, body.getPosition(), new Vector2(1, 0));
		jd.enableMotor = true;
		jd.enableLimit = true;
		jd.lowerTranslation = 0;
		jd.upperTranslation = component.getBoundingBox().x * .9f;
		jd.maxMotorForce = 10;

		joint = (PrismaticJoint) ((ArenaState) RG.currentState).world
				.createJoint(jd);
	}

	@Override
	protected Body getBodyForChildConnection(AttachmentPoint ap) {
		return rodBody;
	}

	@Override
	public void render() {
		piston.render(body.getPosition(), rodBody.getPosition(),
				body.getAngle(), rodBody.getAngle(), flipped);

		renderConnector();
	}

}
