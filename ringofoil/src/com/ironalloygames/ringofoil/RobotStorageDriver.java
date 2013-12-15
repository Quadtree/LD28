package com.ironalloygames.ringofoil;

import java.util.ArrayList;
import java.util.List;

import com.ironalloygames.ringofoil.component.Arm;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.component.CPU;
import com.ironalloygames.ringofoil.component.Component;
import com.ironalloygames.ringofoil.component.LargeMace;
import com.ironalloygames.ringofoil.component.Piston;
import com.ironalloygames.ringofoil.component.Structure;
import com.ironalloygames.ringofoil.component.Tracks;

public abstract class RobotStorageDriver {

	public RobotStorageDriver() {

	}

	protected Robot deserializeRobot(String serializedRobot) {

		Robot robot = new Robot();

		ArrayList<Component> components = new ArrayList<Component>();

		for (String line : serializedRobot.split("\n")) {
			String[] lineParts = line.split("\t");

			Component c = null;

			if (lineParts[0].equals("Arm"))
				c = new Arm();
			if (lineParts[0].equals("CPU"))
				c = new CPU();
			if (lineParts[0].equals("LargeMace"))
				c = new LargeMace();
			if (lineParts[0].equals("Piston"))
				c = new Piston();
			if (lineParts[0].equals("Structure"))
				c = new Structure();
			if (lineParts[0].equals("Tracks"))
				c = new Tracks();

			c.setCommandKey(Integer.parseInt(lineParts[1]));

			if (components.size() == 0) {
				robot.setRootComponent(c);
			} else {
				components.get(Integer.parseInt(lineParts[2])).addChildComponent(c, AttachmentPoint.valueOf(lineParts[3]));
			}

			components.add(c);
		}

		return robot;
	}

	protected abstract String[] list();

	public Robot loadRobot(String name) {
		String data = read(name);
		return deserializeRobot(data);
	}

	protected abstract String read(String name);

	public void saveRobot(Robot robot, String name) {
		String data = serializeRobot(robot);
		write(name, data);
	}

	protected String serializeRobot(Robot robot) {
		StringBuilder sb = new StringBuilder();

		List<Component> rcom = robot.getComponents();

		for (Component c : rcom) {

			if (c instanceof Arm)
				sb.append("Arm");
			if (c instanceof CPU)
				sb.append("CPU");
			if (c instanceof LargeMace)
				sb.append("LargeMace");
			if (c instanceof Piston)
				sb.append("Piston");
			if (c instanceof Structure)
				sb.append("Structure");
			if (c instanceof Tracks)
				sb.append("Tracks");

			sb.append("\t" + c.getCommandKey());

			if (c.getParent() != null) {

				int parentId = -1;

				for (int i = 0; i < rcom.size(); i++) {
					if (rcom.get(i) == c.getParent().getParent()) {
						parentId = i;
					}
				}

				sb.append("\t" + parentId + "\t" + c.getParent().getPoint());
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	protected abstract void write(String name, String data);
}
