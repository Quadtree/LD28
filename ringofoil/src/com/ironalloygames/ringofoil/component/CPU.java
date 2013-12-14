package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.RG;

public class CPU extends Component {

	@Override
	public void render(Vector2 position, float rotation) {
		super.render(position, rotation);

		RG.batch.draw(RG.am.get("plate"), position.x, position.y, -.5f, -.5f,
				1, 1, 1 * SCALE, 1 * SCALE * STANDARD_ASPECT_RATIO, rotation);
		RG.batch.draw(RG.am.get("cpu"), position.x, position.y, -.5f, -.5f, 1,
				1, 1 * SCALE, 1 * SCALE * STANDARD_ASPECT_RATIO, rotation);

	}

}
