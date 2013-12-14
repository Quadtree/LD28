package com.ironalloygames.ringofoil.component;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.entity.CPUEntity;
import com.ironalloygames.ringofoil.entity.ComponentEntity;
import com.ironalloygames.ringofoil.entity.SuperEntity;

public class CPU extends Component {

	@Override
	public ComponentEntity createEntity(SuperEntity superEntity) {
		return new CPUEntity(superEntity, this);
	}

	@Override
	public Vector2 getBoundingBox() {
		return super.getBoundingBox().scl(0.85f);
	}

	@Override
	public void render(Vector2 position, float rotation) {
		super.render(position, rotation);

		RG.batch.draw(RG.am.get("plate"), position.x, position.y, .5f, .5f, 1,
				1, getBoundingBox().x, getBoundingBox().y, rotation);
		RG.batch.draw(RG.am.get("cpu"), position.x, position.y, .5f, .5f, 1, 1,
				getBoundingBox().x, getBoundingBox().y, rotation);

	}

}
