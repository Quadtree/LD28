package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Structure;

public class StructureEntity extends ComponentEntity {
	Structure structure;

	public StructureEntity(Structure structure, Vector2 robotCenter,
			boolean flipped) {
		super(structure, robotCenter, flipped);
		this.structure = structure;
	}
}
