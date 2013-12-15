package com.ironalloygames.ringofoil;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Arm;
import com.ironalloygames.ringofoil.component.Armor;
import com.ironalloygames.ringofoil.component.Attachment;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.component.Blade;
import com.ironalloygames.ringofoil.component.CPU;
import com.ironalloygames.ringofoil.component.Cannon;
import com.ironalloygames.ringofoil.component.Component;
import com.ironalloygames.ringofoil.component.LargeMace;
import com.ironalloygames.ringofoil.component.Piston;
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

	public Robot robot;

	Component selectedComponent = null;

	public EditorState() {
		robot = new Robot();

		CPU cpu = new CPU();

		robot.setRootComponent(cpu);

		palette.add(new PaletteItem(new Structure(), "Structure: Basic large structural component, used to connect things together."));
		palette.add(new PaletteItem(new Tracks(), "Wheels: Provides your robot with some mobility."));
		palette.add(new PaletteItem(new Arm(), "Arm: Motorized arm that can swing things around."));
		palette.add(new PaletteItem(new LargeMace(), "Large Mace: Huge mace. Good for smashing heavy armor."));
		palette.add(new PaletteItem(new Piston(), "Piston: Powerful extendible piston. Good for ramming things."));
		palette.add(new PaletteItem(new Armor(), "Armor: Very tough, especially against blades and bullets."));
		palette.add(new PaletteItem(new Cannon(), "Cannon: Extremely high damage, but very limited ammo."));
		palette.add(new PaletteItem(new Blade(), "Blade: Sharp cutting edge, deals great damage versus unarmored."));
	}

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Keys.ESCAPE) {
			RG.currentState = new MainMenuState();
			Gdx.input.setInputProcessor(RG.currentState);
		}

		if (selectedComponent != null && keycode >= Keys.NUM_1 && keycode <= Keys.NUM_9) {
			selectedComponent.setCommandKey(keycode);
		}

		if (selectedComponent != null && selectedComponent.getParent() != null && keycode == Keys.X) {
			System.out.println("Deleting " + selectedComponent);
			selectedComponent.getParent().getParent().getChildren().remove(selectedComponent.getParent());
			selectedComponent = null;
		}

		if (keycode == Keys.S) {
			Gdx.input.getPlaceholderTextInput(new TextInputListener() {

				@Override
				public void canceled() {
				}

				@Override
				public void input(String text) {
					RG.rsd.saveRobot(robot, text);
				}
			}, "Enter a Name to Save", "Name");
		}

		if (keycode == Keys.L) {
			RG.currentState = new RobotSelectState(this);
			Gdx.input.setInputProcessor(RG.currentState);
		}

		return super.keyDown(keycode);
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
			palette.get(i).comp.render(new Vector2(-2.6f, 2 - i * .4f), 0, false);
		}

		if (selectedComponent != null) {
			RG.batch.draw(RG.am.get("selected"), selectedComponent.getRelativePosition().x - .4f, selectedComponent.getRelativePosition().y - .4f, .8f, .8f);
		}
	}

	@Override
	public void renderText() {
		super.renderText();

	}

	@Override
	public void renderUi() {
		super.renderUi();
		RG.am.getFont().setColor(Color.WHITE);

		if (selectedComponent != null) {

			String keyChar = "[None]";

			if (selectedComponent.getCommandKey() != 0) {
				keyChar = "" + (char) ('1' + selectedComponent.getCommandKey() - Keys.NUM_1);
			}

			RG.am.getFont().drawWrapped(RG.batch,
					"Component selected. Action key is set to " + keyChar + ". Change its action command by pressing a number key." + (selectedComponent instanceof CPU ? "" : " Press X to delete it."), -240, 420, 800);
		} else if (paletteItemSelected >= 0) {
			RG.am.getFont().drawWrapped(RG.batch,
					"Selected: " + palette.get(paletteItemSelected).desc + "\nCost: $" + palette.get(paletteItemSelected).comp.getCost() * 10 + "\nClick on a green spot to place this item, or right click to deselect.",
					-240, 420, 800);
		} else {
			RG.am.getFont().drawWrapped(RG.batch, "Left click an item in the palette to select it. Click a part of the robot to select it.", -240, 420, 800);
		}

		if (robot.getCost() > ArenaState.ROBOT_MAX_COST) {
			RG.am.getFont().setColor(Color.RED);
		} else {
			RG.am.getFont().setColor(Color.GREEN);
		}

		RG.am.getFont().drawWrapped(RG.batch, "Total Robot Cost: $" + (robot.getCost() * 10) + "/$" + (ArenaState.ROBOT_MAX_COST * 10), -400, -380, 800);

		RG.am.getFont().setColor(Color.WHITE);

		RG.am.getFont().drawWrapped(RG.batch, "Commands: Esc - Return to Main Menu, S - Save, L - Load", -400, -420, 800);
	}

	@Override
	public void renderUiPrerender() {
		RG.batch.draw(RG.am.getBigTexture("robotselect"), -600, -450);
		super.renderUiPrerender();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if (button == Buttons.LEFT) {
			if (mouseWorldPosition.x > -2.9f && mouseWorldPosition.x < -2.3f) {
				int paletteItem = (int) (-(5f / 3f) * (.6f / .4f) * (mouseWorldPosition.y - 0.3f - 2));

				System.out.println(paletteItem);

				if (paletteItem >= 0 && paletteItem < palette.size()) {
					paletteItemSelected = paletteItem;
					selectedComponent = null;
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
							break;
						case 1:
							comp = new Tracks();
							break;
						case 2:
							comp = new Arm();
							break;
						case 3:
							comp = new LargeMace();
							break;
						case 4:
							comp = new Piston();
							break;
						case 5:
							comp = new Armor();
							break;
						case 6:
							comp = new Cannon();
							break;
						case 7:
							comp = new Blade();
							break;
						}

						if (att.getPoint() == AttachmentPoint.LEFT && !comp.isAttachmentPointValid(AttachmentPoint.RIGHT))
							return true;
						if (att.getPoint() == AttachmentPoint.RIGHT && !comp.isAttachmentPointValid(AttachmentPoint.LEFT))
							return true;
						if (att.getPoint() == AttachmentPoint.TOP && !comp.isAttachmentPointValid(AttachmentPoint.BOTTOM))
							return true;
						if (att.getPoint() == AttachmentPoint.BOTTOM && !comp.isAttachmentPointValid(AttachmentPoint.TOP))
							return true;
						if (att.getPoint() == AttachmentPoint.ARM && !comp.isAttachmentPointValid(AttachmentPoint.LEFT))
							return true;

						Attachment at2 = new Attachment(comp, att.getParent(), att.getPoint());

						Vector2 nmax = at2.getChildRelativePosition().cpy().add(comp.getBoundingBox().scl(.5f, .5f)).add(att.getParent().getRelativePosition());
						Vector2 nmin = at2.getChildRelativePosition().cpy().add(comp.getBoundingBox().scl(-.5f, -.5f)).add(att.getParent().getRelativePosition());

						AABB nBox = new AABB(nmax, nmin);

						boolean intersection = false;

						for (Component tc : robot.getComponents()) {
							if (tc == at2.getParent())
								continue;

							Vector2 min = tc.getRelativePosition().cpy().add(tc.getBoundingBox().scl(-.51f, -.51f));
							Vector2 max = tc.getRelativePosition().cpy().add(tc.getBoundingBox().scl(.51f, .51f));

							AABB box = new AABB(max, min);

							if (nBox.intersects(box)) {
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

			for (Component tc : robot.getComponents()) {
				Vector2 min = tc.getRelativePosition().cpy().add(tc.getBoundingBox().scl(-.51f, -.51f));
				Vector2 max = tc.getRelativePosition().cpy().add(tc.getBoundingBox().scl(.51f, .51f));

				AABB box = new AABB(max, min);

				if (box.contains(mouseWorldPosition)) {
					selectedComponent = tc;
					paletteItemSelected = -1;
					return true;
				}
			}

		} else if (button == Buttons.RIGHT) {
			paletteItemSelected = -1;
			selectedComponent = null;
		}

		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

}
