package com.ironalloygames.ringofoil;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Component;
import com.ironalloygames.ringofoil.entity.SuperEntity;

public class Robot {
	Component rootComponent;

	public Robot() {

	}

	public SuperEntity generate(Vector2 position, boolean flipped) {
		System.out.println("Initiating generation.");

		SuperEntity se = new SuperEntity(position);

		for (Component c : getComponents()) {
			System.out.println("Generating " + c);

			se.addEntity(c.createEntity(se));
		}

		se.rebake();

		return se;

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
