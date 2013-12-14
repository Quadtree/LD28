package com.ironalloygames.ringofoil;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ironalloygames.ringofoil.entity.Entity;

public class ArenaState extends GameState {

	ArrayList<RobotController> controllers = new ArrayList<RobotController>();

	ArrayList<Entity> entities = new ArrayList<Entity>();

	ArrayList<Entity> entityAddQueue = new ArrayList<Entity>();

	ArrayList<Robot> robots = new ArrayList<Robot>();

	public World world;

	public ArenaState() {
		world = new World(new Vector2(0, -9.8f), false);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		bd.position.x = 0;
		bd.position.y = 0;

		Body groundBody = world.createBody(bd);

		PolygonShape groundShape = new PolygonShape();
		groundShape.setAsBox(40, 1, new Vector2(0, -3f), 0);

		groundBody.createFixture(groundShape, 0);

		PolygonShape leftWallShape = new PolygonShape();
		leftWallShape.setAsBox(1, 40, new Vector2(-3.5f, 0), 0);

		groundBody.createFixture(leftWallShape, 0);

		PolygonShape rightWallShape = new PolygonShape();
		rightWallShape.setAsBox(1, 40, new Vector2(3.5f, 0), 0);

		groundBody.createFixture(rightWallShape, 0);

		controllers.add(new PlayerRobotController());
		controllers.add(new AiRobotController());
	}

	public void addEntity(Entity e) {
		entityAddQueue.add(e);
	}

	@Override
	public boolean keyDown(int keycode) {
		for (RobotController c : controllers) {
			c.keyDown(keycode);
		}

		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {

		for (RobotController c : controllers) {
			c.keyUp(keycode);
		}

		return super.keyUp(keycode);
	}

	@Override
	public void render() {
		super.render();

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render();
		}

		// Box2DDebugRenderer db = new Box2DDebugRenderer();
		// db.render(world, RG.gameCamera.combined);
	}

	@Override
	public void renderUi() {
		// TODO Auto-generated method stub
		super.renderUi();
	}

	public void setRobots(Robot robot0, Robot robot1) {
		robot0.generate(new Vector2(-2, 0), false);

		int endPos = entityAddQueue.size();

		for (int i = 0; i < endPos; i++) {
			controllers.get(0).addEntity(entityAddQueue.get(i));
		}

		robot1.generate(new Vector2(2, 0), true);

		for (int i = endPos; i < entityAddQueue.size(); i++) {
			controllers.get(0).addEntity(entityAddQueue.get(i));
		}
	}

	@Override
	public void update() {
		super.update();

		while (entityAddQueue.size() > 0) {
			entities.add(entityAddQueue.remove(0));
		}

		for (RobotController c : controllers) {
			c.update();
		}

		world.step(0.016f, 2, 2);

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}
}
