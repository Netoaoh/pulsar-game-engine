/**
 * Project: PulsarGameEngine
 * Filename: Scene.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core;

import com.netoaoh.pulsar.engine.components.*;
import com.netoaoh.pulsar.engine.graphics.*;
import com.netoaoh.pulsar.engine.math.Matrix4f;
import com.netoaoh.pulsar.engine.math.Quaternion;
import com.netoaoh.pulsar.engine.math.Vector2f;
import com.netoaoh.pulsar.engine.math.Vector3f;
import com.netoaoh.pulsar.engine.utils.Convert;
import com.netoaoh.pulsar.engine.utils.FileManager;
import org.jbox2d.dynamics.BodyType;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class Scene extends IScene {

	public Scene(){
		super("Demo Scene");
	}

	public void start() {

		GameObject cameraObject = new GameObject("Camera");
		cameraObject.addComponent(new Camera(new Matrix4f().initOrthographic(-80.0f, 80.0f, -60.0f, 60.0f, -1, 100)));
		//cameraObject.addComponent(new Camera(new Matrix4f().initPerspective(45.0f, 800/600, 0.1f, 1000)));
		cameraObject.addComponent(new ScriptComponent("camera.js"));
		cameraObject.addComponent(new AudioListenerComponent());
		addToScene(cameraObject);

		GameObject obj = new GameObject("player");
		obj.addComponent(new SpriteRenderer(new Sprite(16,16), new Material("Default", new Texture("Charizard.png", 4))));
		obj.addComponent(new AudioSourceComponent("bounce.wav"));
		obj.addComponent(new ScriptComponent("player.js"));
		obj.addComponent(new BoxCollider(20, 20));

		GameObject floor = new GameObject("Chão");
		BoxCollider boxCollider = new BoxCollider(200, 1);
		boxCollider.getRigidBody().setDensity(1000);
		boxCollider.getRigidBody().setRestitution(0);
		boxCollider.getRigidBody().setBodyType(BodyType.STATIC);
		floor.addComponent(boxCollider);
		addToScene(floor);

		//TODO:Melhorar isto
		boxCollider.getRigidBody().getBody().setTransform(Convert.convertToVec2(new Vector2f(0, -50)),boxCollider.getRigidBody().getBody().getAngle());
		floor.transform.setPos(new Vector3f(0,-50,0));

		SpriteAnimation idleAnim = new SpriteAnimation("idle");
		//idleAnim.addFrame(0, 0.25f);
		idleAnim.addFrame(0, 0.25f);
		/*idleAnim.addFrame(2, 0.25f);
		idleAnim.addFrame(3, 0.25f);*/

		SpriteAnimation walkRightAnim = new SpriteAnimation("left");
		walkRightAnim.addFrame(4, 0.25f);
		walkRightAnim.addFrame(5, 0.25f);
		walkRightAnim.addFrame(6, 0.25f);
		walkRightAnim.addFrame(7, 0.25f);

		SpriteAnimation walkLeftAnim = new SpriteAnimation("right");
		walkLeftAnim.addFrame(8, 0.25f);
		walkLeftAnim.addFrame(9, 0.25f);
		walkLeftAnim.addFrame(10, 0.25f);
		walkLeftAnim.addFrame(11, 0.25f);

		AnimationComponent anim = new AnimationComponent();
		anim.addAnimation("idle", idleAnim);
		anim.addAnimation("left", walkLeftAnim);
		anim.addAnimation("right", walkRightAnim);
		anim.setAnimation("idle");
		obj.addComponent(anim);
		addToScene(obj);

        /*for(int i = 0; i < 500; i++){

            GameObject obj = new GameObject("player");
            //obj.addComponent(new MeshRenderer(new Mesh("cube.obj"), new Material("block", new Texture("block.png"))));
            //obj.addComponent(new SpriteRenderer(new Sprite(1.0f,1.0f), new Material("Default", new Shader("Basic"),new Texture("Default.png"))));
            //obj.addComponent(new SphereCollider(2));
            //obj.rigidBody.setMass(1.0f);
            float x = (float)Math.random() * 20.0f;
            float y = (float)Math.random() * 30.0f;
            float z = (float)Math.random() * 10.0f;

            obj.transform.setPos(new Vector3f(x, y, z));
            addToScene(obj);

        }*/

		//cameraObject.transform.setPos(new Vector3f(0.0f, 0.0f, -5.0f));
        root.transform.setPos(new Vector3f(5,8,2));
		root.start();

        /*JSONObject my_obj = new JSONObject();
        try{
            //my_obj.put("vector", new Vector3f(2,5,8.9f).serialize());
            my_obj.put("name", name);
            my_obj.put("root", root.serialize());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //String json_string = my_obj.toString();
        //System.out.println(json_string);


        try {
            try (FileWriter file = new FileWriter(Application.resourcePath("/teste.json"))) {
                file.write(my_obj.toString());
                System.out.println("Successfully Copied JSON Object to File...");
                System.out.println("\nJSON Object: " + my_obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String json_str = FileManager.loadAsString(Application.resourcePath("/teste.json"));

        //instancia um novo JSONObject passando a string como entrada
        try {
            JSONObject my_obj2 = new JSONObject(json_str);

            GameObject v = new GameObject();
            v.deserialize(my_obj2.getString("root"));

            System.out.println(v.transform.getPos().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

	/*public void fixedUpdate(){
		root.fixedUpdate();
	}

	public void update(){
		root.update();
	}*/

	/*public void addToScene(GameObject obj){
		if(obj != null)
			root.addChild(obj);
	}*/

	/*public String getName() {
		return name;
	}

	public GameObject getRootObject(){
	    return root;
    }*/
}