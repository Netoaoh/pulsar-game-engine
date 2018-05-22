/**
 * Project: PulsarGameEngine
 * Filename: Renderer.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.components;

public class Renderer extends Component {

	public Renderer(){
		super();
		this.name = getClass().getSimpleName().toString();
	}

	public void awake(){
		super.awake();
	}

	public void start(){ super.start(); }

	public void update(){
		super.update();
	}

	public void render(){
		super.render();
	}
}
