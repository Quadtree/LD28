package com.ironalloygames.ringofoil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DiskRobotStorageDriver extends RobotStorageDriver {

	String baseDir;

	public DiskRobotStorageDriver() {
		baseDir = "../ringofoil-android/assets/robots";
	}

	@Override
	protected String[] list() {
		File dir = new File(baseDir);

		return dir.list();
	}

	@Override
	public Robot loadRobot(String name) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(baseDir + "/" + name + ".robot"));

			Robot r = (Robot) ois.readObject();
			ois.close();

			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected String read(String name) {
		return null;
	}

	@Override
	public void saveRobot(Robot robot, String name) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(baseDir + "/" + name + ".robot"));

			oos.writeObject(robot);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void write(String name, String data) {

	}

}
