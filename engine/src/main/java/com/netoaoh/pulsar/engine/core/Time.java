/**
 * Project: PulsarGameEngine
 * Filename: Time.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core;

public class Time {

	private static final long SECOND = 1000000000L;
	public static float deltaTime;
	public static float timeScale = 1.0f;

	public static double getTime(){
		return (double)System.nanoTime()/(double)SECOND;
	}

	public static double getTimeMilliseconds(){
		return (double)System.currentTimeMillis();
	}
}