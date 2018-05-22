/**
 * Project: PulsarGameEngine
 * Filename: AudioEngine.java
 * Author: Paulo Maria Neto
 * Created: 30/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 Design Coding. All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.audio;

import com.netoaoh.pulsar.engine.math.Vector3f;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;

import java.util.ArrayList;
import java.util.List;

public class AudioEngine extends IAudioEngine {

	private Vector3f listenerPos;

	public AudioEngine() {
		super();
	}

	public void initialize()
	{
		try {
			AL.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	public void shutdown()
	{
		AL.destroy();
	}

	public Vector3f getListenerPos() {
		return listenerPos;
	}

	public void setListenerPos(Vector3f listenerPos) {
		this.listenerPos = listenerPos;
	}
}
