package com.ironalloygames.ringofoil.entity;

import com.ironalloygames.ringofoil.component.Tracks;

public class TracksEntity extends ComponentEntity {
	Tracks tracks;

	public TracksEntity(Tracks tracks) {
		super(tracks);
		this.tracks = tracks;
	}
}
