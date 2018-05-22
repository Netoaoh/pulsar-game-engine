/**
 * Project: PulsarGameEngine
 * Filename: JSScript.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.script;

import com.netoaoh.pulsar.engine.core.resourcemanagement.ScriptResource;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.SimpleScriptContext;

public class JSScript {

	private ScriptContext context;
	private Bindings engineScope;
	private ScriptResource scriptResource;

	public JSScript(ScriptResource scriptResource) {
		this.scriptResource = scriptResource;
		this.context = new SimpleScriptContext();
		this.engineScope = context.getBindings(ScriptContext.ENGINE_SCOPE);
	}


	public ScriptContext getContext() {
		return context;
	}

	public Bindings getEngineScope() {
		return engineScope;
	}

	public ScriptResource getScriptResource() {
		return scriptResource;
	}
}
