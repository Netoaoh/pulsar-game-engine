/**
 * Project: PulsarGameEngine
 * File: AudioSource.java
 * Author: Paulo Maria Neto
 * Created: 30/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 Design Coding. All Rights Reserved.
 */
package com.netoaoh.pulsar.engine.audio;

import com.netoaoh.pulsar.engine.core.resourcemanagement.AudioResource;
import com.netoaoh.pulsar.engine.math.Vector3f;
import org.lwjgl.openal.AL10;

public class AudioSource {

	private AudioResource resource;

	private boolean looping;
	Vector3f position = new Vector3f(0,0,0);

	public AudioSource(AudioResource resource) {
		this.resource = resource;
	}

	public void play() {
		stop();
		AL10.alSourcei(resource.getSourceID(), AL10.AL_BUFFER, resource.getBufferID());
		AL10.alSourcePlay(resource.getSourceID());
	}

	public void pause(){
		AL10.alSourcePause(resource.getSourceID());
	}

	public void resume(){
		AL10.alSourcePlay(resource.getSourceID());
	}

	public void stop(){
		AL10.alSourceStop(resource.getSourceID());
	}

	public void destroy(){
		stop();
		AL10.alDeleteSources(resource.getSourceID());
		AL10.alDeleteBuffers(resource.getBufferID());
	}

	public boolean isPlaying(){
		return AL10.alGetSourcei(resource.getSourceID(), AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
	}

	public boolean isLooping(){
		return AL10.alGetSourcei(resource.getSourceID(), AL10.AL_LOOPING) == AL10.AL_TRUE;
	}

	public void setLooping(boolean loop){
		AL10.alSourcei(resource.getSourceID(), AL10.AL_LOOPING, loop ? AL10.AL_TRUE : AL10.AL_FALSE);
	}

	public void setVelocity(Vector3f velocity){
		AL10.alSource3f(resource.getSourceID(), AL10.AL_VELOCITY, velocity.getX(), velocity.getY(), velocity.getZ());
	}

	public void setVolume(float volume){
		AL10.alSourcef(resource.getSourceID(), AL10.AL_GAIN, volume);
	}

	public void setPitch(float pitch){
		AL10.alSourcef(resource.getSourceID(), AL10.AL_PITCH, pitch);
	}

	public void setPosition(Vector3f pos){
		AL10.alSource3f(resource.getSourceID(), AL10.AL_POSITION, pos.getX(), pos.getY(), pos.getZ());
	}
}
