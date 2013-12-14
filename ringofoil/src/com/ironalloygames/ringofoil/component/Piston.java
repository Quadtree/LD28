package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.entity.ComponentEntity;

public class Piston extends Component {

	@Override
	public ComponentEntity createEntity(Vector2 robotCenter, boolean flipped) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAttachmentPointValid(AttachmentPoint point) {
		return point == AttachmentPoint.LEFT || point == AttachmentPoint.RIGHT;
	}

}
