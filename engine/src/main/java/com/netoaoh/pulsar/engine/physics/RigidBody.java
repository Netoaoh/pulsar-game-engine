/**
 * Project: PulsarGameEngine
 * Filename: RigidBody.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.physics;

import com.netoaoh.pulsar.engine.core.GameObject;
import com.netoaoh.pulsar.engine.core.IEngine;
import com.netoaoh.pulsar.engine.math.Vector2f;
import com.netoaoh.pulsar.engine.math.Vector3f;
import com.netoaoh.pulsar.engine.utils.Convert;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

public class RigidBody {

	private Body body = null;

	private BodyType bodyType = null;

	private float density = 0.0f;
	private float friction = 0.0f;
	private float restitution = 0.0f;

	private BodyDef bodyDef = null;

	public RigidBody(){
		density = 1.0f;
		friction = 0.3f;
		bodyType = BodyType.DYNAMIC;

		bodyDef = new BodyDef();
		createBody(null, null);
	}

	public void createBody(GameObject gameObject, PolygonShape polygonShape){

		IEngine.getPhysicsEngine().removeBody(body);

		//Body Definition
		bodyDef.type = bodyType;

		if(gameObject != null)
			bodyDef.position.set(gameObject.transform.getPos().getX(), gameObject.transform.getPos().getY());
		else
			bodyDef.position.set(0.0f , 0.0f);

		//Create Box2D body
		body = IEngine.getPhysicsEngine().createBody(bodyDef);

		//Fixture definition
		FixtureDef fixtureDef = new FixtureDef();

		if(polygonShape != null)
		{
			fixtureDef.shape = polygonShape;
		}
		else
		{
			PolygonShape p = new PolygonShape();
			p.setAsBox(0,0);
			fixtureDef.shape = p;
		}


		fixtureDef.density = density;
		fixtureDef.friction = friction;

		if(gameObject != null)
			fixtureDef.userData = gameObject;

		//Create Box2D fixture
		body.createFixture(fixtureDef);

		IEngine.getPhysicsEngine().addToPhysicsEngine(body);
	}

	public void update(Vector2f pos){

	}

	public void addForce(Vector2f force){
		body.setLinearVelocity(Convert.convertToVec2(force));
	}

	public Body getBody() {
		return body;
	}

	public BodyType getBodyType() {
		return bodyType;
	}

	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
		body.setType(bodyType);
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
		body.getFixtureList().setDensity(density);
	}

	public float getFriction() {
		return friction;
	}

	public void setFriction(float friction) {
		this.friction = friction;
		body.getFixtureList().setFriction(friction);
	}

	public float getRestitution() {
		return restitution;
	}

	public void setRestitution(float restitution) {
		this.restitution = restitution;
		body.getFixtureList().setRestitution(restitution);
	}
}
