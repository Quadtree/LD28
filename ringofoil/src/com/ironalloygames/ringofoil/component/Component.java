package com.ironalloygames.ringofoil.component;

import java.util.ArrayList;
import java.util.List;

public class Component {
	List<Attachment> children = new ArrayList<Attachment>();
	Attachment parent;

	public void addChildComponentsToList(ArrayList<Component> components) {
	}

	public void render() {

	}
}
