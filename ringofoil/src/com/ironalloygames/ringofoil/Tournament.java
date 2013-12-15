package com.ironalloygames.ringofoil;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class Tournament {

	List<Robot> currentRoundRobots = new ArrayList<Robot>();

	List<Robot> nextRoundRobots = new ArrayList<Robot>();
	Robot playerRobot;

	public Tournament(Robot playerRobot) {
		this.playerRobot = playerRobot;
		currentRoundRobots.add(playerRobot);

		while (currentRoundRobots.size() < 16) {
			String rname = RG.rsd.list()[MathUtils.random.nextInt(RG.rsd.list().length)].replace(".robot", "");

			currentRoundRobots.add(RG.rsd.loadRobot(rname));
		}
	}

	public void nextMatch() {
		ArenaState as = new ArenaState();
		RG.currentState = as;
		Gdx.input.setInputProcessor(RG.currentState);

		if (currentRoundRobots.get(0) == playerRobot)
			as.controllers.add(new PlayerRobotController());
		else
			as.controllers.add(new AiRobotController());

		if (currentRoundRobots.get(1) == playerRobot)
			as.controllers.add(new PlayerRobotController());
		else
			as.controllers.add(new AiRobotController());

		as.setRobots(currentRoundRobots.get(0), currentRoundRobots.get(1));

	}
}
