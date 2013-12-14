package com.ironalloygames.ringofoil;

import java.util.ArrayList;
import java.util.List;

import com.ironalloygames.ringofoil.component.Component;

public class Robot {
	Component rootComponent;

	public Robot() {

	}

	public List<Component> getComponents() {
		ArrayList<Component> components = new ArrayList<Component>();

		if (rootComponent == null)
			return components;

		components.add(rootComponent);

		rootComponent.addChildComponents(components);

		return components;
	}

	public Component getRootComponent() {
		return rootComponent;
	}

	public void setRootComponent(Component rootComponent) {
		this.rootComponent = rootComponent;
	}
}
