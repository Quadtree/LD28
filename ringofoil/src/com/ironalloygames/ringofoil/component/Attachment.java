package com.ironalloygames.ringofoil.component;

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

}
