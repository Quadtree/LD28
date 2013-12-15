package com.ironalloygames.ringofoil;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Attachment;
import com.ironalloygames.ringofoil.component.CPU;
import com.ironalloygames.ringofoil.component.Component;
import com.ironalloygames.ringofoil.component.Structure;
import com.ironalloygames.ringofoil.component.Tracks;

public class EditorState extends GameState {

	public ArrayList<Attachment> attachmentPoints;

	ArrayList<Component> palette = new ArrayList<Component>();

	Robot robot;

	public EditorState() {
		robot = new Robot();

		CPU cpu = new CPU();

		robot.setRootComponent(cpu);

		palette.add(new Structure());
		palette.add(new Tracks());
	}

	@Override
	public void render() {
		super.render();

		attachmentPoints = new ArrayList<Attachment>();

		for (Component c : robot.getComponents()) {
			c.render(c.getRelativePosition(), 0, false);
			c.renderAttachmentPoints();
			c.renderConnector();
		}

		for (int i = 0; i < palette.size(); ++i) {
			palette.get(i).render(new Vector2(-2.6f, 2 - i * .6f), 0, false);
		}
	}

	@Override
	public void renderText() {
		super.renderText();

	}

	@Override
	public void renderUi() {
		super.renderUi();

		RG.am.getFont().draw(RG.batch, "test string...", -100, -300);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

}
