/**
 * Project: PulsarGameEngine
 * Filename: ScriptComponent.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.components;

import com.netoaoh.pulsar.engine.core.GameObject;
import com.netoaoh.pulsar.engine.core.IEngine;
import com.netoaoh.pulsar.engine.core.ResourceManager;
import com.netoaoh.pulsar.engine.physics.Collision;
import com.netoaoh.pulsar.engine.script.JSEngine;
import com.netoaoh.pulsar.engine.script.JSScript;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ScriptComponent extends Component {

	private JSScript script;
	public String filename;
	/*private ScriptContext context;
	private Bindings engineScope;
	private String source;*/
	public static HashMap<String, Object> globals = new HashMap<String, Object>();

	public ScriptComponent(String filename) {
		super();
		this.name = getClass().getSimpleName().toString();
		this.filename = filename;
		this.script = new JSScript(ResourceManager.loadScript(filename));
	}

	public void awake() {
		super.awake();
		this.script.getEngineScope().put("gameObject", gameObject);
		//engineScope.put("gameObject", gameObject);
		IEngine.getScriptEngine().addScript(script);
		IEngine.getScriptEngine().awake();
	}

	public void start() {
		super.start();
	}

	public void update() {
		super.update();
		IEngine.getScriptEngine().addScript(script);
	}

	public void render() {
		super.render();
	}

    public void onCollisionEnter(Collision collision){
        super.onCollisionEnter(collision);
		IEngine.getScriptEngine().addScript(script);
		IEngine.getScriptEngine().onCollisionEnter(collision);
    }

	public void clear() {
		//ASXEngine.Instance().RemoveScript(index);
	}

	public static void setGlobalData(String name, Object value) {
		globals.put(name, value);
	}

	public static Object getGlobalData(String name) {
		return globals.get(name);
	}

	public static void InstantiateGameObject(GameObject obj) {
		GameObject.root.addChild(obj);
	}
}
