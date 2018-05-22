/**
 * Project: PulsarGameEngine
 * Filename: PhysicsEngine.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.physics;

import com.netoaoh.pulsar.engine.components.BoxCollider;
import com.netoaoh.pulsar.engine.components.Collider;
import com.netoaoh.pulsar.engine.core.Time;
import com.netoaoh.pulsar.engine.graphics.RenderingEngine;
import com.netoaoh.pulsar.engine.utils.Convert;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import java.util.HashSet;
import java.util.Set;

public class PhysicsEngine extends IPhysicsEngine {

	private World world = null;
	private Set<Body> bodies = null;

	//private OpenglDebugDraw debugDraw = new OpenglDebugDraw();

	public PhysicsEngine() {
		super();
		bodies = new HashSet<Body>();
		//debugDraw.setFlags(debugDraw.e_shapeBit | debugDraw.e_jointBit);
		//world.setDebugDraw(debugDraw);
	}

	public void initialize() {

		world = new World(Convert.convertToVec2(Physics.gravity), false);

		/*BodyDef boxDef = new BodyDef();
		boxDef.type = BodyType.STATIC;
		boxDef.position.set(0, -50);
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(1000, 0);
		Body box = world.createBody(boxDef);
		FixtureDef boxFixture = new FixtureDef();
		boxFixture.density = 1000;
		boxFixture.shape = boxShape;
		boxFixture.restitution = 0.0f;
		boxFixture.friction = 1.0f;
		box.createFixture(boxFixture);
		bodies.add(box);*/
	}

	public void fixedUpdate() {
		world.step(/*Time.deltaTime*/ 1 / 30.0f, 8, 3);

		//world.drawDebugData();
	}

	public void shutdown() {
		//TODO: Limpar Physics engine ao finalizar a engine.
	}

	public void removeBody(Body body){
		if(bodies.contains(body)) {
			world.destroyBody(body);
			bodies.remove(body);
		}
	}

	public Body createBody(BodyDef bodyDef){
		return world.createBody(bodyDef);
	}

	public void addToPhysicsEngine(Body body)
	{
		bodies.add(body);
	}
}
