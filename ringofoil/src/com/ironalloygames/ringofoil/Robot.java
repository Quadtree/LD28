package com.ironalloygames.ringofoil;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Component;

public class Robot {
	Component rootComponent;

	public Robot() {

	}

	public void generate(Vector2 position, boolean flipped) {
		System.out.println("Initiating generation.");

		rootComponent.createEntity(position, flipped);

		/*
		 * HashMap<Component, ComponentEntity> entityMap = new
		 * HashMap<Component, ComponentEntity>();
		 * 
		 * for (Component c : getComponents()) {
		 * System.out.println("Generating " + c); ComponentEntity e =
		 * c.createEntity();
		 * e.setPosition(c.getRelativePosition().add(position));
		 * entityMap.put(c, e); ((ArenaState) RG.currentState).entities.add(e);
		 * }
		 * 
		 * for (Component c : getComponents()) {
		 * entityMap.get(c).generateAttachments(entityMap); }
		 */
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
