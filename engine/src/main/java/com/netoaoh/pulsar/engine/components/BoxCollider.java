/**
 * Project: PulsarGameEngine
 * Filename: BoxCollider.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.components;

import com.netoaoh.pulsar.engine.core.GameObject;
import com.netoaoh.pulsar.engine.math.Vector2f;
import com.netoaoh.pulsar.engine.math.Vector3f;
import com.netoaoh.pulsar.engine.physics.Collision;
import com.netoaoh.pulsar.engine.physics.PhysicsEngine;
import com.netoaoh.pulsar.engine.physics.RigidBody;
import com.netoaoh.pulsar.engine.utils.Convert;
import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.contacts.ContactEdge;

public class BoxCollider extends Collider {

	public float width;
	public float height;

	public BoxCollider(float width, float height){
		super();
		this.name = getClass().getSimpleName().toString();
		this.width = width;
		this.height = height;

		polygonShape.setAsBox(width / 2.0f, height / 2.0f);
	}

	public void awake(){
		super.awake();
		rigidbody.createBody(gameObject, polygonShape);
	}

	public void start(){ super.start(); }

	public void fixedUpdate(){
		super.fixedUpdate();
		gameObject.transform.setPos(new Vector3f(rigidbody.getBody().getPosition().x, rigidbody.getBody().getPosition().y, 0));
	}

	public void update(){
		super.update();
		checkCollision();
	}

	private void checkCollision() {
		ContactEdge ce = rigidbody.getBody().getContactList();

		while (ce != null) {
			if (ce.contact.isTouching()) {
				//System.out.println("asdasds");
				//TODO: Handle collisions

				/*if (ce.contact.getFixtureA().getUserData() != null)
				{
					GameObject obj = (GameObject)ce.contact.getFixtureA().getUserData();
					if(obj != null)
						System.out.println("A");
						//obj.onCollisionEnter(new Collision(obj));
				}*/

				//if (/*ce.contact.getFixtureB().getUserData()*/ce.other.getFixtureList().getUserData()  != null)
				//{
					//GameObject obj = (GameObject)ce.contact.getFixtureB().getUserData();
					/*GameObject obj = (GameObject)ce.other.getFixtureList().getUserData();
					if(obj != null)
						System.out.println("B" + gameObject.name);*/
						//obj.onCollisionEnter(new Collision(obj));

					//if(ce.contact.getFixtureB().getBody() == rigidbody.getBody())
					//{
						GameObject obj = (GameObject)ce.other.getFixtureList().getUserData();
						gameObject.onCollisionEnter(new Collision(obj));
					//}

				//}
			}

			ce = ce.next;
		}
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
		polygonShape.setAsBox(width / 2.0f, height / 2.0f);
		rigidbody.createBody(gameObject, polygonShape);
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
		polygonShape.setAsBox(width / 2.0f, height / 2.0f);
		rigidbody.createBody(gameObject, polygonShape);
	}
}
