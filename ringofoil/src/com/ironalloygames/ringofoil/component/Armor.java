package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.entity.ArmorEntity;
import com.ironalloygames.ringofoil.entity.ComponentEntity;

public class Armor extends Component {

	public Armor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ComponentEntity createEntity(Vector2 robotCenter, boolean flipped) {
		return new ArmorEntity(this, robotCenter, flipped);
	}

	@Override
	public Vector2 getBoundingBox() {
		return new Vector2(32 / 128f * SCALE, 80 / 128f * SCALE);
	}

	@Override
	public int getCost() {
		return 45;
	}

	@Override
	public float getHeavyDamageResistance() {
		return .8f;
	}

	@Override
	public float getLightDamageResistance() {
		return .1f;
	}

	@Override
	public float getMaxHp() {
		return 2;
	}

	@Override
	public boolean isAttachmentPointValid(AttachmentPoint point) {
		return point == AttachmentPoint.LEFT;
	}

	@Override
	public void render(Vector2 position, float rotation, boolean flipped) {
		super.render(position, rotation, flipped);

		renderSized(position, rotation, flipped, "armor");

	}
}
