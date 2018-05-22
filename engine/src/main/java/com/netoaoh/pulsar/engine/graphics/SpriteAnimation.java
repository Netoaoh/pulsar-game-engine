/**
 * Project: PulsarGameEngine
 * Filename: SpriteAnimation.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics;

import com.netoaoh.pulsar.engine.core.Time;

import java.util.ArrayList;

public class SpriteAnimation {

	public String name;
	public int currentFrame;
	private double startFrame;
	public ArrayList<Integer> frames;
	public ArrayList<Float> framesSteps;

	public SpriteAnimation(String name){
		this.name = name;
		currentFrame = 0;
		startFrame = 0;
		frames = new ArrayList<Integer>();
		framesSteps = new ArrayList<Float>();
	}

	public void update(){
		if((Time.getTime() - startFrame) > framesSteps.get(currentFrame)){
			if(currentFrame  < frames.size() -1){
				currentFrame++;
			} else {
				currentFrame = 0;
			}

			startFrame = Time.getTime();
		}
	}

	public void addFrame(int index, float timestep){
		frames.add(index);
		framesSteps.add(timestep);
	}

	public int getCurrentFrame(){
		return frames.get(currentFrame);
	}
}
