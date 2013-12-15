package com.ironalloygames.ringofoil;

import com.badlogic.gdx.Gdx;

public class WinState extends GameState {

	public WinState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean keyDown(int keycode) {
		RG.currentState = new MainMenuState();
		Gdx.input.setInputProcessor(RG.currentState);
		return super.keyDown(keycode);
	}

	@Override
	public void renderUi() {
		super.renderUi();

		RG.am.getFont().drawWrapped(RG.batch, "YOU WIN!", 200, -425, 800);
	}
}
