package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.entity.ComponentEntity;
import com.ironalloygames.ringofoil.entity.TracksEntity;

public class Tracks extends Component {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1084798913635297965L;

	@Override
	public ComponentEntity createEntity(Vector2 robotCenter, boolean flipped) {
		return new TracksEntity(this, robotCenter, flipped);
	}

	@Override
	protected int getUnitCost() {
		return 35;
	}

	@Override
	public boolean isAttachmentPointValid(AttachmentPoint point) {
		return point == AttachmentPoint.TOP;
	}

	@Override
	public void render(Vector2 position, float rotation, boolean flipped) {
		super.render(position, rotation, flipped);

		renderWithWheels(position, rotation, flipped, new Vector2[] { new Vector2(-getBoundingBox().x / 2 * .6f, -getBoundingBox().y / 2 * .4f).add(position),

		new Vector2(getBoundingBox().x / 2 * .6f, -getBoundingBox().y / 2 * .4f).add(position), new Vector2(0, -getBoundingBox().y / 2 * .4f).add(position) }, new float[] { 0, 0, 0 });

	}

	public void renderWithWheels(Vector2 position, float rotation, boolean flipped, Vector2[] wheelPos, float[] wheelRot) {
		renderSized(position, rotation, flipped, "tracks");

		for (int i = 0; i < 3; i++) {
			RG.batch.draw(RG.am.get("wheel"), wheelPos[i].x - .5f, wheelPos[i].y - .5f, .5f, .5f, 1, 1, .25f * SCALE, .25f * SCALE, wheelRot[i] * (180f / MathUtils.PI));
		}
	}
}
