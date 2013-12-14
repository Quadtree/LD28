package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.ironalloygames.ringofoil.component.Component;

public abstract class ComponentEntity extends Entity {
	Component component;

	Vector2 relativePosition;

	public ComponentEntity(SuperEntity superEntity, Component component) {
		super(superEntity);
		this.component = component;
		this.relativePosition = component.getRelativePosition();
	}

	public float getDensity() {
		return 1;
	}

	public Vector2 getRelativePosition() {
		return component.getRelativePosition();
	}

	public Shape getShape() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(component.getBoundingBox().x / 2,
				component.getBoundingBox().y / 2);
		return shape;
	}

	@Override
	public void render() {
		super.render();

		component.render(superEntity.body.getWorldPoint(relativePosition),
				superEntity.body.getAngle());
	}
}
