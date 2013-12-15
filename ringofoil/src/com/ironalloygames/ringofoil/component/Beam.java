package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.entity.BeamEntity;
import com.ironalloygames.ringofoil.entity.ComponentEntity;

public class Beam extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4425996261038365833L;

	@Override
	public ComponentEntity createEntity(Vector2 robotCenter, boolean flipped) {
		return new BeamEntity(this, robotCenter, flipped);
	}

	@Override
	public Vector2 getBoundingBox() {
		return new Vector2(128f * SCALE / 128f, 25f * SCALE / 128f);
	}

	@Override
	public float getHp() {
		return .5f;
	}

	@Override
	protected int getUnitCost() {
		return 8;
	}

	@Override
	public void render(Vector2 position, float rotation, boolean flipped) {
		super.render(position, rotation, flipped);

		renderSized(position, rotation, flipped, "beam");

	}

}
