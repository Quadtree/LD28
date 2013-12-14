package com.ironalloygames.ringofoil.entity;

import com.ironalloygames.ringofoil.component.Tracks;

public class TracksEntity extends ComponentEntity {
	Tracks tracks;

	public TracksEntity(SuperEntity superEntity, Tracks tracks) {
		super(superEntity, tracks);
		this.tracks = tracks;
	}
}
