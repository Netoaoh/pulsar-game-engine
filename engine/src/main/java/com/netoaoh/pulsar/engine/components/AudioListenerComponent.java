/**
 * Project: PulsarGameEngine
 * File: AudioListenerComponent.java
 * Author: Paulo Maria Neto
 * Created: 30/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 Design Coding. All Rights Reserved.
 */
package com.netoaoh.pulsar.engine.components;

import com.netoaoh.pulsar.engine.audio.AudioEngine;
import com.netoaoh.pulsar.engine.audio.IAudioEngine;
import com.netoaoh.pulsar.engine.core.IEngine;
import com.netoaoh.pulsar.engine.math.Vector3f;
import org.lwjgl.openal.AL10;

public class AudioListenerComponent extends Component {

	private Vector3f listenerPosition;

	public AudioListenerComponent(){
		super();
		this.name = getClass().getSimpleName().toString();
	}

	public void awake(){
		super.awake();
		listenerPosition = gameObject.transform.getPos();
		AL10.alListener3f(AL10.AL_POSITION, listenerPosition.getX(), listenerPosition.getY(), listenerPosition.getZ());
		AL10.alListener3f(AL10.AL_VELOCITY, 0,0,0);
		IEngine.getAudioEngine().setListenerPos(listenerPosition);
	}

	public void update(){
		super.update();
		listenerPosition = gameObject.transform.getPos();
		AL10.alListener3f(AL10.AL_POSITION, listenerPosition.getX(), listenerPosition.getY(), listenerPosition.getZ());
		IEngine.getAudioEngine().setListenerPos(listenerPosition);
	}
}
