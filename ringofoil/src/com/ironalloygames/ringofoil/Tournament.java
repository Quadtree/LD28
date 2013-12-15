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

		while (currentRoundRobots.size() < 4) {
			String rname = RG.rsd.list()[MathUtils.random.nextInt(RG.rsd.list().length)].replace(".robot", "");

			currentRoundRobots.add(RG.rsd.loadRobot(rname));
		}
	}

	public void nextMatch() {

		if (currentRoundRobots.size() == 0) {
			currentRoundRobots = nextRoundRobots;
			nextRoundRobots = new ArrayList<Robot>();

			if (currentRoundRobots.size() == 1) {
				RG.currentState = new WinState();
				Gdx.input.setInputProcessor(RG.currentState);
				return;
			}
		}

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

	public void recordResult(Robot winner, Robot loser) {
		System.out.println("Result, " + winner + " won " + loser + " lost");

		if (loser == playerRobot) {
			RG.currentState = new LoseState();
			Gdx.input.setInputProcessor(RG.currentState);
			return;
		}

		currentRoundRobots.remove(loser);
		nextRoundRobots.add(winner);
		currentRoundRobots.remove(winner);

		nextMatch();
	}
}
