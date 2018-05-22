/**
 * Project: PulsarGameEngine
 * Filename: GameObject.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core;

import com.netoaoh.pulsar.engine.components.BoxCollider;
import com.netoaoh.pulsar.engine.components.Component;
import com.netoaoh.pulsar.engine.components.Transform;
import com.netoaoh.pulsar.engine.math.Vector3f;
import com.netoaoh.pulsar.engine.physics.Collision;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameObject {

	public static GameObject root;

    private UUID id = UUID.randomUUID();

	public String name;
	public String tag;
	public boolean enabled;
	public Transform transform;

	private boolean destroyed;

	private ArrayList<GameObject> children;
	private HashMap<String, Component> components;

	public GameObject() {
		this.name = "GameObject";
		this.tag = "Untagged";
		this.enabled = true;
		this.destroyed = false;
		this.transform = new Transform();
		this.children = new ArrayList<GameObject>();
		this.components = new HashMap<String, Component>();
		//rigidBody.setGameObject(this);
	}

	public GameObject(String name) {
		this.name = name;
		this.tag = "Untagged";
		this.enabled = true;
		this.destroyed = false;
		this.transform = new Transform();
		this.children = new ArrayList<GameObject>();
		this.components = new HashMap<String, Component>();
		//rigidBody.setGameObject(this);
	}

	public GameObject addChild(GameObject child) {
		child.transform.setParent(transform);
		//child.Awake();
		children.add(child);

		return this;
	}

	public GameObject addComponent(Component component) {
		component.setGameObject(this);
		component.awake();
		components.put(component.getName(), component);

		return this;
	}

	public void destroy() {
		destroyed = true;
	}

	public void destroyImmediately() {

		if (destroyed) {
			for (int i = 0; i < children.size(); i++) {
				children.get(i).destroy();
				children.get(i).destroyImmediately();
			}

			components.clear();
			children.clear();
		}
	}

	public static void clearDeadObjects() {
		if (GameObject.root != null) {
			for (int i = 0; i < GameObject.root.getChildrens().size(); i++) {
				GameObject.root.getChildrens().get(i).destroyImmediately();

				if (GameObject.root.getChildrens().get(i).isDestroyed() == true) {
					GameObject.root.getChildrens().remove(i);
				}
			}
		}
	}

	public void start() {
		if (!enabled)
			return;

		startComponents();

		for (GameObject obj : children) {
			obj.start();
		}
	}

	public void fixedUpdate() {
		if (!enabled)
			return;

		fixedUpdateComponents();

		for (GameObject obj : children) {
			obj.fixedUpdate();
		}
	}

	public void update() {
		if (!enabled)
			return;

		updateComponents();

		//if(getComponent(BoxCollider.class) != null)
		//	getComponent(BoxCollider.class).rigidbody.getBody().getPosition().set(transform.getPos().getX(), transform.getPos().getY());

		for (GameObject obj : children) {
			obj.update();
		}
	}

	public void render() {
		if (!enabled)
			return;

		renderComponents();

		for (GameObject obj : children) {
			obj.render();
		}
	}

	/*public void Awake(){
        for(Entry<String, Component> comp : components.entrySet()){
			if(comp.getValue().enabled)
				comp.getValue().Awake();
		}
	}*/

	private void startComponents() {
		for (Map.Entry<String, Component> comp : components.entrySet()) {
			if (comp.getValue().isEnabled())
				comp.getValue().start();
		}
	}

	private void fixedUpdateComponents() {
		for (Map.Entry<String, Component> comp : components.entrySet()) {
			if (comp.getValue().isEnabled())
				comp.getValue().fixedUpdate();
		}
	}

	private void updateComponents() {
		transform.update();

		for (Map.Entry<String, Component> comp : components.entrySet()) {
			if (comp.getValue().isEnabled())
				comp.getValue().update();
		}
	}

	private void renderComponents() {
		for (Map.Entry<String, Component> comp : components.entrySet()) {
			if (comp.getValue().isEnabled())
				comp.getValue().render();
		}
	}

	public ArrayList<GameObject> getAllAttached() {
		ArrayList<GameObject> result = new ArrayList<GameObject>();

		for (GameObject child : children)
			result.addAll(child.getAllAttached());

		result.add(this);

		return result;
	}

    public void onCollisionEnter(Collision collision){
        for(Map.Entry<String, Component> comp : components.entrySet()){
            if(comp.getValue().isEnabled())
                comp.getValue().onCollisionEnter(collision);
        }
    }

	public HashMap<String, Component> getAllComponents() {
		return components;
	}

	public ArrayList<GameObject> getChildrens() {
		return children;
	}

	@SuppressWarnings("unchecked")
	public <T> T getComponent(Class<T> type) {
		if (components.containsKey(type.getSimpleName())) {
			return (T) (components.get(type.getSimpleName()));
		}

		return null;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

    public UUID getId() {
        return id;
    }

    public GameObject findByID(UUID id){
        for (GameObject obj : children) {
            if(obj.getId() == id){
                return obj;
            }
        }

        return null;
    }

	public JSONObject serialize(){
        JSONObject obj = new JSONObject();

        try{
            obj.put("name", name);
            obj.put("tag", tag);
            obj.put("enabled", enabled);
            obj.put("destroyed", destroyed);
            obj.put("transform", transform.serialize());

            JSONArray compArr = new JSONArray();
            int index = 0;
            for (Map.Entry<String, Component> comp : components.entrySet()) {
                compArr.put(index, comp.getValue().serialize());
                index++;
            }
            obj.put("components", compArr);

            JSONArray childArr = new JSONArray();
            for(int i = 0; i < children.size(); i++){
                childArr.put(i, children.get(i).serialize());
            }
            obj.put("children", childArr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public GameObject deserialize(String str){
        try {
            JSONObject obj = new JSONObject(str);

            name = obj.getString("name");
            tag = obj.getString("tag");
            enabled = obj.getBoolean("enabled");
            destroyed = obj.getBoolean("destroyed");
            transform = new Transform().deserialize(obj.getString("transform"));

            JSONArray compArr = obj.getJSONArray("components");
            for(int i = 0; i < compArr.length(); i++){
                addComponent(new Component().deserialize(compArr.get(i).toString()));
            }

            JSONArray childArr = obj.getJSONArray("children");
            for(int i = 0; i < childArr.length(); i++){
                addChild(new GameObject().deserialize(childArr.get(i).toString()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }
}