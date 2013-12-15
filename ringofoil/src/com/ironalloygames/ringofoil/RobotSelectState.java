package com.ironalloygames.ringofoil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.ironalloygames.ringofoil.component.Component;

public class RobotSelectState extends GameState {

	EditorState editorState;

	String[] robots;

	int selectedIndex = -1;
	Robot selectedRobot;

	public RobotSelectState(EditorState editorState) {
		robots = RG.rsd.list();
		this.editorState = editorState;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ESCAPE) {
			if (editorState != null) {
				RG.currentState = editorState;
				Gdx.input.setInputProcessor(RG.currentState);
			} else {
				RG.currentState = new MainMenuState();
				Gdx.input.setInputProcessor(RG.currentState);
			}
		}

		if (keycode == Keys.ENTER) {
			if (editorState != null) {
				editorState.robot = selectedRobot;
				RG.currentState = editorState;
				Gdx.input.setInputProcessor(RG.currentState);
			} else {
				RG.tr = new Tournament(selectedRobot);
				RG.tr.nextMatch();
			}
		}

		return super.keyDown(keycode);
	}

	@Override
	public void render() {

		if (selectedRobot != null) {
			for (Component c : selectedRobot.getComponents()) {
				c.render(c.getRelativePosition(), 0, false);
				c.renderAttachmentPoints();
				c.renderConnector();
			}
		}

		super.render();
	}

	@Override
	public void renderUi() {
		super.renderUi();

		RG.am.getFont().drawWrapped(RG.batch, "Commands: Esc - Go back, Enter - Accept selection, Mouse Wheel/Arrow Keys - Scroll", -400, -420, 800);

		int y = 420;

		for (String robot : robots) {
			RG.am.getFont().drawWrapped(RG.batch, robot.replace(".robot", ""), -400, y, 800);

			y -= 20;
		}
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		int row = (int) ((420 - mouseScreenPosition.y) / 20) - 7;

		if (row >= 0 && row < robots.length) {
			selectedIndex = row;
			selectedRobot = RG.rsd.loadRobot(robots[row].replace(".robot", ""));
		}

		return super.touchDown(screenX, screenY, pointer, button);
	}

}
