package com.ironalloygames.ringofoil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Component;

public class Robot implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6652893614264158955L;
	Component rootComponent;

	public Robot() {

	}

	public void generate(Vector2 position, boolean flipped) {
		System.out.println("Initiating generation.");

		rootComponent.createEntity(position, flipped);
	}

	public List<Component> getComponents() {
		ArrayList<Component> components = new ArrayList<Component>();

		if (rootComponent == null)
			return components;

		components.add(rootComponent);

		rootComponent.addChildComponentsToList(components);

		return components;
	}

	public int getCost() {
		return rootComponent.getCost();
	}

	public Component getRootComponent() {
		return rootComponent;
	}

	public void setRootComponent(Component rootComponent) {
		this.rootComponent = rootComponent;
	}
}
