package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.entity.CannonEntity;
import com.ironalloygames.ringofoil.entity.ComponentEntity;

public class Cannon extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1920461711973887184L;

	public int roundsLeft = 8;

	public Cannon() {
	}

	@Override
	public ComponentEntity createEntity(Vector2 robotCenter, boolean flipped) {
		return new CannonEntity(this, robotCenter, flipped);
	}

	@Override
	protected int getUnitCost() {
		return 200;
	}

	@Override
	public boolean isAttachmentPointValid(AttachmentPoint point) {
		return point == AttachmentPoint.LEFT;
	}

	@Override
	public void render(Vector2 position, float rotation, boolean flipped) {
		super.render(position, rotation, flipped);

		renderSized(position, rotation, flipped, "cannon");

	}
}
