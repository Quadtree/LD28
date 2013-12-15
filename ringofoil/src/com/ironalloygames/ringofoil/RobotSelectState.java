package com.ironalloygames.ringofoil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
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
				if (selectedRobot.getCost() <= ArenaState.ROBOT_MAX_COST) {
					RG.tr = new Tournament(selectedRobot);
					RG.tr.nextMatch();
				}
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

		if (selectedRobot != null) {
			if (selectedRobot.getCost() > ArenaState.ROBOT_MAX_COST) {
				RG.am.getFont().setColor(Color.RED);
				if (editorState == null) {
					RG.am.getFont().drawWrapped(RG.batch, "This robot is too expensive. Choose another to compete.", -400, -360, 800);
				}
			} else {
				RG.am.getFont().setColor(Color.GREEN);
			}

			RG.am.getFont().drawWrapped(RG.batch, "Total Robot Cost: $" + (selectedRobot.getCost() * 10) + "/$" + (ArenaState.ROBOT_MAX_COST * 10), -400, -380, 800);

			RG.am.getFont().setColor(Color.WHITE);
		}

		RG.am.getFont().draw(RG.batch, "Commands: Esc - Go back, Enter - Accept selection", -400, -420);

		int y = 420;

		int i = 0;

		for (String robot : robots) {
			if (i++ == selectedIndex)
				RG.am.getFont().setColor(Color.GREEN);
			else
				RG.am.getFont().setColor(Color.WHITE);
			RG.am.getFont().drawWrapped(RG.batch, robot.replace(".robot", ""), -560, y, 800);
			RG.am.getFont().setColor(Color.WHITE);
			y -= 20;

		}
	}

	@Override
	public void renderUiPrerender() {
		RG.batch.draw(RG.am.getBigTexture("robotselect"), -600, -450);
		super.renderUiPrerender();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		int row = (screenY - 30) / 20;

		System.out.println(row);

		if (row >= 0 && row < robots.length) {
			selectedIndex = row;
			selectedRobot = RG.rsd.loadRobot(robots[row].replace(".robot", ""));
		}

		return super.touchDown(screenX, screenY, pointer, button);
	}

}
