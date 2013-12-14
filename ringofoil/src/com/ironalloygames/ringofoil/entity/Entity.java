package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.physics.box2d.Shape;

public abstract class Entity {
	SuperEntity superEntity;

	public Entity(SuperEntity superEntity) {
		this.superEntity = superEntity;
	}

	public float getDensity() {
		return 1;
	}

	public abstract Shape getShape();

	public void render() {

	}

	public void update() {

	}
}
