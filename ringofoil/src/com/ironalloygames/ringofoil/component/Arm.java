package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.entity.ArmEntity;
import com.ironalloygames.ringofoil.entity.ComponentEntity;

public class Arm extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1802276674561545849L;

	public Arm() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ComponentEntity createEntity(Vector2 robotCenter, boolean flipped) {
		return new ArmEntity(this, robotCenter, flipped);
	}

	@Override
	public boolean isAttachmentPointValid(AttachmentPoint point) {
		return true;
	}

	@Override
	public void render(Vector2 position, float rotation, boolean flipped) {
		super.render(position, rotation, flipped);

		render(position, rotation, rotation, flipped);
	}

	public void render(Vector2 position, float rotation, float armRotation, boolean flipped) {
		renderSized(position, rotation, flipped, "plate");
		renderSized(position, armRotation, flipped, "arm");
	}

}
