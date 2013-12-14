package com.ironalloygames.ringofoil.entity;

import com.ironalloygames.ringofoil.component.Structure;

public class StructureEntity extends ComponentEntity {
	Structure structure;

	public StructureEntity(SuperEntity superEntity, Structure structure) {
		super(superEntity, structure);
		this.structure = structure;
	}
}
