package com.ironalloygames.ringofoil.entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.ironalloygames.ringofoil.ArenaState;
import com.ironalloygames.ringofoil.RG;
import com.ironalloygames.ringofoil.component.Attachment;
import com.ironalloygames.ringofoil.component.Attachment.AttachmentPoint;
import com.ironalloygames.ringofoil.component.Component;

public abstract class ComponentEntity extends Entity {
	List<ComponentEntity> children = new ArrayList<ComponentEntity>();

	int commandKey;

	Component component;

	boolean flipped;

	float liquidLeft = 1;

	public boolean loose = false;

	Joint parentConnector;

	boolean punctured = false;

	Vector2 relativePosition;

	public ComponentEntity(Component component, Vector2 robotCenter, boolean flipped) {
		this.component = component;
		this.relativePosition = component.getRelativePosition();
		this.flipped = flipped;

		System.out.println(flipped);

		if (flipped) {
			this.relativePosition.x *= -1;
		}

		createBody(robotCenter);

		createFixture();

		for (Attachment att : component.getChildren()) {
			ComponentEntity child = att.getChild().createEntity(robotCenter, flipped);

			createJointToChild(att, child);
		}
	}

	public boolean canBePunctured() {
		return true;
	}

	public void commandKeyDown() {
	}

	public void commandKeyUp() {
	}

	protected void createBody(Vector2 robotCenter) {
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.position.x = relativePosition.x + robotCenter.x;
		bd.position.y = relativePosition.y + robotCenter.y;

		System.out.println("Creating body at " + bd.position);

		body = ((ArenaState) RG.currentState).world.createBody(bd);
		body.setUserData(this);
	}

	protected void createFixture() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(component.getBoundingBox().x / 2, component.getBoundingBox().y / 2);

		body.createFixture(shape, getDensity()).setFilterData(getFilter());
	}

	public void createJointToChild(Attachment att, ComponentEntity child) {
		WeldJointDef jd = new WeldJointDef();
		jd.bodyA = getBodyForChildConnection(att.getPoint());
		jd.bodyB = child.body;

		Vector2 delta = jd.bodyB.getPosition().cpy().sub(jd.bodyA.getPosition());

		// Vector2 centerPoint =
		// jd.bodyA.getPosition().cpy().add(delta.cpy().scl(.5f));

		Vector2 localAnchorA = delta.cpy().scl(.5f);
		Vector2 localAnchorB = delta.cpy().scl(-.5f);

		jd.localAnchorA.x = localAnchorA.x;
		jd.localAnchorA.y = localAnchorA.y;
		jd.localAnchorB.x = localAnchorB.x;
		jd.localAnchorB.y = localAnchorB.y;

		child.setParentConnector(((ArenaState) RG.currentState).world.createJoint(jd));

		children.add(child);
	}

	protected Body getBodyForChildConnection(AttachmentPoint ap) {
		return body;
	}

	public int getCommandKey() {
		return component.getCommandKey();
	}

	protected float getDensity() {
		return 1;
	}

	protected Filter getFilter() {
		Filter fd = new Filter();
		fd.categoryBits = 1;
		fd.maskBits = 1;
		fd.groupIndex = ((ArenaState) RG.currentState).currentGroup;

		return fd;
	}

	@Override
	public float getHeavyDamageMultiplier() {
		return component.getHeavyDamageMultiplier();
	}

	@Override
	public float getHeavyDamageReduction() {
		return component.getHeavyDamageReduction();
	}

	@Override
	public float getHeavyDamageResistance() {
		return component.getHeavyDamageResistance();
	}

	@Override
	public float getHp() {
		return component.getHp();
	}

	@Override
	public float getLightDamageMultiplier() {
		return component.getLightDamageMultiplier();
	}

	@Override
	public float getLightDamageReduction() {
		return component.getLightDamageReduction();
	}

	@Override
	public float getLightDamageResistance() {
		return component.getLightDamageResistance();
	}

	@Override
	public float getMaxHp() {
		return component.getMaxHp();
	}

	public Joint getParentConnector() {
		return parentConnector;
	}

	@Override
	public void render() {
		super.render();

		component.render(body.getPosition(), body.getAngle(), flipped);

		renderConnector();
	}

	protected void renderConnector() {
		if (component.getParent() != null) {
			Vector2 pt = component.getParent().getCenterPoint().scl(-1);

			if (flipped)
				pt.x *= -1;

			pt = body.getWorldPoint(pt);

			component.renderConnector(pt, body.getAngle());

		}
	}

	@Override
	public void setHp(float hp) {
		component.setHp(hp);
	}

	public void setParentConnector(Joint parentConnector) {
		this.parentConnector = parentConnector;
	}

	protected void setToNewGroup(short group) {
		loose = true;
		Filter f = new Filter();
		f.categoryBits = 2;
		f.maskBits = 2;
		f.groupIndex = group;

		body.getFixtureList().get(0).setFilterData(f);

		for (ComponentEntity ce : children) {
			if (ce.parentConnector != null) {
				ce.setToNewGroup(group);
			}
		}
	}

	@Override
	public void takeDamage(float lightDamage, float heavyDamage) {
		float initialHp = getHp();

		super.takeDamage(lightDamage, heavyDamage);

		if (initialHp - getHp() > 0.1f && MathUtils.randomBoolean(.35f) && canBePunctured()) {
			punctured = true;
		}

		if (getHp() < 0) {
			if (component.getParent() != null) {
				component.getParent().getParent().getChildren().remove(component.getParent());
				component.setParent(null);
			} else if (component.getChildren().size() > 0) {
				Attachment child = component.getChildren().get(MathUtils.random.nextInt(component.getChildren().size()));

				child.getChild().setParent(null);
				component.getChildren().remove(child);
			}
		}
	}

	@Override
	public void update() {
		super.update();

		if (punctured && MathUtils.randomBoolean(liquidLeft)) {
			new SparkEntity(getPosition(), MathUtils.random(1.4f, 1.6f), MathUtils.random(4, 7) * liquidLeft, body.getFixtureList().get(0).getFilterData(), "oil");
			liquidLeft -= 0.005f;
		}

		if (component.getParent() == null && parentConnector != null) {
			((ArenaState) RG.currentState).world.destroyJoint(parentConnector);
			parentConnector = null;

			short newGroup = --((ArenaState) RG.currentState).currentGroup;

			setToNewGroup(newGroup);
		}
	}
}
