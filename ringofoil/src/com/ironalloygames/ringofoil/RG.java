package com.ironalloygames.ringofoil;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ironalloygames.ringofoil.component.Arm;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.component.CPU;
import com.ironalloygames.ringofoil.component.LargeMace;
import com.ironalloygames.ringofoil.component.Piston;
import com.ironalloygames.ringofoil.component.Structure;
import com.ironalloygames.ringofoil.component.Tracks;

public class RG implements ApplicationListener {
	public static AssetManager am;
	public static SpriteBatch batch;
	public static GameState currentState;

	public static OrthographicCamera gameCamera;

	public static RobotStorageDriver rsd;

	public static OrthographicCamera textCamera;

	public static Tournament tr;

	public static OrthographicCamera uiCamera;

	ShapeRenderer sr;

	long ticks = 0;

	public RG(RobotStorageDriver rsd) {
		RG.rsd = rsd;
	}

	@Override
	public void create() {
		uiCamera = new OrthographicCamera(1200, 900);
		gameCamera = new OrthographicCamera(800.f / 128, 600.f / 128);
		textCamera = new OrthographicCamera(1200, 900);
		batch = new SpriteBatch();

		// currentState = new ArenaState();
		// ((ArenaState) currentState).setRobots(createFakeRobot(),
		// createFakeRobot());

		// currentState = new EditorState();

		currentState = new MainMenuState();
		Gdx.input.setInputProcessor(currentState);

		am = new AssetManager();

		ticks = System.currentTimeMillis();
	}

	Robot createFakeRobot() {
		Robot robot = new Robot();

		CPU cpu = new CPU();
		Structure structure = new Structure();
		Piston p1 = new Piston();
		Arm a1 = new Arm();
		Structure structure2 = new Structure();
		Tracks tracks = new Tracks();
		Tracks tracks2 = new Tracks();

		LargeMace mace = new LargeMace();

		p1.setCommandKey(Keys.NUM_1);

		robot.setRootComponent(cpu);
		cpu.addChildComponent(structure, AttachmentPoint.BOTTOM);
		structure.addChildComponent(tracks, AttachmentPoint.BOTTOM);
		structure.addChildComponent(structure2, AttachmentPoint.RIGHT);
		structure2.addChildComponent(a1, AttachmentPoint.RIGHT);
		a1.addChildComponent(p1, AttachmentPoint.ARM);
		p1.addChildComponent(mace, AttachmentPoint.RIGHT);
		structure2.addChildComponent(tracks2, AttachmentPoint.BOTTOM);

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

		batch.setProjectionMatrix(textCamera.combined);
		batch.begin();
		currentState.renderText();
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
