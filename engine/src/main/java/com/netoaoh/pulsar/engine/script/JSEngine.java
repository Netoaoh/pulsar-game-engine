/**
 * Project: PulsarGameEngine
 * Filename: JSEngine.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.script;

import com.netoaoh.pulsar.engine.core.ErrorCode;
import com.netoaoh.pulsar.engine.core.Log;
import com.netoaoh.pulsar.engine.physics.Collision;

import javax.script.*;
import java.util.ArrayList;

public class JSEngine extends IScriptEngine {

	private ScriptEngineManager manager;
	private ScriptEngine engine;
	private Invocable invocable;
	//private ArrayList<ScriptContext> contexts = new ArrayList<ScriptContext>();
	//private ArrayList<String> scriptNames = new ArrayList<String>();
	//private ArrayList<String> scripts = new ArrayList<String>();
	private ArrayList<JSScript> scripts = new ArrayList<JSScript>();
	//private Bindings engineScope;
	//private ScriptContext context;

	public JSEngine() {
		super();
        /*contexts = new ArrayList<ScriptContext>();
        scriptNames = new ArrayList<String>();
        scripts = new ArrayList<String>();*/
	}

	public void initialize() {
		manager = new ScriptEngineManager();
		engine = manager.getEngineByName("nashorn");
	}

	public void awake() {
		//context = new SimpleScriptContext();
		//engineScope = context.getBindings(ScriptContext.GLOBAL_SCOPE);
		//engine.put("texto", "Texto global");
        /*try {
            engine.eval(scripts.get(contexts.size() - 1), contexts.get(contexts.size() - 1));
            engine.setContext(contexts.get(contexts.size() - 1));
            invocable = (Invocable) engine;
            invocable.invokeFunction("Awake");
        } catch (NoSuchMethodException e) {
            Log.getInstance().fatalError(ErrorCode.SCRIPT_INVALID_METHOD, "Não foi possivel localizar o metodo Awake() no script '" + scriptNames.get(contexts.size() - 1) + "'.", e.getMessage());
        } catch (ScriptException e) {
            Log.getInstance().fatalError(ErrorCode.SCRIPT_CALL_FAILURE, "Falha ao executar metodo Awake() no script '" + scriptNames.get(contexts.size() - 1) + "'.", e.getMessage());
        }*/

		try {
			engine.eval(scripts.get(scripts.size() - 1).getScriptResource().getSource(), scripts.get(scripts.size() - 1).getContext());
			engine.setContext(scripts.get(scripts.size() - 1).getContext());
			invocable = (Invocable) engine;
			invocable.invokeFunction("Awake");
		} catch (NoSuchMethodException e) {
			//Log.getInstance().fatalError(ErrorCode.SCRIPT_INVALID_METHOD, "Não foi possivel localizar o metodo Awake() no script '" + scriptNames.get(contexts.size() - 1) + "'.", e.getMessage());
		} catch (ScriptException e) {
			//Log.getInstance().fatalError(ErrorCode.SCRIPT_CALL_FAILURE, "Falha ao executar metodo Awake() no script '" + scriptNames.get(contexts.size() - 1) + "'.", e.getMessage());
		}
	}

	public void start() {
		for (int i = 0; i < scripts.size(); i++) {
			try {
				engine.setContext(scripts.get(i).getContext());
				invocable = (Invocable) engine;
				invocable.invokeFunction("Start");
			} catch (NoSuchMethodException e) {
				//Log.getInstance().fatalError(ErrorCode.SCRIPT_INVALID_METHOD, "Não foi possivel localizar o metodo Start() no script '" + scriptNames.get(contexts.size() - 1) + "'.", e.getMessage());
			} catch (ScriptException e) {
				//Log.getInstance().fatalError(ErrorCode.SCRIPT_CALL_FAILURE, "Falha ao executar metodo Start() no script '" + scriptNames.get(i) + "'.", e.getMessage());
			}
		}
	}

	public void update() {
		for (int i = 0; i < scripts.size(); i++) {
			try {
				engine.setContext(scripts.get(i).getContext());
				invocable = (Invocable) engine;
				invocable.invokeFunction("Update");
			} catch (NoSuchMethodException e) {
				//Log.getInstance().fatalError(ErrorCode.SCRIPT_INVALID_METHOD, "Não foi possivel localizar o metodo Update() no script '" + scriptNames.get(contexts.size() - 1) + "'.", e.getMessage());
			} catch (ScriptException e) {
				//Log.getInstance().fatalError(ErrorCode.SCRIPT_CALL_FAILURE, "Falha ao executar metodo Update() no script '" + scriptNames.get(i) + "'.", e.getMessage());
			}
		}
	}

    public void onCollisionEnter(Collision collision){

		for (int i = 0; i < scripts.size(); i++) {
			try {
				engine.setContext(scripts.get(i).getContext());
				invocable = (Invocable) engine;
				invocable.invokeFunction("OnCollisionEnter", collision);
			} catch (NoSuchMethodException e) {
				//Log.getInstance().fatalError(ErrorCode.SCRIPT_INVALID_METHOD, "Não foi possivel localizar o metodo Update() no script '" + scriptNames.get(contexts.size() - 1) + "'.", e.getMessage());
			} catch (ScriptException e) {
				//Log.getInstance().fatalError(ErrorCode.SCRIPT_CALL_FAILURE, "Falha ao executar metodo Update() no script '" + scriptNames.get(i) + "'.", e.getMessage());
			}
		}
    }

	public int addScript(JSScript script) {
		//scriptNames.add(name);
		scripts.add(script);
		//contexts.add(context);

		return scripts.size() - 1;
	}

	public void removeAll() {
		//scriptNames.clear();
		scripts.clear();
		//contexts.clear();
	}

	public void removeScript(int index) {
		//scriptNames.remove(index);
		scripts.remove(index);
		//contexts.remove(index);
	}
}