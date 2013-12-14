package com.ironalloygames.ringofoil.entity;

import com.ironalloygames.ringofoil.component.CPU;

public class CPUEntity extends ComponentEntity {
	CPU cpu;

	public CPUEntity(SuperEntity superEntity, CPU cpu) {
		super(superEntity, cpu);
		this.cpu = cpu;
	}
}
