/**
 * Project: PulsarGameEngine
 * Filename: Vertex.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics;

import com.netoaoh.pulsar.engine.math.Vector2f;
import com.netoaoh.pulsar.engine.math.Vector3f;

public class Vertex {

	public static final int SIZE = 11;
	//public static final int SIZE = 8;

	private Vector3f position;
	private Vector2f texCoord;
	private Vector3f normal;
	private Vector3f tangent;

	public Vertex(Vector3f pos) {
		this.position = pos;
		this.texCoord = new Vector2f(0.0f, 0.0f);
		this.normal = new Vector3f(0.0f, 0.0f, 0.0f);
		this.tangent = new Vector3f(0.0f, 0.0f, 0.0f);
	}

	public Vertex(Vector3f pos, Vector2f texCoord) {
		this.position = pos;
		this.texCoord = texCoord;
		this.normal = new Vector3f(0.0f, 0.0f, 0.0f);
		this.tangent = new Vector3f(0.0f, 0.0f, 0.0f);
	}

	public Vertex(Vector3f pos, Vector2f texCoord, Vector3f normal) {
		this.position = pos;
		this.texCoord = texCoord;
		this.normal = normal;
		this.tangent = new Vector3f(0.0f, 0.0f, 0.0f);
	}

	public Vertex(Vector3f pos, Vector2f texCoord, Vector3f normal, Vector3f tangent) {
		this.position = pos;
		this.texCoord = texCoord;
		this.normal = normal;
		this.tangent = tangent;
	}

	public Vector3f getPos() {
		return position;
	}

	public Vector2f getTexCoord() {
		return texCoord;
	}

	public Vector3f getNormal() {
		return normal;
	}

	public Vector3f getTangent() {
		return tangent;
	}

	public void setPos(Vector3f pos) {
		if (pos != null) this.position = pos;
	}

	public void setTexCoord(Vector2f texCoord) {
		if (texCoord != null) this.texCoord = texCoord;
	}

	public void setNormal(Vector3f normal) {
		if (normal != null) this.normal = normal;
	}

	public void setTangent(Vector3f tangent) {
		if (tangent != null) this.tangent = tangent;
	}
}
