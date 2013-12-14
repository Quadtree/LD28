package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;

public class Attachment {
	public enum AttachmentPoint {
		ARM, BOTTOM, LEFT, RIGHT, TOP
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
		return this.getChildRelativePosition().scl(child != null ? .5f : 1);
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
		case ARM:
			delta = new Vector2(.7f, 0);
			break;
		default:
			throw new RuntimeException("Att pt " + point + "not fnd");
		}

		Vector2 accum = delta.cpy().scl(parent.getBoundingBox().x / 2,
				parent.getBoundingBox().y / 2);

		accum.add(delta.cpy().scl(8 / 128f, 8 / 128f));

		if (child != null) {
			accum.add(delta.cpy().scl(child.getBoundingBox().x / 2,
					child.getBoundingBox().y / 2));
		}
		return accum;
	}

	public Component getParent() {
		return parent;
	}

	public AttachmentPoint getPoint() {
		return point;
	}
}
