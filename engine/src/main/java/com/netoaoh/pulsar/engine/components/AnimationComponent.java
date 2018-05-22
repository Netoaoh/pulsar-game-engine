/**
 * Project: PulsarGameEngine
 * Filename: AnimationComponent.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 Design Coding. All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.components;

import com.netoaoh.pulsar.engine.graphics.SpriteAnimation;

import java.util.HashMap;

public class AnimationComponent extends Component {

	public HashMap<String, SpriteAnimation> animationClips;
	public SpriteAnimation currentAnimation;

	public AnimationComponent(){
		super();
		this.name = getClass().getSimpleName().toString();
		animationClips = new HashMap<String,SpriteAnimation>();
		currentAnimation = null;
	}

	public void awake(){
		super.awake();
	}

	public void start(){ super.start(); }

	public void update(){
		super.update();

		if(currentAnimation != null){
			currentAnimation.update();
		} else{
			if(animationClips.size() > 0){
				currentAnimation = animationClips.entrySet().iterator().next().getValue();
				currentAnimation.update();
			}
		}
	}

	public void render(){
		super.render();
	}

	public void addAnimation(String name, SpriteAnimation animation){
		animationClips.put(name, animation);
	}

	public void removeAnimation(String name){
		if(!animationClips.containsKey(name))
			return;

		animationClips.remove(name);
	}

	public int getCurrentAnimation(){
		return currentAnimation.getCurrentFrame();
	}

	public void setAnimation(String name){
		if(animationClips.containsKey(name)){
			currentAnimation = animationClips.get(name);
		}
	}
}
