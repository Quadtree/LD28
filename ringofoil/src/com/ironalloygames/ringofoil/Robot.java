package com.ironalloygames.ringofoil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Component;
import com.ironalloygames.ringofoil.entity.Entity;

public class Robot {
	Component rootComponent;

	public Robot() {

	}

	public void generate(Vector2 position, boolean flipped) {
		System.out.println("Initiating generation.");

		HashMap<Component, Entity> entityMap = new HashMap<Component, Entity>();

		for (Component c : getComponents()) {
			System.out.println("Generating " + c);
			Entity e = c.createEntity();
			e.setPosition(c.getRelativePosition().add(position));
			entityMap.put(c, e);
			((ArenaState) RG.currentState).entities.add(e);
		}
	}

	public List<Component> getComponents() {
		ArrayList<Component> components = new ArrayList<Component>();

		if (rootComponent == null)
			return components;

		components.add(rootComponent);

		rootComponent.addChildComponentsToList(components);

		return components;
	}

	public Component getRootComponent() {
		return rootComponent;
	}

	public void setRootComponent(Component rootComponent) {
		this.rootComponent = rootComponent;
	}
}
