package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.entity.ComponentEntity;
import com.ironalloygames.ringofoil.entity.StructureEntity;

public class Structure extends Component {
	/**
	 * 
	 */
	private static final long serialVersionUID = -996018436834585699L;

	@Override
	public ComponentEntity createEntity(Vector2 robotCenter, boolean flipped) {
		return new StructureEntity(this, robotCenter, flipped);
	}

	@Override
	protected int getUnitCost() {
		return 20;
	}

	@Override
	public void render(Vector2 position, float rotation, boolean flipped) {
		super.render(position, rotation, flipped);

		renderSized(position, rotation, flipped, "plate");

	}
}
