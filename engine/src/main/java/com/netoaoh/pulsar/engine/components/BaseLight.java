/**
 * Project: PulsarGameEngine
 * Filename: BaseLight.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.components;

import com.netoaoh.pulsar.engine.core.IEngine;
import com.netoaoh.pulsar.engine.graphics.RenderingEngine;
import com.netoaoh.pulsar.engine.graphics.Shader;
import com.netoaoh.pulsar.engine.math.Vector3f;

public class BaseLight extends Component {

	protected Vector3f color;
	protected float intensity;
	protected Shader shader;

	public BaseLight(Vector3f color, float intensity){
		this.color = color;
		this.intensity = intensity;
	}

	public void awake(){
		super.awake();

		//IEngine.getRenderingEngine().AddLight(this);
	}

	public Vector3f getColor() {
		return color;
	}

	public float getIntensity() {
		return intensity;
	}

	public Shader getShader() {
		return shader;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}

	public void setShader(Shader shader) {
		this.shader = shader;
	}
}
