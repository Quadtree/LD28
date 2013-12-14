package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.physics.box2d.Body;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;

public abstract class Entity {
	Body body;

	public Entity() {
		((ArenaState) RG.currentState).addEntity(this);
	}

	public void render() {
	}

	public void update() {

	}
}
