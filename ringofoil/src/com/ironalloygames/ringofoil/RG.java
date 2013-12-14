package com.ironalloygames.ringofoil;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.component.CPU;
import com.ironalloygames.ringofoil.component.Structure;
import com.ironalloygames.ringofoil.component.Tracks;

public class RG implements ApplicationListener {
	public static AssetManager am;
	public static SpriteBatch batch;
	public static GameState currentState;

	public static OrthographicCamera gameCamera;

	public static OrthographicCamera uiCamera;

	long ticks = 0;

	@Override
	public void create() {
		uiCamera = new OrthographicCamera(800, 600);
		gameCamera = new OrthographicCamera(800.f / 128, 600.f / 128);
		batch = new SpriteBatch();

		currentState = new ArenaState();
		((ArenaState) currentState).setRobots(createFakeRobot(),
				createFakeRobot());
		// currentState = new EditorState();

		am = new AssetManager();

		ticks = System.currentTimeMillis();
	}

	Robot createFakeRobot() {
		Robot robot = new Robot();

		CPU cpu = new CPU();
		Structure structure = new Structure();
		Tracks tracks = new Tracks();

		robot.setRootComponent(cpu);
		cpu.addChildComponent(structure, AttachmentPoint.BOTTOM);
		structure.addChildComponent(tracks, AttachmentPoint.BOTTOM);

		return robot;
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(gameCamera.combined);
		batch.begin();
		currentState.render();
		batch.end();

		batch.setProjectionMatrix(uiCamera.combined);
		batch.begin();
		currentState.renderUi();
		batch.end();

		if (ticks < System.currentTimeMillis()) {
			ticks += 16;
			currentState.update();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}
}
