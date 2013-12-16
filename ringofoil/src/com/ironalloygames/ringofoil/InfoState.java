package com.ironalloygames.ringofoil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InfoState extends GameState {

	public InfoState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ENTER) {
			RG.currentState = new RobotSelectState(null);
			Gdx.input.setInputProcessor(RG.currentState);
		}

		return super.keyDown(keycode);
	}

	@Override
	public void renderUiPrerender() {
		super.renderUiPrerender();

		RG.batch.draw(RG.am.getBigTexture("info"), -600, -450);

		RG.am.getFont().drawWrapped(RG.batch, "Welcome to the Ring of Oil Tournament!", -200, 380, 800);

		RG.am.getFont()
				.drawWrapped(
						RG.batch,
						"The tournament has eight competitors, and uses a one-round, single-elimination format. You only get one robot, and there are no repairs, so choose wisely!\n\nIn every match, the goal is to destroy the enemy robot's CPU. If the match becomes very boring, the referees may also award a robot a win on points.",
						-350, 340, 800);

		RG.am.getFont().drawWrapped(RG.batch, "Controls:\nA-D/Arrow Keys: Move left/right\n1-9: Activate modules (depends on control setup in editor)\nMouse: Control arms (if you have them)\nEsc: Quit to main menu",
				-350, 150, 800);

		RG.am.getFont().drawWrapped(RG.batch, "Press enter to begin", -150, -375, 800);
	}

}
