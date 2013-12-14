package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.entity.ComponentEntity;
import com.ironalloygames.ringofoil.entity.StructureEntity;

public class Structure extends Component {
	@Override
	public ComponentEntity createEntity() {
		return new StructureEntity(this);
	}

	@Override
	public void render(Vector2 position, float rotation) {
		super.render(position, rotation);

		RG.batch.draw(RG.am.get("plate"), position.x, position.y, .5f, .5f, 1,
				1, getBoundingBox().x, getBoundingBox().y, rotation);

	}
}
