package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Attachment;
import com.ironalloygames.ringofoil.component.Component;

public abstract class ComponentEntity extends Entity {
	Component component;

	boolean flipped;

	Joint parentConnector;

	Vector2 relativePosition;

	public ComponentEntity(Component component, Vector2 robotCenter,
			boolean flipped) {
		this.component = component;
		this.relativePosition = component.getRelativePosition();
		this.flipped = flipped;

		System.out.println(flipped);

		if (flipped) {
			this.relativePosition.x *= -1;
		}

		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.position.x = relativePosition.x + robotCenter.x;
		bd.position.y = relativePosition.y + robotCenter.y;

		System.out.println("Creating body at " + bd.position);

		body = ((ArenaState) RG.currentState).world.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(component.getBoundingBox().x / 2,
				component.getBoundingBox().y / 2);

		body.createFixture(shape, 1);

		for (Attachment att : component.getChildren()) {
			ComponentEntity child = att.getChild().createEntity(robotCenter,
					flipped);

			createJointToChild(att, child);
		}
	}

	public void createJointToChild(Attachment att, ComponentEntity child) {
		WeldJointDef jd = new WeldJointDef();
		jd.bodyA = body;
		jd.bodyB = child.body;

		Vector2 delta = jd.bodyB.getPosition().cpy()
				.sub(jd.bodyA.getPosition());

		// Vector2 centerPoint =
		// jd.bodyA.getPosition().cpy().add(delta.cpy().scl(.5f));

		Vector2 localAnchorA = delta.cpy().scl(.5f);
		Vector2 localAnchorB = delta.cpy().scl(-.5f);

		jd.localAnchorA.x = localAnchorA.x;
		jd.localAnchorA.y = localAnchorA.y;
		jd.localAnchorB.x = localAnchorB.x;
		jd.localAnchorB.y = localAnchorB.y;

		child.setParentConnector(((ArenaState) RG.currentState).world
				.createJoint(jd));
	}

	public Joint getParentConnector() {
		return parentConnector;
	}

	@Override
	public void render() {
		super.render();

		component.render(body.getPosition(), body.getAngle(), flipped);

		if (component.getParent() != null) {
			Vector2 pt = component.getParent().getCenterPoint().scl(-1);

			if (flipped)
				pt.x *= -1;

			pt = body.getWorldPoint(pt);

			RG.batch.draw(RG.am.get("connector"), pt.x, pt.y, .5f, .5f, 1, 1,
					16 / 128f, 16 / 128f, body.getAngle()
							* (180f / MathUtils.PI));

		}
	}

	public void setParentConnector(Joint parentConnector) {
		this.parentConnector = parentConnector;
	}
}
