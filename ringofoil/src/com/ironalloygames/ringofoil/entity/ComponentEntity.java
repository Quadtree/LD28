package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Component;

public abstract class ComponentEntity extends Entity {
	Component component;

	Vector2 relativePosition;

	public ComponentEntity(SuperEntity superEntity, Component component) {
		super(superEntity);
		this.component = component;
		this.relativePosition = component.getRelativePosition();
	}

	@Override
	public Shape getShape() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(component.getBoundingBox().x / 2,
				component.getBoundingBox().y / 2, relativePosition, 0);
		return shape;
	}

	@Override
	public void render() {
		super.render();

		component.render(superEntity.body.getWorldPoint(relativePosition),
				superEntity.body.getAngle());

		if (component.getParent() != null) {
			Vector2 pt = component.getParent().getCenterPoint().scl(-1);
			pt = superEntity.body.getWorldPoint(pt);
			pt.add(relativePosition);

			RG.batch.draw(RG.am.get("connector"), pt.x, pt.y, .5f, .5f, 1, 1,
					16 / 128f, 16 / 128f, superEntity.body.getAngle());

		}
	}
}
