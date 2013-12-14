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
		System.out.println(robot.getComponents());
		for (Component c : robot.getComponents()) {
			c.render(c.getRelativePosition(), 0);
		}
		super.render();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

}
