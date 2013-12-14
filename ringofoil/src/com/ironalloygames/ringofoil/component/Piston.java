package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.entity.ComponentEntity;
import com.ironalloygames.ringofoil.entity.PistonEntity;

public class Piston extends Component {

	@Override
	public ComponentEntity createEntity(Vector2 robotCenter, boolean flipped) {
		return new PistonEntity(this, robotCenter, flipped);
	}

	@Override
	public boolean isAttachmentPointValid(AttachmentPoint point) {
		return point == AttachmentPoint.LEFT || point == AttachmentPoint.RIGHT;
	}

	@Override
	public void render(Vector2 position, float rotation, boolean flipped) {
		super.render(position, rotation, flipped);

		render(position, position, rotation, flipped);
	}

	public void render(Vector2 position, Vector2 rodPosition, float rotation,
			boolean flipped) {

		renderSized(rodPosition, rotation, flipped, "piston_rod");
		renderSized(position, rotation, flipped, "piston_base");

	}
}
