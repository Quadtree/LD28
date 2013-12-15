package com.ironalloygames.ringofoil;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ironalloygames.ringofoil.entity.Entity;

public class ArenaState extends GameState implements ContactListener {

	ArrayList<RobotController> controllers = new ArrayList<RobotController>();

	public short currentGroup = 0;

	ArrayList<Entity> entities = new ArrayList<Entity>();

	ArrayList<Entity> entityAddQueue = new ArrayList<Entity>();

	ArrayList<Robot> robots = new ArrayList<Robot>();

	public World world;

	public ArenaState() {
		world = new World(new Vector2(0, -9.8f), false);
		world.setContactListener(this);

		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		bd.position.x = 0;
		bd.position.y = 0;

		Body groundBody = world.createBody(bd);

		Filter f = new Filter();
		f.categoryBits = 3;
		f.maskBits = 3;
		f.groupIndex = 0;

		PolygonShape groundShape = new PolygonShape();
		groundShape.setAsBox(40, 1, new Vector2(0, -3f), 0);

		groundBody.createFixture(groundShape, 0).setFilterData(f);

		PolygonShape leftWallShape = new PolygonShape();
		leftWallShape.setAsBox(1, 40, new Vector2(-4f, 0), 0);

		groundBody.createFixture(leftWallShape, 0);

		PolygonShape rightWallShape = new PolygonShape();
		rightWallShape.setAsBox(1, 40, new Vector2(4f, 0), 0);

		groundBody.createFixture(rightWallShape, 0);

		controllers.add(new PlayerRobotController());
		controllers.add(new AiRobotController());
	}

	public void addEntity(Entity e) {
		entityAddQueue.add(e);
	}

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

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
	public void postSolve(Contact contact, ContactImpulse impulse) {
		if (contact.getFixtureA().getBody().getUserData() instanceof Entity && contact.getFixtureB().getBody().getUserData() instanceof Entity) {
			Entity e1 = (Entity) contact.getFixtureA().getBody().getUserData();
			Entity e2 = (Entity) contact.getFixtureB().getBody().getUserData();

			float totalImpulse = impulse.getNormalImpulses()[0];

			// for (float f : impulse.getNormalImpulses()) {
			// totalImpulse += f;
			// }

			e1.impact(totalImpulse, e2);
			e2.impact(totalImpulse, e1);
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		super.render();

		RG.batch.draw(RG.am.getBigTexture("arena"), -400 / 128f, -300 / 128f, 800 / 128f, 600 / 128f);

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
		currentGroup = -1;
		robot0.generate(new Vector2(-2, 0), false);

		int endPos = entityAddQueue.size();

		for (int i = 0; i < endPos; i++) {
			controllers.get(0).addEntity(entityAddQueue.get(i));
		}

		currentGroup = -2;
		robot1.generate(new Vector2(2, 0), true);

		for (int i = endPos; i < entityAddQueue.size(); i++) {
			controllers.get(1).addEntity(entityAddQueue.get(i));
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

		world.step(0.016f, 10, 10);

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}
}
