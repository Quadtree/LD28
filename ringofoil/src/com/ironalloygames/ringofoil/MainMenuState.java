package com.ironalloygames.ringofoil;

import com.badlogic.gdx.Gdx;

public class MainMenuState extends GameState {

	public MainMenuState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
	}

	@Override
	public void renderUi() {
		super.renderUi();

		RG.batch.draw(RG.am.getBigTexture("mainmenu"), -600, -450);

		RG.am.getFont().drawWrapped(RG.batch, "Made by Quadtree for Ludum Dare 28", 200, -425, 800);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if (screenX >= 360 && screenX < 801 && screenY > 370 && screenY < 483) {
			RG.currentState = new InfoState();
			Gdx.input.setInputProcessor(RG.currentState);
		}
		if (screenX >= 360 && screenX < 801 && screenY > 501 && screenY < 616) {
			RG.currentState = new EditorState();
			Gdx.input.setInputProcessor(RG.currentState);
		}
		if (screenX >= 360 && screenX < 801 && screenY > 633 && screenY < 750) {
			Gdx.app.exit();
		}

		return super.touchDown(screenX, screenY, pointer, button);
	}

}
