package com.ironalloygames.ringofoil.component;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.entity.ComponentEntity;

public abstract class Component {
	public static float SCALE = .5f;
	public static float STANDARD_ASPECT_RATIO = .625f;

	List<Attachment> children = new ArrayList<Attachment>();
	int commandKey;

	float hp;

	Attachment parent;

	public void addChildComponent(Component childComponent,
			AttachmentPoint point) {
		Attachment att = new Attachment(childComponent, this, point);
		childComponent.parent = att;
		children.add(att);
	}

	public void addChildComponentsToList(ArrayList<Component> components) {
		for (Attachment att : children) {
			components.add(att.getChild());
			att.getChild().addChildComponentsToList(components);
		}
	}

	public abstract ComponentEntity createEntity(Vector2 robotCenter,
			boolean flipped);

	public List<Attachment> getAllAttachmentsExceptParent() {
		return children;
	}

	public Vector2 getBoundingBox() {
		return new Vector2(1 * SCALE, 1 * SCALE * STANDARD_ASPECT_RATIO);
	}

	public List<Attachment> getChildren() {
		return children;
	}

	public int getCommandKey() {
		return commandKey;
	}

	public float getHp() {
		return hp;
	}

	public float getMaxHp() {
		return 1;
	}

	public Attachment getParent() {
		return parent;
	}

	public Vector2 getRelativePosition() {
		if (parent == null) {
			return new Vector2(0, 0);
		}

		return parent.getParent().getRelativePosition()
				.add(parent.getChildRelativePosition()).cpy();
	}

	public boolean isAttachmentPointConnected(AttachmentPoint point) {
		if (parent != null
				&& ((parent.point.equals(AttachmentPoint.TOP) && point
						.equals(AttachmentPoint.BOTTOM))
						|| (parent.point.equals(AttachmentPoint.BOTTOM) && point
								.equals(AttachmentPoint.TOP))
						|| (parent.point.equals(AttachmentPoint.LEFT) && point
								.equals(AttachmentPoint.RIGHT))
						|| (parent.point.equals(AttachmentPoint.RIGHT) && point
								.equals(AttachmentPoint.LEFT))
						|| (parent.point.equals(AttachmentPoint.ARM) && point
								.equals(AttachmentPoint.LEFT)) || (parent.point
						.equals(AttachmentPoint.LEFT) && point
						.equals(AttachmentPoint.ARM))))
			return true;

		for (Attachment ap : children) {
			if (ap.point.equals(point))
				return true;
		}

		return false;
	}

	public boolean isAttachmentPointValid(AttachmentPoint point) {
		if (point != AttachmentPoint.ARM)
			return true;
		else
			return false;
	}

	public void render(Vector2 position, float rotation, boolean flipped) {

	}

	public void renderAttachmentPoints() {
		for (AttachmentPoint ap : AttachmentPoint.values()) {
			if (isAttachmentPointValid(ap) && !isAttachmentPointConnected(ap)) {
				Attachment a = new Attachment(null, this, ap);

				Vector2 pt = a.getCenterPoint().add(this.getRelativePosition());

				RG.batch.draw(RG.am.get("att_pt"), pt.x, pt.y, .5f, .5f, 1, 1,
						.1f, .1f, 0);
			}
		}
	}

	protected void renderSized(Vector2 position, float rotation,
			boolean flipped, String graphic) {
		if (RG.am.get(graphic).isFlipX() != flipped)
			RG.am.get(graphic).flip(true, false);

		Vector2 bb = getBoundingBox();

		RG.batch.draw(RG.am.get(graphic), position.x - .5f, position.y - .5f,
				.5f, .5f, 1, 1, bb.x, bb.y, rotation * (180f / MathUtils.PI));
	}

	public void setCommandKey(int commandKey) {
		this.commandKey = commandKey;
	}

	public void setHp(float hp) {
		this.hp = hp;
	}

	public void takeDamage(float heavyDamage, float lightDamage) {
		this.hp -= heavyDamage;
		this.hp -= lightDamage;
	}
}
