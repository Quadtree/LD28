package com.ironalloygames.ringofoil.entity;

import com.ironalloygames.ringofoil.component.CPU;

public class CPUEntity extends ComponentEntity {
	CPU cpu;

	public CPUEntity(CPU cpu) {
		super(cpu);
		this.cpu = cpu;
	}
}
