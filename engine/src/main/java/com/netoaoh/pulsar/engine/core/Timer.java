/**
 * Project: PulsarGameEngine
 * Filename: Timer.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core;

public class Timer {

	private double startTime;
	private double pausedTime;
	private double currentTime;
	private double currentPausedTime;

	private boolean started;
	private boolean paused;

	public Timer(){
		startTime = 0;
		pausedTime = 0;
		currentTime = 0;
		currentPausedTime = 0;
		started = false;
		paused = false;
	}

	public void start(){
		if(started || paused)
			return;

		startTime = Time.getTime();
		started = true;
	}

	public void stop(){
		if(!started)
			return;

		if(paused)
			unPause();

		currentTime = (Time.getTime() - startTime)  - pausedTime;
		started = false;
	}

	public void restart(){
		startTime = Time.getTime();
		pausedTime = 0;
		currentPausedTime = 0;
		started = false;
		paused = false;
	}

	public void pause(){
		if(!started || paused)
			return;

		currentPausedTime = Time.getTime();
		paused = true;
	}

	public void unPause(){
		if(!started || !paused)
			return;

		pausedTime = Time.getTime() - currentPausedTime;
		paused = false;
	}

	public double getPausedTime(){
		if(!paused)
			return pausedTime;
		else
			return Time.getTime() - currentPausedTime;
	}

	public double getCurrentTime(){
		if(!started)
			return currentTime;
		else
			return (Time.getTime() - startTime) - pausedTime;
	}
}