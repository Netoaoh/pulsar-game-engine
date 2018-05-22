/**
 * Project: PulsarGameEngine
 * Filename: ScriptResource.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core.resourcemanagement;

public class ScriptResource {

	private String source = "";

	public ScriptResource(String source) {
		this.source = source;
	}

	public String getSource(){
		return source;
	}
}