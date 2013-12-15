package com.ironalloygames.ringofoil;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Attachment;
import com.ironalloygames.ringofoil.component.CPU;
import com.ironalloygames.ringofoil.component.Component;
import com.ironalloygames.ringofoil.component.Structure;
import com.ironalloygames.ringofoil.component.Tracks;

public class EditorState extends GameState {

	class PaletteItem {
		public Component comp;
		public String desc;

		public PaletteItem(Component comp, String desc) {
			super();
			this.comp = comp;
			this.desc = desc;
		}

	}

	public ArrayList<Attachment> attachmentPoints = new ArrayList<Attachment>();

	ArrayList<PaletteItem> palette = new ArrayList<PaletteItem>();

	int paletteItemSelected = -1;

	Robot robot;

	public EditorState() {
		robot = new Robot();

		CPU cpu = new CPU();

		robot.setRootComponent(cpu);

		palette.add(new PaletteItem(new Structure(), "Structure: Basic large structural component, used to connect things together."));
		palette.add(new PaletteItem(new Tracks(), "Wheels: Provides your robot with some mobility."));
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
			palette.get(i).comp.render(new Vector2(-2.6f, 2 - i * .6f), 0, false);
		}
	}

	@Override
	public void renderText() {
		super.renderText();

	}

	@Override
	public void renderUi() {
		super.renderUi();

		if (paletteItemSelected >= 0) {
			RG.am.getFont().drawWrapped(RG.batch, "Selected: " + palette.get(paletteItemSelected).desc + "\nClick on a green spot to place this item, or right click to deselect.", -400, 420, 800);
		} else {
			RG.am.getFont().drawWrapped(RG.batch, "Left click an item in the palette to select it.", -400, 420, 800);
		}
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if (mouseWorldPosition.x > -2.9f && mouseWorldPosition.x < -2.3f) {
			int paletteItem = (int) (-(5f / 3f) * (mouseWorldPosition.y - 0.3f - 2));

			System.out.println(paletteItem);

			if (paletteItem >= 0 && paletteItem < palette.size()) {
				paletteItemSelected = paletteItem;
				return true;
			}
		}

		if (paletteItemSelected >= 0) {
			for (Attachment att : attachmentPoints) {
				if (att.getCenterPoint().cpy().add(att.getParent().getRelativePosition()).sub(mouseWorldPosition).len2() < 0.05f) {
					Component comp = null;
					switch (paletteItemSelected) {
					case 0:
						comp = new Structure();
					}

					Attachment at2 = new Attachment(comp, att.getParent(), att.getPoint());

					Vector2[] pts = new Vector2[4];
					pts[0] = at2.getChildRelativePosition().cpy().add(comp.getBoundingBox().scl(.5f, .5f)).add(att.getParent().getRelativePosition());
					pts[1] = at2.getChildRelativePosition().cpy().add(comp.getBoundingBox().scl(-.5f, .5f)).add(att.getParent().getRelativePosition());
					pts[2] = at2.getChildRelativePosition().cpy().add(comp.getBoundingBox().scl(.5f, -.5f)).add(att.getParent().getRelativePosition());
					pts[3] = at2.getChildRelativePosition().cpy().add(comp.getBoundingBox().scl(-.5f, -.5f)).add(att.getParent().getRelativePosition());

					System.out.println(pts[0] + " " + pts[1] + " " + pts[2] + " " + pts[3]);

					boolean intersection = false;

					for (Component tc : robot.getComponents()) {
						if (tc == at2.getParent())
							continue;

						Vector2 ll = tc.getRelativePosition().cpy().add(tc.getBoundingBox().scl(-.52f, -.52f));
						Vector2 ur = tc.getRelativePosition().cpy().add(tc.getBoundingBox().scl(.52f, .52f));
						System.out.println(tc + ": " + ll + " " + ur);

						for (Vector2 pt : pts) {

							if (pt.x > ll.x && pt.y > ll.y && pt.x < ur.x && pt.y < ur.y)
								intersection = true;
						}
					}

					if (comp != null && !intersection) {
						att.getParent().addChildComponent(comp, att.getPoint());
						return true;
					}
				}
			}
		}

		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

}
