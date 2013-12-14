package com.ironalloygames.ringofoil.entity;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;

public class SuperEntity {
	Body body;

	ArrayList<Entity> entities = new ArrayList<Entity>();

	Vector2 startPosition;

	public SuperEntity(Vector2 position) {
		this.startPosition = position;
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void checkForDisjointSets() {

	}

	public void rebake() {
		if (body == null) {
			BodyDef bd = new BodyDef();

			bd.position.x = startPosition.x;
			bd.position.y = startPosition.y;
			bd.type = BodyType.DynamicBody;

			body = ((ArenaState) RG.currentState).world.createBody(bd);

			for (Entity e : entities) {
				body.createFixture(e.getShape(), e.getDensity());
			}
		}
	}

	public void render() {
		for (Entity e : entities) {
			e.render();
		}
	}

	public void update() {
		for (Entity e : entities) {
			e.update();
		}
	}
}
