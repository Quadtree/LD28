package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;

public class Attachment {
	public enum AttachmentPoint {
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
		Vector2 delta;

		switch (point) {
		case BOTTOM:
			delta = new Vector2(0, -1);
			break;
		case TOP:
			delta = new Vector2(0, 1);
			break;
		case LEFT:
			delta = new Vector2(-1, 0);
			break;
		case RIGHT:
			delta = new Vector2(1, 0);
			break;
		default:
			throw new RuntimeException("Att pt not fnd");
		}

		Vector2 accum = delta.cpy().scl(parent.getBoundingBox().x / 2,
				parent.getBoundingBox().y / 2);

		accum.add(delta.cpy().scl(child.getBoundingBox().x / 2,
				child.getBoundingBox().y / 2));

		return accum;
	}

	public Component getParent() {
		return parent;
	}

	public AttachmentPoint getPoint() {
		return point;
	}
}
