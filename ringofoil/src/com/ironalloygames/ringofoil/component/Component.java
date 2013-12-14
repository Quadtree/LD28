package com.ironalloygames.ringofoil.component;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;

public class Component {
	public static float SCALE = .5f;
	public static float STANDARD_ASPECT_RATIO = .625f;

	List<Attachment> children = new ArrayList<Attachment>();
	Attachment parent;

	public void addChildComponent(Component childComponent,
			AttachmentPoint point) {

	}

	public void addChildComponentsToList(ArrayList<Component> components) {
	}

	public Vector2 getRelativePosition() {
		if (parent == null) {
			return new Vector2(0, 0);
		}

		return parent.getParent().getRelativePosition()
				.add(parent.getChildRelativePosition()).cpy();
	}

	public boolean isAttachmentPointValid(AttachmentPoint point) {
		return true;
	}

	public void render(Vector2 position, float rotation) {

	}
}
