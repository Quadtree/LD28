package com.ironalloygames.ringofoil.entity;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class SuperEntity {
	Body body;

	ArrayList<Entity> entities = new ArrayList<Entity>();

	public SuperEntity(Vector2 position) {

	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void checkForDisjointSets() {

	}

	public void rebake() {

	}

	public void render() {
		System.out.println(entities);
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
