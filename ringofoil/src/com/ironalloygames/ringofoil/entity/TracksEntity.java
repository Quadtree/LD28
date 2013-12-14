package com.ironalloygames.ringofoil.entity;

import com.badlogic.gdx.math.Vector2;
import com.ironalloygames.ringofoil.component.Tracks;

public class TracksEntity extends ComponentEntity {
	Tracks tracks;

	public TracksEntity(Tracks tracks, Vector2 robotCenter, boolean flipped) {
		super(tracks, robotCenter, flipped);
		this.tracks = tracks;
	}
}
