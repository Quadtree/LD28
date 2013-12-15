package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.entity.ComponentEntity;
import com.ironalloygames.ringofoil.entity.PistonEntity;

public class Piston extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2182400434913569226L;

	@Override
	public ComponentEntity createEntity(Vector2 robotCenter, boolean flipped) {
		return new PistonEntity(this, robotCenter, flipped);
	}

	@Override
	public Vector2 getBoundingBox() {
		return super.getBoundingBox().scl(1, .3f);
	}

	@Override
	protected int getUnitCost() {
		return 60;
	}

	@Override
	public boolean isAttachmentPointValid(AttachmentPoint point) {
		return point == AttachmentPoint.LEFT || point == AttachmentPoint.RIGHT;
	}

	@Override
	public void render(Vector2 position, float rotation, boolean flipped) {
		super.render(position, rotation, flipped);

		render(position, position, rotation, rotation, flipped);
	}

	public void render(Vector2 position, Vector2 rodPosition, float rotation, float rodRotation, boolean flipped) {

		renderSized(rodPosition, rodRotation, flipped, "piston_rod");
		renderSized(position, rotation, flipped, "piston_base");

	}
}
