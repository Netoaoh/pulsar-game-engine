/**
 * Project: PulsarGameEngine
 * Filename: PostProcessEffect.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics;

import static org.lwjgl.opengl.GL11.*;

public class PostProcessEffect {

	private FrameBuffer frameBuffer;
	private Shader shader;
	public boolean enabled;
	public String name;

	public PostProcessEffect(String name, Shader shader){
		this.shader = shader;
		this.name = name;
		this.enabled = true;
	}

	public void initialize(){
		frameBuffer = new FrameBuffer(1.0f, 1.0f);
		frameBuffer.create();
	}

	public void prepare(){
		frameBuffer.bind();
		glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void finish(){
		frameBuffer.unbind();
	}

	public void render(){

		glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Set clear color to white (not really necessery actually, since we won't be able to see behind the quad anyways)
		glClear(GL_COLOR_BUFFER_BIT);
		glDisable(GL_DEPTH_TEST); // We don't care about depth information when rendering a single quad

		//Draw Screen
		shader.enable();
		frameBuffer.render();
		shader.disable();
	}

	public void clear(){
		frameBuffer.clear();
	}
}
