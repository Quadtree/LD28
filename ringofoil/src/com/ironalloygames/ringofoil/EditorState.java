package com.ironalloygames.ringofoil;

import com.ironalloygames.ringofoil.component.CPU;
import com.ironalloygames.ringofoil.component.Component;

public class EditorState extends GameState {

	Robot robot;

	public EditorState() {
		robot = new Robot();
		robot.setRootComponent(new CPU());
	}

	@Override
	public void render() {
		for (Component c : robot.getComponents()) {
			c.render();
		}
		super.render();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

}
