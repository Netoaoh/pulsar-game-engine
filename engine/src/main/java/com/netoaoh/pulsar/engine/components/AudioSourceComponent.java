/**
 * Project: PulsarGameEngine
 * File: AudioSourceComponent.java
 * Author: Paulo Maria Neto
 * Created: 30/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 Design Coding. All Rights Reserved.
 */
package com.netoaoh.pulsar.engine.components;

import com.netoaoh.pulsar.engine.audio.AudioEngine;
import com.netoaoh.pulsar.engine.audio.AudioSource;
import com.netoaoh.pulsar.engine.core.IEngine;
import com.netoaoh.pulsar.engine.core.ResourceManager;
import com.netoaoh.pulsar.engine.math.Vector3f;

public class AudioSourceComponent extends Component {

	private AudioSource source = null;
	private boolean sound3D = false;
	private Vector3f sourcePos = new Vector3f(0,0,0);

	public AudioSourceComponent(String filename){
		super();
		this.name = getClass().getSimpleName().toString();
		source = new AudioSource(ResourceManager.loadAudio(filename));
	}

	public void update(){
		super.update();


		if(sound3D)
			sourcePos.set(gameObject.transform.getPos());
		else
			sourcePos.set(IEngine.getAudioEngine().getListenerPos());

		source.setPosition(sourcePos);
	}

	public AudioSource getSource() {
		return source;
	}

	public void setSource(AudioSource source) {
		this.source = source;
	}

	public boolean isSound3D() {
		return sound3D;
	}

	public void setSound3D(boolean sound3D) {
		this.sound3D = sound3D;
	}
}
