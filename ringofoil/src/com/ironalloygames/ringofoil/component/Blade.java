package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.entity.BladeEntity;
import com.ironalloygames.ringofoil.entity.ComponentEntity;

public class Blade extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6188507153631834428L;

	public Blade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ComponentEntity createEntity(Vector2 robotCenter, boolean flipped) {
		return new BladeEntity(this, robotCenter, flipped);
	}

	@Override
	public Vector2 getBoundingBox() {
		return new Vector2(91f / 128f * SCALE, 71f / 128f * SCALE);
	}

	@Override
	public float getHeavyDamageResistance() {
		return 1.5f;
	}

	@Override
	public float getLightDamageMultiplier() {
		return 7f;
	}

	@Override
	protected int getUnitCost() {
		return 35;
	}

	@Override
	public boolean isAttachmentPointValid(AttachmentPoint point) {
		return point == AttachmentPoint.LEFT;
	}

	@Override
	public void render(Vector2 position, float rotation, boolean flipped) {
		super.render(position, rotation, flipped);

		renderSized(position, rotation, flipped, "blade");

	}

}
