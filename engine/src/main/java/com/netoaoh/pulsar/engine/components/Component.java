/**
 * Project: PulsarGameEngine
 * Filename: Component.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.components;

import com.netoaoh.pulsar.engine.core.GameObject;
import com.netoaoh.pulsar.engine.physics.Collision;
import org.json.JSONObject;

public class Component {

	protected String name;
	protected boolean enabled;
	protected GameObject gameObject;

	public Component() {
		this.name = "Component";
		this.enabled = true;
	}

	public Component(String name) {
		this.name = name;
		this.enabled = true;
	}

	public void awake() {
	}

	public void start() {
		if (!enabled) return;
	}

	public void fixedUpdate() {
		if (!enabled) return;
	}

	public void update() {
		if (!enabled) return;
	}

	public void render() {
		if (!enabled) return;
	}
	public void onCollisionEnter(Collision collision){ if(!enabled) return; }

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public GameObject getGameObject() {
		return gameObject;
	}

	public void setName(String name) {
		if (name.isEmpty())
			return;

		this.name = name;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setGameObject(GameObject gameObject) {
		if (gameObject == null)
			return;

		this.gameObject = gameObject;
	}

	public JSONObject serialize(){ return new JSONObject();}
    public Component deserialize(String str){ return this; }
}
