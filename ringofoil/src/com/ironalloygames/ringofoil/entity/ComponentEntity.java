package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Attachment;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.component.Component;

public abstract class ComponentEntity extends Entity {
	Component component;

	Vector2 relativePosition;

	public ComponentEntity(Component component, Vector2 robotCenter,
			boolean flipped) {
		this.component = component;
		this.relativePosition = component.getRelativePosition();

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

			if (att.getPoint() != AttachmentPoint.ARM) {
				WeldJointDef jd = new WeldJointDef();
				jd.bodyA = body;
				jd.bodyB = child.body;

				System.out.println("Welding " + jd.bodyA.getPosition() + " to "
						+ jd.bodyB.getPosition());

				((ArenaState) RG.currentState).world.createJoint(jd);
			}
		}
	}

	@Override
	public void render() {
		super.render();

		component.render(body.getPosition(), body.getAngle());

		/*
		 * component.render(superEntity.body.getWorldPoint(relativePosition),
		 * superEntity.body.getAngle());
		 * 
		 * if (component.getParent() != null) { Vector2 pt =
		 * component.getParent().getCenterPoint().scl(-1); pt =
		 * superEntity.body.getWorldPoint(pt); pt.add(relativePosition);
		 * 
		 * RG.batch.draw(RG.am.get("connector"), pt.x, pt.y, .5f, .5f, 1, 1, 16
		 * / 128f, 16 / 128f, superEntity.body.getAngle());
		 * 
		 * }
		 */
	}
}
