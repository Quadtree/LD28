package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Entity {
	Body body;

	public void render() {

	}

	public void setPosition(Vector2 position) {
		body.setTransform(position.cpy(), body.getAngle());
	}

	public void update() {

	}
}
