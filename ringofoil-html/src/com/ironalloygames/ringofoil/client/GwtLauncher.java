package com.ironalloygames.ringofoil.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.google.gwt.storage.client.Storage;
import com.ironalloygames.ringofoil.RG;

public class GwtLauncher extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener() {
		Storage.getLocalStorageIfSupported();

		return new RG();
	}

	@Override
	public GwtApplicationConfiguration getConfig() {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(800, 600);
		return cfg;
	}
}