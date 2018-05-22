/**
 * Project: PulsarGameEngine
 * Filename: Collider.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.components;

import com.netoaoh.pulsar.engine.physics.RigidBody;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;

public class Collider extends Component {

	protected RigidBody rigidbody = null;
	protected PolygonShape polygonShape = null;


	public Collider(){
		super();
		this.name = getClass().getSimpleName().toString();

		rigidbody = new RigidBody();
		polygonShape = new PolygonShape();
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

	public RigidBody getRigidBody(){
		return rigidbody;
	}
}
