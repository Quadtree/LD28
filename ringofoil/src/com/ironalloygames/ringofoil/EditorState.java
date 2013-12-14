package com.ironalloygames.ringofoil;

public class EditorState extends GameState {

	@Override
	public void render() {
		RG.batch.draw(RG.am.get("tracks"), 0, 0, .5f, .5f * .625f);
		super.render();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

}
