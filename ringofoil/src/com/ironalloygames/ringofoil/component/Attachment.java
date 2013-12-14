package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;

public class Attachment {
	enum AttachmentPoint {
		BOTTOM, LEFT, RIGHT, TOP
	}

	Component child;
	Component parent;
	AttachmentPoint point;

	public Attachment(Component child, Component parent, AttachmentPoint point) {
		super();
		this.child = child;
		this.parent = parent;
		this.point = point;
	}

	public Vector2 getCenterPoint() {
		return this.getChildRelativePosition().scl(.5f);
	}

	public Component getChild() {
		return child;
	}

	public Vector2 getChildRelativePosition() {
		return new Vector2(0, 0);
	}

	public Component getParent() {
		return parent;
	}

	public AttachmentPoint getPoint() {
		return point;
	}
}
