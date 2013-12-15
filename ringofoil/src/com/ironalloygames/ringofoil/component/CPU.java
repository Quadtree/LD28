package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.entity.CPUEntity;
import com.ironalloygames.ringofoil.entity.ComponentEntity;

public class CPU extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5540318526074511630L;

	@Override
	public ComponentEntity createEntity(Vector2 robotCenter, boolean flipped) {
		return new CPUEntity(this, robotCenter, flipped);
	}

	@Override
	public Vector2 getBoundingBox() {
		return super.getBoundingBox().scl(0.85f);
	}

	@Override
	public void render(Vector2 position, float rotation, boolean flipped) {
		super.render(position, rotation, flipped);

		renderSized(position, rotation, flipped, "plate");
		renderSized(position, rotation, flipped, "cpu");
	}

}
