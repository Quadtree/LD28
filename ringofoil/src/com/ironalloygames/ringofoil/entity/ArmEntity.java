package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Arm;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.component.Component;

public class ArmEntity extends ComponentEntity {

	Vector2 aimPoint;

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
		bd.angularDamping = .8f;

		System.out.println("Creating rod body at " + bd.position);

		rodBody = ((ArenaState) RG.currentState).world.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(component.getBoundingBox().x / 2, component.getBoundingBox().y / 2 / 2);

		rodBody.createFixture(shape, getDensity()).setFilterData(getFilter());

		System.out.println("GINDEX " + rodBody.getFixtureList().get(0).getFilterData().groupIndex);

		RevoluteJointDef jd = new RevoluteJointDef();
		jd.initialize(body, rodBody, body.getPosition());
		jd.enableMotor = true;
		jd.maxMotorTorque = 5;
		jd.motorSpeed = 0;

		joint = (RevoluteJoint) ((ArenaState) RG.currentState).world.createJoint(jd);
	}

	@Override
	protected Body getBodyForChildConnection(AttachmentPoint ap) {
		return rodBody;
	}

	@Override
	public void render() {
		((Arm) component).render(body.getPosition(), body.getAngle(), rodBody.getAngle(), flipped);

		renderConnector();
	}

	public void setAimPoint(Vector2 aimPoint) {
		this.aimPoint = aimPoint.cpy();
	}

	@Override
	protected void setToNewGroup(short group) {
		Filter fd = new Filter();
		fd.categoryBits = 2;
		fd.maskBits = 2;
		fd.groupIndex = group;

		rodBody.getFixtureList().get(0).setFilterData(fd);

		super.setToNewGroup(group);
	}

	@Override
	public void update() {
		super.update();

		// System.out.println("Aiming at " + aimPoint);

		Vector2 leftPoint = rodBody.getWorldPoint(new Vector2(.1f, -.02f)).cpy();
		Vector2 centerPoint = rodBody.getWorldPoint(new Vector2(.101f, 0)).cpy();
		Vector2 rightPoint = rodBody.getWorldPoint(new Vector2(.1f, .02f)).cpy();

		float leftDist = leftPoint.sub(aimPoint).len2();
		float centerDist = centerPoint.sub(aimPoint).len2();
		float rightDist = rightPoint.sub(aimPoint).len2();

		// System.out.println(leftPoint + " " + centerPoint + " " + rightPoint);
		// System.out.println(leftDist + " " + centerDist + " " + rightDist);

		if (!loose) {
			if (leftDist < centerDist && leftDist < rightDist) {
				joint.setMotorSpeed(-3);
			} else if (rightDist < centerDist && rightDist < leftDist) {
				joint.setMotorSpeed(3);
			} else {
				joint.setMotorSpeed(0);
			}
		} else {
			joint.enableMotor(false);
		}
	}
}
