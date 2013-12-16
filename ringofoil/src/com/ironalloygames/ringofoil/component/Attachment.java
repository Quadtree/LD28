package com.ironalloygames.ringofoil.component;

import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

public class Attachment implements Serializable {
	public enum AttachmentPoint {
		ARM, BOTTOM, LEFT, RIGHT, TOP
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6485381487566697837L;

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

		Vector2 delta1 = delta.cpy().scl(parent.getBoundingBox().x / 2, parent.getBoundingBox().y / 2);

		Vector2 delta2 = new Vector2(0, 0);

		if (child != null) {
			delta2 = delta.cpy().scl(child.getBoundingBox().x / 2, child.getBoundingBox().y / 2);
		}

		Vector2 accum = new Vector2(-1000, -1000);

		if (delta1.len2() > delta2.len2()) {

			accum = delta1;
			accum.sub(delta.cpy().scl(4 / 128f, 4 / 128f));

		} else {

			accum = delta2;
			accum.add(delta.cpy().scl(4 / 128f, 4 / 128f));

		}

		return accum;
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

		Vector2 accum = delta.cpy().scl(parent.getBoundingBox().x / 2, parent.getBoundingBox().y / 2);

		accum.add(delta.cpy().scl(8 / 128f, 8 / 128f));

		if (child != null) {
			accum.add(delta.cpy().scl(child.getBoundingBox().x / 2, child.getBoundingBox().y / 2));
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
