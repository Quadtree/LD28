package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.entity.ComponentEntity;
import com.ironalloygames.ringofoil.entity.LargeMaceEntity;

public class LargeMace extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3926364119283965420L;

	public LargeMace() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ComponentEntity createEntity(Vector2 robotCenter, boolean flipped) {
		return new LargeMaceEntity(this, robotCenter, flipped);
	}

	@Override
	public Vector2 getBoundingBox() {
		return new Vector2(.5f * SCALE, .5f * SCALE);
	}

	@Override
	protected int getUnitCost() {
		return 35;
	}

	@Override
	public void render(Vector2 position, float rotation, boolean flipped) {
		super.render(position, rotation, flipped);

		renderSized(position, rotation, flipped, "mace");
	}
}
