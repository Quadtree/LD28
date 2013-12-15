package com.ironalloygames.ringofoil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class DiskRobotStorageDriver extends RobotStorageDriver {

	String baseDir;

	public DiskRobotStorageDriver() {
		baseDir = "../ringofoil-android/assets/robots";
	}

	@Override
	protected String[] list() {
		File dir = new File(baseDir);

		ArrayList<String> names = new ArrayList<String>();

		for (File f : dir.listFiles()) {
			if (f.isFile()) {
				names.add(f.getName());
			}
		}

		return (String[]) names.toArray();
	}

	@Override
	public Robot loadRobot(String name) {
		try {
			FileReader fos = new FileReader(baseDir + "/" + name + ".robot.txt");

			fos.read(cb);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveRobot(Robot robot, String name) {
		try {
			FileWriter fos = new FileWriter(baseDir + "/" + name + ".robot.txt");
			fos.write(data);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
