/**
 * Project: PulsarGameEngine
 * File: AudioResource.java
 * Author: Paulo Maria Neto
 * Created: 30/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 Design Coding. All Rights Reserved.
 */
package com.netoaoh.pulsar.engine.core.resourcemanagement;

import com.netoaoh.pulsar.engine.audio.WaveData;
import org.lwjgl.openal.AL10;

public class AudioResource {

	private int sourceID;
	private int bufferID;

	public AudioResource(int buffer, int source){
		bufferID = buffer;
		sourceID = source;
	}

	public int getSourceID() {
		return sourceID;
	}

	public int getBufferID() {
		return bufferID;
	}
}
