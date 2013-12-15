package com.ironalloygames.ringofoil;

import com.badlogic.gdx.math.Vector2;

public class AABB {
	Vector2 max;
	Vector2 min;

	public AABB(Vector2 max, Vector2 min) {
		super();
		this.max = max;
		this.min = min;
	}

	public boolean contains(Vector2 pt) {
		return pt.x >= min.x && pt.x <= max.x && pt.y >= min.y && pt.y <= max.y;
	}

	public boolean intersects(AABB aabb) {
		return contains(new Vector2(aabb.min.x, aabb.min.y)) || contains(new Vector2(aabb.max.x, aabb.min.y)) || contains(new Vector2(aabb.min.x, aabb.max.y)) || contains(new Vector2(aabb.max.x, aabb.max.y))
				|| aabb.contains(new Vector2(min.x, min.y)) || aabb.contains(new Vector2(max.x, min.y)) || aabb.contains(new Vector2(min.x, max.y)) || aabb.contains(new Vector2(max.x, max.y));
	}
}
