package com.ironalloygames.ringofoil;

import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.component.CPU;
import com.ironalloygames.ringofoil.component.Component;
import com.ironalloygames.ringofoil.component.Structure;
import com.ironalloygames.ringofoil.component.Tracks;

public class EditorState extends GameState {

	Robot robot;

	public EditorState() {
		robot = new Robot();

		CPU cpu = new CPU();
		Structure structure = new Structure();
		Tracks tracks = new Tracks();

		robot.setRootComponent(cpu);
		cpu.addChildComponent(structure, AttachmentPoint.BOTTOM);
		structure.addChildComponent(tracks, AttachmentPoint.BOTTOM);
	}

	@Override
	public void render() {
		for (Component c : robot.getComponents()) {
			c.render(c.getRelativePosition(), 0);
			c.renderAttachmentPoints();
		}
		super.render();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

}
