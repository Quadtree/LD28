package com.ironalloygames.ringofoil.entity;

import com.ironalloygames.ringofoil.component.Structure;

public class StructureEntity extends ComponentEntity {
	Structure structure;

	public StructureEntity(Structure structure) {
		super(structure);
		this.structure = structure;
	}
}
