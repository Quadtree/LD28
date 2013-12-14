package com.ironalloygames.ringofoil.entity;

import java.util.Map;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Component;

public abstract class ComponentEntity extends Entity {
	Component component;

	public ComponentEntity(Component component) {
		this.component = component;

		ArenaState as = ((ArenaState) RG.currentState);

		BodyDef bd = new BodyDef();
		bd.position.x = 0;
		bd.position.y = 0;
		bd.type = BodyDef.BodyType.DynamicBody;

		body = as.world.createBody(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(component.getBoundingBox().x / 2,
				component.getBoundingBox().y / 2);

		body.createFixture(shape, 1);
	}

	public void generateAttachments(Map<Component, ComponentEntity> entityMap) {

	}

	@Override
	public void render() {
		super.render();

		component.render(body.getPosition(), body.getAngle());
	}
}
