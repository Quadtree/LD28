package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.entity.ComponentEntity;
import com.ironalloygames.ringofoil.entity.TracksEntity;

public class Tracks extends Component {
	@Override
	public ComponentEntity createEntity(Vector2 robotCenter, boolean flipped) {
		return new TracksEntity(this, robotCenter, flipped);
	}

	@Override
	public void render(Vector2 position, float rotation, boolean flipped) {
		super.render(position, rotation, flipped);

		renderSized(position, rotation, flipped, "tracks");

	}
}
